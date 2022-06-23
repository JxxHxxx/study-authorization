package study.authorization.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository1;

    @Autowired
    MemberRepository memberRepository2;

    @DisplayName("싱글톤 테스트")
    @Test
    void isSingleTon() {
        assertThat(memberRepository1).isEqualTo(memberRepository2);
    }
}