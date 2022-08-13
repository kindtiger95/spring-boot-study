package practice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.domain.Book;
import practice.domain.Item;
import practice.repository.ItemRepository;

import java.util.List;

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
