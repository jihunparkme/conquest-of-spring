package com.conquest.spring.bean;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
