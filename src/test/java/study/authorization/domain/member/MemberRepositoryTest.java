package study.authorization.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {


    @DisplayName("싱글톤 테스트")
    @Test
    void isSingleTon() {

        MemberRepository memberRepository1 = MemberRepository.getInstance();
        MemberRepository memberRepository2 = MemberRepository.getInstance();

        assertThat(memberRepository1).isEqualTo(memberRepository2);

    }
}