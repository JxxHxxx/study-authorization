package study.authorization;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import study.authorization.domain.item.Item;
import study.authorization.domain.item.ItemRepository;
import study.authorization.domain.member.Member;
import study.authorization.domain.member.MemberRepository;

import javax.annotation.PostConstruct;

@Controller
@RequiredArgsConstructor
public class TestInit {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @PostConstruct
    public void init() {
        Member member = new Member(0L, "memberA","1234", 10000L);
        Item itemA = new Item(0L,"itemA", 2500L);
        Item itemB = new Item(1L,"itemB", 5000L);

        member.addItem(itemA);
        member.addItem(itemB);

        memberRepository.save(member);

        Member member2 = new Member(1L, "memberB", "1234",20000L);
        Item itemC = new Item(3L,"itemC", 5000L);

        member2.addItem(itemC);
        memberRepository.save(member2);

        itemRepository.save(itemA);
        itemRepository.save(itemB);
        itemRepository.save(itemC);
    }
}
