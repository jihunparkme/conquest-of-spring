package com.conquest.spring.validation;

import com.conquest.spring.validation.domain.Item;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class BeanValidationTest {

    @Test
    void beanValidation() {
        /**
         * 검증기 생성
         */
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Item item = new Item();
        item.setItemName(" ");
        item.setPrice(0);
        item.setQuantity(10000);

        /**
         * 검증 실행
         *
         * 검증 대상(item)을 검증기에 삽입
         * Set 에는 ConstraintViolation 이라는 검증 오류가 담김
         * 결과가 비어있으면 검증 오류가 없는 것
         */
        Set<ConstraintViolation<Item>> violations = validator.validate(item);
        for (ConstraintViolation<Item> violation : violations) {
            System.out.println("violation=" + violation);
            System.out.println("violation.message=" + violation.getMessage() + "\n");
        }
    }
}