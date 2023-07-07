package com.conquest.spring.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Member {
    private long id;
    private String name;
    private Grade grade;
}
