package springjpapractice1.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springjpapractice1.domain.entities.Item;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null)
            this.em.persist(item);
        else
            this.em.merge(item);
    }

    public Item findOne(Long id) {
        return this.em.find(Item.class, id);
    }

    public List<Item> findAll() {
        String query = "SELECT i FROM Item AS i";
        return this.em.createQuery(query, Item.class).getResultList();
    }
}
