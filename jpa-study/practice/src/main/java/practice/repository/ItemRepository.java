package practice.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import practice.domain.Book;
import practice.domain.Item;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;

    public Long save(Book book) {
        this.em.persist(book);
        return book.getId();
    }

    public Item findOne(Long id) {
        return this.em.find(Item.class, id);
    }

    public List<Item> findAll() {
        String query = "SELECT i FROM Item i";
        return this.em.createQuery(query, Item.class).getResultList();
    }
}
