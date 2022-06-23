package study.authorization.web;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import study.authorization.domain.member.Member;
import study.authorization.domain.member.MemberRepository;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {


    private final MemberRepository memberRepository;

    @GetMapping("/")
    public String home(@CookieValue(name = "memberIdCookie", required = false) Long memberId, Model model) {

        //알 수 없는 쿠키키
        if (memberId == null) {
            return "home";
        }

        Member loginMember = memberRepository.findById(memberId);

        // DB에 해당 memberId가 없는 경우
        if (loginMember == null) {
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";
    }
}
