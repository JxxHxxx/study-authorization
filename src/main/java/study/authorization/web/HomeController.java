package study.authorization.web;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import study.authorization.domain.member.Member;
import study.authorization.domain.member.MemberRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static study.authorization.domain.login.LoginController.MEMBER_ID_SESSION;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {


    private final MemberRepository memberRepository;

    @GetMapping("/")
    public String home(@SessionAttribute(name = MEMBER_ID_SESSION, required = false) Long memberId, Model model) {
        log.info("memberId = [{}]를 가진 유저를 찾습니다.", memberId);
        Member loginMember = memberRepository.findById(memberId);

        // DB에 해당 memberId가 없는 경우
        if (loginMember == null) {
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";
    }
}
