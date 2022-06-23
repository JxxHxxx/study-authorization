package study.authorization.domain.member;

import java.util.HashMap;
import java.util.Map;

import java.util.List;

public class MemberRepository {

    private final Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    private static MemberRepository MrInstance = new MemberRepository();

    private MemberRepository() {
    }

    public static MemberRepository getInstance() {
        return MrInstance;
    }


    public Member save(Member member) {
        store.put(++sequence, member);
        return member;
    }

    public Member findById(Long memberId) {
        return store.get(memberId);
    }

    public Member findByName(String memberName) {
        return store.values().stream()
                .filter(member -> isMatched(member, memberName))
                .findFirst().orElse(null);
    }

    private boolean isMatched(Member member, String memberName) {
        return member.getName().equals(memberName);
    }

    public List<Member> findAll() {
        return store.values().stream().toList();
    }

    public void clear() {
        store.clear();
        sequence = 0L;
    }
}
