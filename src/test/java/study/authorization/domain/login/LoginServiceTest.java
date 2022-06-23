package study.authorization.domain.login;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.authorization.domain.member.Member;
import study.authorization.domain.member.MemberRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LoginServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    LoginService loginService;


    @BeforeEach
    void beforeEach() {
        memberRepository.clear();

        Member member = new Member();
        member.setName("memberA");
        member.setPassword("1234");
        member.setWallet(10000L);

        memberRepository.save(member);
    }

    @DisplayName("Name/비밀번호가 매칭되어야 로그인이 가능하고 Member 타입을 리턴한다.")
    @Test
    void passedLoginService() {
        Member memberA = loginService.login("memberA", "1234");

        assertThat(memberA.getWallet()).isEqualTo(10000L);
    }

    @DisplayName("Name/비밀번호가 매칭되지 않으면 NULL을 반환한다.")
    @Test
    void notPassedLoginService() {
        // 아이디가 없을 때
        Member memberB = loginService.login("memberB", "1234");

        assertThat(memberB).isNull();

        // 아이디와 비밀번호가 매칭되지 않을 떄
        Member memberA = loginService.login("memberA", "1235");
        assertThat(memberA).isNull();
    }
}