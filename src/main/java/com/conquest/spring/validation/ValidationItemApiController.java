package com.conquest.spring.validation;

import com.conquest.spring.validation.domain.ItemSaveForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/validation/api/items")
public class ValidationItemApiController {

    /**
     * HttpMessageConverter 단계에서 JSON 데이터를 객체로 변경 실패파면, 이후 단계가 진행되지 않고 400 Bad Request 예외 발생
     * 타입 오류가 발생할 경우 컨트롤러도 호출되지 않고, Validator 도 적용 불가
     */
    @PostMapping("/request-body/add")
    public Object addItemByRequestBody(@RequestBody @Validated ItemSaveForm form, BindingResult bindingResult) {
        log.info("API 컨트롤러 호출");

        if (bindingResult.hasErrors()) {
            log.info("검증 오류 발생 errors={}", bindingResult);
            return bindingResult.getAllErrors();
        }

        log.info("성공 로직 실행");
        return form;
    }

    /**
     * ModelAttribute 대상 객체는 `Setter` 메서드 필요
     * 필드 단위로 정교하게 바인딩이 적용
     * 특정 필드가 바인딩 되지 않더라도 나머지 필드는 정상 바인딩 되고, @Validator 를 사용한 검증도 적용 가능
     */
    @PostMapping("/model-attribute/add")
    public Object addItemByModalAttribute(@ModelAttribute @Validated ItemSaveForm form, BindingResult bindingResult) {
        log.info("API 컨트롤러 호출");

        if (bindingResult.hasErrors()) {
            log.info("검증 오류 발생 errors={}", bindingResult);
            return bindingResult.getAllErrors();
        }

        log.info("성공 로직 실행");
        return form;
    }
}
