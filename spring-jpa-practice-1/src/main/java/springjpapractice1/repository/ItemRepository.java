package springjpapractice1.repository;

import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springjpapractice1.domain.Book;
import springjpapractice1.domain.Item;

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
