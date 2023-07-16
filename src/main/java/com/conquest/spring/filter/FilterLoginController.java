package com.conquest.spring.filter;

import com.conquest.spring.login.domain.login.LoginService;
import com.conquest.spring.login.domain.member.Member;
import com.conquest.spring.login.domain.member.MemberRepository;
import com.conquest.spring.login.web.login.LoginForm;
import com.conquest.spring.session.httpSession.SessionConst;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/filter")
@RequiredArgsConstructor
public class FilterLoginController {

    private final LoginService loginService;
    private final MemberRepository memberRepository;

    /**
     * @CookieValue : 쿠키 조회
     */
    @GetMapping("/")
    public String homeLogin(@CookieValue(name = "memberId", required = false) Long memberId, Model model) {

        if (memberId == null) {
            return "home";
        }

        Member loginMember = memberRepository.findById(memberId);
        if (loginMember == null) {
            return "home";
        }

        model.addAttribute("member", loginMember);

        return "loginHome";
    }

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(
            @Valid @ModelAttribute LoginForm form, BindingResult bindingResult,
            @RequestParam(defaultValue = "/") String redirectURL,
            HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        // 세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
        HttpSession session = request.getSession();
        // 세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        /**
         * 미인증 사용자는 요청 경로를 포함해서 /login 에 redirectURL 요청 파라미터를 추가해서 요청
         * 로그인 성공시 해당 경로로 고객을 redirect
         */
        return "redirect:" + redirectURL;
    }

    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        expireCookie(response, "memberId");
        return "redirect:/";
    }

    /**
     * setMaxAge 로 쿠키 만료
     */
    private void expireCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
