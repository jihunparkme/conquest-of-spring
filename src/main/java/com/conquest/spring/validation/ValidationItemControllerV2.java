package com.conquest.spring.validation;

import com.conquest.spring.validation.domain.Item;
import com.conquest.spring.validation.domain.ItemRepository;
import com.conquest.spring.validation.domain.ItemSaveForm;
import com.conquest.spring.validation.domain.ItemUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/validation/v2/items")
@RequiredArgsConstructor
public class ValidationItemControllerV2 {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation/v2/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "validation/v2/addForm";
    }

    /**
     * @Validated 를 적용하여 검증 사용
     * 검증 에러 발생 시 FieldError, ObjectError 를 생성해서 BindingResult 에 담아 준다.
     */
    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute("item") ItemSaveForm form,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        // 글로벌 오류는 @ScriptAssert() 의 많은 제약으로 자바 코드로 작성 권장
        if (form.globalFieldIsNotNull()) {
            if (form.totalPrice() < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000, form.totalPrice()}, null);
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            return "validation/v2/addForm";
        }

        Item item = Item.builder()
                .itemName(form.getItemName())
                .price(form.getPrice())
                .quantity(form.getQuantity())
                .build();

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/validation/v2/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId,
                       @Validated @ModelAttribute("item") ItemUpdateForm form,
                       BindingResult bindingResult) {

        // 글로벌 오류는 @ScriptAssert() 의 많은 제약으로 자바 코드로 작성 권장
        if (form.globalFieldIsNotNull()) {
            if (form.totalPrice() < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000, form.totalPrice()}, null);
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "validation/v2/editForm";
        }

        Item itemParam = Item.builder()
                .itemName(form.getItemName())
                .price(form.getPrice())
                .quantity(form.getQuantity())
                .build();
        itemRepository.update(itemId, itemParam);

        return "redirect:/validation/v2/items/{itemId}";
    }
}
