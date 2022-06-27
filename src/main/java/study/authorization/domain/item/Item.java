package study.authorization.domain.item;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Item {


    private Long id;

    private String name;
    private Long price;

    public Item() {
    }

    public Item(Long id, String name, Long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
