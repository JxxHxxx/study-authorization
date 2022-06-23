package study.authorization.domain.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.authorization.domain.member.Member;
import study.authorization.domain.member.MemberRepository;



@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String name, String password) {
        Member findMember = memberRepository.findByName(name);

        if (findMember == null) {
            return null;
        }

        if(isMatched(findMember, password)) {
            return findMember;
        }
        return null;
    }

    private boolean isMatched(Member findMember, String password) {
        return findMember.getPassword().equals(password);
    }
}
