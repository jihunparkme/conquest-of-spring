package com.conquest.spring.argumentResolver;

import com.conquest.spring.login.domain.member.Member;
import com.conquest.spring.session.httpSession.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


@Slf4j
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
        boolean hasMemberType = Member.class.isAssignableFrom(parameter.getParameterType());

        // @Login 어노테이션이 있으면서 Member 타입이면 해당 ArgumentResolver 사용
        return hasLoginAnnotation && hasMemberType;
    }

    /**
     * 컨트롤러 호출 직전에 호출 되어서 필요한 파라미터 정보를 생성해
     * - 세션에 있는 로그인 회원 정보인 member 객체를 찾아서 반환
     * - 이후 스프링MVC 는 컨트롤러의 메서드를 호출하면서 여기에서 반환된 member 객체를 파라미터에 전달
     */
    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }

        return session.getAttribute(SessionConst.LOGIN_MEMBER);
    }
}