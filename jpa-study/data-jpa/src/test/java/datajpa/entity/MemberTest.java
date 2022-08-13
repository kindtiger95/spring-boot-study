package datajpa.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
class MemberTest {

    @Autowired
    EntityManager entityManager;

    @Test
    @Transactional
    public void testEntity() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        this.entityManager.persist(teamA);
        this.entityManager.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);

        this.entityManager.persist(member1);
        this.entityManager.persist(member2);
        this.entityManager.persist(member3);
        this.entityManager.persist(member4);

        this.entityManager.flush();
        this.entityManager.clear();

        List<Member> members = this.entityManager.createQuery("SELECT m FROM Member AS m", Member.class)
                                                 .getResultList();

        members.forEach(member -> {
            System.out.println("member = " + member);
            System.out.println("-> member.team=" + member.getTeam());
        });
    }

}