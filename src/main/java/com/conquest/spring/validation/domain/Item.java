package com.conquest.spring.validation.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;
}
