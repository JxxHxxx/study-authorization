package study.authorization.domain.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import study.authorization.domain.member.Member;
import study.authorization.domain.member.MemberRepository;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @GetMapping("/{memberId}/items")
    public String items(@PathVariable(name = "memberId") Long memberId, Model model) {
        Member member = memberRepository.findById(memberId);
        List<Item> items = member.getItems();

        model.addAttribute("items", items);
        return "/main/items";
    }

    @GetMapping("/item/{itemId}")
    public String itemOne(@PathVariable(name ="itemId") Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);

        model.addAttribute("item",item);
        return "main/item";
    }
}
