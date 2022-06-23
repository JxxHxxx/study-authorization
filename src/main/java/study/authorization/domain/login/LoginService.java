package study.authorization.domain.login;

import study.authorization.domain.member.Member;
import study.authorization.domain.member.MemberRepository;



public class LoginService {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    private LoginService() {

    }

    private static LoginService LsInstance = new LoginService();

    public static LoginService getInstance() {
        return LsInstance;
    }

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
