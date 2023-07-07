package com.conquest.spring.validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ValidationItemApiControllerModelAttributeTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    private static final String BASE_URL = "/validation/api/items";

    @Test
    void validation_item_api_success() throws Exception {
        String request = "itemName=hello&price=10000&quantity=10";

        String expected =
                "{" +
                    "\"itemName\":\"hello\"," +
                    "\"price\":10000," +
                    "\"quantity\":10" +
                "}";
        mvc.perform(post(BASE_URL + "/model-attribute/add")
                        .content(request)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                )
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    void validation_item_api_validation_fail() throws Exception {
        String request = "itemName=hello&price=10000&quantity=100000";

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
        mvc.perform(post(BASE_URL + "/model-attribute/add")
                        .content(request)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                )
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    void validation_item_api_request_fail() throws Exception {
        String request = "itemName=hello&price=A&quantity=100000";

        String expected =
                "[" +
                    "{" +
                        "\"codes\":[" +
                            "\"typeMismatch.itemSaveForm.price\"," +
                            "\"typeMismatch.price\"," +
                            "\"typeMismatch.java.lang.Integer\"," +
                            "\"typeMismatch\"" +
                        "]," +
                         "\"arguments\":[" +
                            "{" +
                                "\"codes\":[" +
                                    "\"itemSaveForm.price\"," +
                                    "\"price\"" +
                                "]," +
                                "\"arguments\":null," +
                                "\"defaultMessage\":\"price\"," +
                                "\"code\":\"price\"" +
                             "}" +
                        "]," +
                        "\"defaultMessage\":\"Failed to convert property value of type 'java.lang.String' to required type 'java.lang.Integer' for property 'price'; For input string: \\\"A\\\"\"," +
                        "\"objectName\":\"itemSaveForm\"," +
                        "\"field\":\"price\"," +
                        "\"rejectedValue\":\"A\"," +
                        "\"bindingFailure\":true," +
                        "\"code\":\"typeMismatch\"" +
                        "}," +
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
        mvc.perform(post(BASE_URL + "/model-attribute/add")
                        .content(request)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                )
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }
}