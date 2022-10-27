package practice.querydsl;

import static practice.querydsl.entity.QMember.member;
import static practice.querydsl.entity.QTeam.team;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import practice.querydsl.dto.MemberDto;
import practice.querydsl.dto.QMemberDto;
import practice.querydsl.dto.QTeamDto;
import practice.querydsl.dto.TeamDto;
import practice.querydsl.entity.Member;
import practice.querydsl.entity.Team;

@SpringBootTest
@Transactional
public class BasicTest {
    @PersistenceContext
    EntityManager em;

    JPAQueryFactory jpaQueryFactory;

    @BeforeEach
    public void before() {
        jpaQueryFactory = new JPAQueryFactory(em);

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
        System.out.println("=================================================================================================================================");
        System.out.println("=================================================================================================================================");
    }

    @Test
    public void startJPQL() {
        String jpql = "SELECT m FROM Member AS m WHERE m.username = :username";
        Member member1 = em.createQuery(jpql, Member.class)
                           .setParameter("username", "member1")
                           .getSingleResult();
        Assertions.assertThat(member1.getUsername()).isEqualTo("member1");
    }

    @Test
    public void startQuerydsl() {
        Member member1 = jpaQueryFactory.select(member)
                                        .from(member)
                                        .where(member.username.eq("member1"))
                                        .fetchOne();

        Assertions.assertThat(member1.getUsername()).isEqualTo("member1");
    }

    @Test
    public void search() {
        Member member1 = jpaQueryFactory.selectFrom(member)
                                        .where(member.username.eq("member1")
                                                              .and(member.age.eq(10)))
                                        .fetchOne();

        Assertions.assertThat(member1.getUsername()).isEqualTo("member1");
    }

    @Test
    public void searchAndParam() {
        Member member1 = jpaQueryFactory.selectFrom(member)
                                        .where(member.username.eq("member1"),
                                            member.age.eq(10))
                                        .fetchOne();

        Assertions.assertThat(member1.getUsername()).isEqualTo("member1");
    }

    @Test
    public void searchVariable() {
        List<Member> fetch = jpaQueryFactory.selectFrom(member)
                                            .fetch();

        Member member2 = jpaQueryFactory.selectFrom(member)
                                        .fetchFirst();
    }

    @Test
    public void sorting() {
        em.persist(new Member(null, 100));
        em.persist(new Member("member5", 100));
        em.persist(new Member("member6", 100));

        List<Member> result = jpaQueryFactory.selectFrom(member)
                                            .where(member.age.eq(100))
                                            .orderBy(member.age.desc(), member.username.asc()
                                                                                       .nullsLast())
                                            .fetch();
        Member member5 = result.get(0);
        Member member6 = result.get(1);
        Member memberNull = result.get(2);
        Assertions.assertThat(member5.getUsername()).isEqualTo("member5");
        Assertions.assertThat(member6.getUsername()).isEqualTo("member6");
        Assertions.assertThat(memberNull.getUsername()).isNull();
    }

    @Test
    public void paging() {
        List<Member> result = jpaQueryFactory.selectFrom(member)
                                             .orderBy(member.username.desc())
                                             .offset(1)
                                             .limit(2)
                                             .fetch();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void basicJoin() {
        List<Member> teamA = jpaQueryFactory.selectFrom(member)
                                            .join(member.team, team)
                                            .where(team.name.eq("teamA"))
                                            .fetch();
        Assertions.assertThat(teamA)
                  .extracting("username")
                  .containsExactly("member1", "member2");
    }

    @Test
    public void basicJoin2() {
        List<Tuple> fetch = jpaQueryFactory.select(member, team)
                                           .from(member)
                                           .join(member.team, team)
                                           .fetch();
        for (Tuple tuple : fetch) {
            System.out.println(tuple);
        }
    }

    @Test
    public void noForeignKeyJoin() {
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));

        List<Tuple> fetch = jpaQueryFactory.select(member, team)
                                           .from(member)
                                           .leftJoin(team)
                                           .on(member.username.eq(team.name))
                                           .fetch();

        for (Tuple tuple : fetch) {
            System.out.println("tuple = " + tuple);
        }
    }

    @Test
    public void fetchJoin() {
        List<Member> member1 = jpaQueryFactory.selectFrom(member)
                                              .join(member.team, team)
                                              .fetchJoin()
                                              .where(member.username.eq("member1"))
                                              .fetch();
        Assertions.assertThat(member1)
                  .extracting("username")
                  .containsExactly("member1");
    }

    @Test
    public void test1() {
        List<MemberDto> fetch = jpaQueryFactory.select(new QMemberDto(member.username, member.age))
                                               .from(member)
                                               .fetch();
    }

    @Test
    public void 동적쿼리_basic() {
        String usernameParam = "member1";
        Integer ageParam = 10;

        List<Member> members = searchMember1(usernameParam, ageParam);
        Assertions.assertThat(members.size()).isEqualTo(1);
    }

    @Test
    public void in_query_테스트() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);
        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        List<TeamDto> teamDtos = jpaQueryFactory.select(new QTeamDto(team.id, team.name))
                                             .from(team)
                                             .fetch();

        List<Long> teamIdList = teamDtos.stream()
                                        .map(TeamDto::getTeamId)
                                        .collect(Collectors.toList());

        List<MemberDto> memberDtos = jpaQueryFactory.select(new QMemberDto(member.username, member.age))
                                                    .from(member)
                                                    .where(member.team.id.in(teamIdList))
                                                    .fetch();
        

    }

    private List<Member> searchMember1(String usernameCond, Integer ageCond) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (usernameCond != null) booleanBuilder.and(member.username.eq(usernameCond));
        if (ageCond != null) booleanBuilder.and(member.age.eq(ageCond));
        return jpaQueryFactory.selectFrom(member)
                              .where(booleanBuilder)
                              .fetch();
    }

    private List<Member> searchMember2(String usernameCond, Integer ageCond) {
        return jpaQueryFactory.selectFrom(member)
                              .where(usernameEq(usernameCond), ageEq(ageCond))
                              .fetch();
    }

    private BooleanExpression usernameEq(String usernameCond) {
        return StringUtils.hasText(usernameCond) ? member.username.eq(usernameCond) : null;
    }

    private BooleanExpression ageEq(Integer ageCond) {
        return ageCond != null ? member.age.eq(ageCond) : null;
    }
}
