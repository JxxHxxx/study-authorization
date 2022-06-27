package study.authorization.domain.member;

import lombok.Getter;
import lombok.Setter;
import study.authorization.domain.item.Item;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Member {

    private Long id;

    private String name;
    private String password;

    private List<Item> items = new ArrayList<>();

    private Long wallet;

    //중복 아님


    public Member() {
    }

    public Member(Long id, String name, String password, Long wallet) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.wallet = wallet;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }
}
