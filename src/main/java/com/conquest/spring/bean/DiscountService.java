package com.conquest.spring.bean;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DiscountService {
    /**
     * Map(Key=스프링 빈 이름, value=DiscountPolicy 타입으로 조회한 모든 스프링 빈)
     *
     * policyMap
     * {key = "fixDiscountPolicy", value = {FixDiscountPolicy@5731}}
     * {key = "rateDiscountPolicy", value = {RateDiscountPolicy@5732}}
     */
    private final Map<String, DiscountPolicy> policyMap;
    /**
     * DiscountPolicy 타입으로 조회한 모든 스프링 빈을
     *
     * policies
     *  0 = {FixDiscountPolicy@5736} discountFixAmount = 1000
     *  1 = {RateDiscountPolicy@5737} discountPercent = 10
     */
    private final List<DiscountPolicy> policies;

    public int discount(Member member, int price, String discountCode) {
        DiscountPolicy discountPolicy = policyMap.get(discountCode);
        return discountPolicy.discount(member, price);
    }
}