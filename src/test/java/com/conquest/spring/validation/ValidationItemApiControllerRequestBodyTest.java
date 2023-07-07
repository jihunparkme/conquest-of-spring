package com.conquest.spring.validation;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.conquest.spring.validation.domain.ItemSaveForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ValidationItemApiControllerRequestBodyTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    private static final String BASE_URL = "/validation/api/items";

    @Test
    @DisplayName("요청 성공")
    void validation_item_api_success() throws Exception {
        String itemName = "hello";
        int price = 10000;
        int quantity = 10;

        ItemSaveForm request = ItemSaveForm.builder().itemName(itemName)
                .price(price)
                .quantity(quantity)
                .build();
        String body = mapper.writeValueAsString(request);

        String expected =
                "{" +
                    "\"itemName\":\"hello\"," +
                    "\"price\":10000," +
                    "\"quantity\":10" +
                "}";
        mvc.perform(post(BASE_URL + "/request-body/add")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    @DisplayName("요청 실패: 검증 실패")
    void validation_item_api_validation_fail() throws Exception {
        String itemName = "hello";
        int price = 1000;
        int quantity = 100000;

        ItemSaveForm request = ItemSaveForm.builder().itemName(itemName)
                .price(price)
                .quantity(quantity)
                .build();
        String body = mapper.writeValueAsString(request);

        String expected =
                "[" +
                    "{" +
                        "\"codes\":[" +
                            "\"Max.itemSaveForm.quantity\"," +
                            "\"Max.quantity\"," +
                            "\"Max.java.lang.Integer\"," +
                            "\"Max\"" +
                        "]," +
                        "\"arguments\":[" +
                        "{" +
                            "\"codes\":[" +
                                "\"itemSaveForm.quantity\"," +
                                "\"quantity\"" +
                            "]," +
                            "\"arguments\":null," +
                            "\"defaultMessage\":\"quantity\"," +
                            "\"code\":\"quantity\"" +
                        "}," +
                        "9999" +
                        "]," +
                        "\"defaultMessage\":\"must be less than or equal to 9999\"," +
                        "\"objectName\":\"itemSaveForm\"," +
                        "\"field\":\"quantity\"," +
                        "\"rejectedValue\":100000," +
                        "\"bindingFailure\":false," +
                        "\"code\":\"Max\"" +
                    "}" +
                "]";
        mvc.perform(post(BASE_URL + "/request-body/add")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    @DisplayName("요청 실패: 요청 타입 오류")
    void validation_item_api_request_fail() throws Exception {
        String request =
                "{" +
                    "\"itemName\":\"hello\", " +
                    "\"price\":\"A\", " +
                    "\"quantit\": 10000" +
                "}";

        mvc.perform(post(BASE_URL + "/request-body/add")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is4xxClientError());
    }
}