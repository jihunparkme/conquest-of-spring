package com.conquest.spring.bean;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
@SpringBootTest
public class GetBeanTest {

    @Autowired
    private AnnotationConfigApplicationContext ac;

    @Test
    void findAllBean() {
        log.info("{}", "스프링에 등록된 모든 빈 정보 조회");
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);  // 빈 이름으로 빈 객체(인스턴스) 조회
            log.info("name: {}, object: {}", beanDefinitionName, bean);
        }
    }

    @Test
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        log.info("{}", "[ROLE_APPLICATION]: 직접 등록한 애플리케이션 빈");
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                log.info("name: {}, object: {}", beanDefinitionName, bean);
            }
        }

        log.info("{}", "[ROLE_INFRASTRUCTURE]: 스프링이 내부에서 사용하는 빈");
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            if (beanDefinition.getRole() == BeanDefinition.ROLE_INFRASTRUCTURE) {
                Object bean = ac.getBean(beanDefinitionName);
                log.info("name: {}, object: {}", beanDefinitionName, bean);
            }
        }
    }
}
