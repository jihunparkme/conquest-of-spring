package com.conquest.spring.exception.servlet.api.exceptionResolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

    /**
     * ModelAndView 를 반환하는 이유는 try-catch 처럼 Exception 을 처리해서 정상 흐름처럼 변경하여 예외를 해결하는 것이 목적
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            /**
             * IllegalArgumentException 발생 시 response.sendError(400) 를 호출해서 HTTP 상태 코드를 400 으로 지정하고, 빈 ModelAndView 반환
             */
            if (ex instanceof IllegalArgumentException) {
                log.info("IllegalArgumentException resolver to 400");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());

                return new ModelAndView();
            }
        } catch (IOException e) {
            log.error("resolver ex", e);
        }

        return null;
    }
}
