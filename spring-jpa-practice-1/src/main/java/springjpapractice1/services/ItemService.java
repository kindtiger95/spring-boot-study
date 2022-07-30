package springjpapractice1.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springjpapractice1.domain.entities.Item;
import springjpapractice1.repositories.ItemRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {
    private final EntityManager em;
    private final ItemRepository itemRepository;

    public void saveItem(Item item) {
        this.itemRepository.save(item);
    }

    public List<Item> findItems() {
        return this.itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return this.itemRepository.findOne(itemId);
    }
}
