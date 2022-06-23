package study.authorization;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import study.authorization.domain.member.Member;
import study.authorization.domain.member.MemberRepository;

import javax.annotation.PostConstruct;

@Controller
@RequiredArgsConstructor
public class TestInit {

    private final MemberRepository memberRepository;

    @PostConstruct
    public void init() {
        Member member = new Member();
        member.setName("memberA");
        member.setPassword("1234");
        member.setWallet(10000L);

        memberRepository.save(member);

        Member member2 = new Member();
        member2.setName("memberB");
        member2.setPassword("1234");
        member2.setWallet(10000L);

        memberRepository.save(member2);
    }
}
