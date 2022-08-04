package springjpapractice1.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springjpapractice1.domain.Book;
import springjpapractice1.domain.Item;
import springjpapractice1.repository.ItemRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public void saveItem(Book book) {
        this.itemRepository.save(book);
    }

    public List<Item> findItems() {
        return this.itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return this.itemRepository.findOne(itemId);
    }

    public void updateItem(Long id, String name, int price, int quantity) {
        Item item = this.itemRepository.findOne(id);
        item.setName(name);
        item.setPrice(price);
        item.setStockQuantity(quantity);
    }
}
