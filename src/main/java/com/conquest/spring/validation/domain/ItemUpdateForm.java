package com.conquest.spring.validation.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class ItemUpdateForm {

    @NotNull
    private Long id;

    @NotBlank
    private String itemName;

    @NotNull
    @Range(min = 1000, max = 1000000)
    private Integer price;

    private Integer quantity;

    public boolean globalFieldIsNotNull() {
        return this.price != null && this.quantity != null;
    }

    public int totalPrice() {
        return this.price * this.quantity;
    }
}
