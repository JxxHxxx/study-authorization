package study.authorization.domain.item;



import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Repository
public class ItemRepository {

    private final Map<Long, Item> store = new HashMap<>();
    private static Long sequence = 0L;

    public Item save(Item item) {
        store.put(sequence++, item);
        return item;
    }

    public Item findById(Long itemId) {
        return store.get(itemId);
    }

    public List<Item> findAll() {
        return store.values().stream().toList();
    }

    public void clear() {
        store.clear();
    }
}
