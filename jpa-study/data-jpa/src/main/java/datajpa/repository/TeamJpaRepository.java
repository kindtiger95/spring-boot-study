package datajpa.repository;

import datajpa.entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TeamJpaRepository {
    private final EntityManager entityManager;

    public Team save(Team team) {
        this.entityManager.persist(team);
        return team;
    }

    public void delete(Team team) {
        this.entityManager.remove(team);
    }

    public List<Team> findAll() {
        String query = "SELECT t FROM Team AS t";
        return this.entityManager.createQuery(query, Team.class)
                                 .getResultList();
    }

    public Optional<Team> findById(Long id) {
        Team team = this.entityManager.find(Team.class, id);
        return Optional.ofNullable(team);
    }

    public long count() {
        String query = "SELECT COUNT(t) FROM Team AS t";
        return this.entityManager.createQuery(query, Long.class)
                                 .getSingleResult();
    }
}
