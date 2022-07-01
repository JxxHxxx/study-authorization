package study.authorization.domain.login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import study.authorization.domain.member.Member;

import javax.servlet.http.*;
import javax.validation.Valid;

import java.util.NoSuchElementException;

import static study.authorization.domain.login.SessionConst.MEMBER_ID;


@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {


    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(Model model) {

        model.addAttribute("loginDto", new LoginDto());
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String postLoginForm(@Valid @ModelAttribute("loginDto") LoginDto loginDto, BindingResult bindingResult, HttpServletRequest request) {
        log.info("유저 [{}] 로그인 검증을 실시합니다.", loginDto.getName());

        if (bindingResult.hasErrors()) {
            log.info("로그인 입력 양식에 오류가 존재합니다.");
            return "login/loginForm";
        }

        Member loginMember = loginService.login(loginDto.getName(), loginDto.getPassword());

        if (loginMember == null) {
            log.info("유저 [{}] 로그인 검증에 실패합니다.", loginDto.getName());
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        // 로그인 성공 처리
        // 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession();
        if (session.isNew()) {
            log.info("세션을 신규로 생성합니다.");
            log.info("신규 세션 ID [{}]", session.getId());
        }
        try {
            log.info("세션 setAttribute 전 NAME [{}]", session.getAttributeNames().nextElement());
        } catch (NoSuchElementException e) {
            log.info("세션 setAttribute 전에는 Name == null");
        }

        // 세션에 로그이 회원 정보 보관
        session.setAttribute(MEMBER_ID, loginMember.getId());

        try {
            log.info("세션 setAttribute 후 NAME [{}]", session.getAttributeNames().nextElement());
        } catch (NoSuchElementException e) {
            log.info("=============");
        }

        log.info("유저 [{}] 로그인 검증에 성공합니다.", loginDto.getName());
        return "redirect:/";
    }

    //@GetMapping("/logout")
    public String logoutGet( HttpServletResponse response){

        Cookie cookie = new Cookie("memberIdCookie", null);
        cookie.setMaxAge(0);

        response.addCookie(cookie);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }

}
