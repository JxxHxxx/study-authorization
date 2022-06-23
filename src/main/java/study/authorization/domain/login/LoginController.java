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
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
    public String postLoginForm(@Valid @ModelAttribute("loginDto") LoginDto loginDto, BindingResult bindingResult, HttpServletResponse response) {
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

        //쿠키 생성 //쿠키에 시간 정보를 주지 않으면 세션 크키(브라우저 종료시 모두 종료)
        Cookie idCookie = new Cookie("memberIdCookie", String.valueOf(loginMember.getId()));

        //생성된 쿠키를 클라이언트(브라우저)로 보내줘야함 > 응답에 담아서 같이 보냄
        response.addCookie(idCookie);

        // 로그인 성공 처리 TODO

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
    public String logout( HttpServletResponse response){

        Cookie cookie = new Cookie("memberIdCookie", null);
        cookie.setMaxAge(0);

        response.addCookie(cookie);
        return "redirect:/";
    }

}
