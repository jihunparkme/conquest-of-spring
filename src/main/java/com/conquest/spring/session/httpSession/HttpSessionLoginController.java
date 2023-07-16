package com.conquest.spring.session.httpSession;

import com.conquest.spring.login.domain.login.LoginService;
import com.conquest.spring.login.domain.member.Member;
import com.conquest.spring.login.web.login.LoginForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HttpSessionLoginController {

    private final LoginService loginService;

    public String homeLogin(HttpServletRequest request, Model model) {
        // 세션이 없을 경우(세션을 조회할 시점에는 false 옵션으로 세션을 생성하지 않도록)
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "home";
        }

        // 세션 정보 조회
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        // 세션에 회원 데이터가 없을 경우
        if (loginMember == null) {
            return "home";
        }
        // 세션이 있을 경우
        model.addAttribute("member", loginMember);
        return "loginHome";
    }

    /**
     * 스프링은 세션을 더 편리하게 사용할 수 있도록 `@SessionAttribute` 지원
     * - 세션과 세션 데이터를 찾는 번거로운 과정을 스프링이 한번에 처리
     */
    @GetMapping("/")
    public String homeLoginV2Spring(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model) {
        // 세션에 회원 데이터가 있을 경우
        if (loginMember == null) {
            return "home";
        }

        // 세션이 있을 경우
        model.addAttribute("member", loginMember);
        return "loginHome";
    }

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        log.info("login? {}", loginMember);

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        // 로그인 성공 처리
        // 세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
        HttpSession session = request.getSession();
        // 세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // 세션 삭제
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }
}
