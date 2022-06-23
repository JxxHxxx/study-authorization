package study.authorization.domain.login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import study.authorization.domain.member.Member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    public static final String MEMBER_ID_SESSION = "MEMBER_ID_SESSION";
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
        // 세션에 로그이 회원 정보 보관
        session.setAttribute(MEMBER_ID_SESSION, loginMember.getId());

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
