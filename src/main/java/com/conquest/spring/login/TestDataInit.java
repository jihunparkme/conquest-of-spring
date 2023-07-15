package com.conquest.spring.login;

import com.conquest.spring.login.domain.member.Member;
import com.conquest.spring.login.domain.member.MemberRepository;
import com.conquest.spring.validation.domain.Item;
import com.conquest.spring.validation.domain.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataInit {
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(Item.builder()
                .itemName("itemA")
                .price(10000)
                .quantity(10)
                .build());
        itemRepository.save(Item.builder()
                .itemName("itemB")
                .price(20000)
                .quantity(20)
                .build());

        Member member = new Member();
        member.setLoginId("test");
        member.setPassword("test!");
        member.setName("테스터");
        memberRepository.save(member);
    }
}
