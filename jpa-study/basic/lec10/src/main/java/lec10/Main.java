package lec10;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lec10");
        InsertMockingData insertMockingData = new InsertMockingData(emf);
        insertMockingData.insertMocking();
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            /** JQPL로 조건별 조회하기 **/
            /*
            String query1 = "SELECT m from Member as m WHERE m.name =: username";
            List<Member> queryRet = em.createQuery(query1, Member.class)
                    .setParameter("username", "user20").getResultList();

            Optional<Member> getUser = queryRet.stream().findAny();
            Member member = getUser.orElseGet(Member::new);
            System.out.println("member.getName() = " + member.getName());
             */

            /** JPQL로 특정 값만 뽑아오기(new 방식) **/
            /*
           String query2 = "SELECT new lec10.ManyArgDto(m.name, m.city) FROM Member as m";
            List<ManyArgDto> resultList = em.createQuery(query2, ManyArgDto.class).getResultList();
            resultList.forEach((manyArgDto) -> {
                System.out.println("manyArgDto.getCity() = " + manyArgDto.getCity());
                System.out.println("manyArgDto.getUsername() = " + manyArgDto.getUsername());
            });
            */

            /** 페이징 **/
            /*
            String query3 = "SELECT m FROM Member m ORDER BY m.name ASC";
            List<Member> resultList = em.createQuery(query3, Member.class)
                    .setFirstResult(9)
                    .setMaxResults(10)
                    .getResultList();
            resultList.forEach((member) -> {
                System.out.println("member.getName() = " + member.getName());
            });
            */

            /** 조인 **/
            /*
            String query4 = "SELECT m FROM Member as m INNER JOIN m.orders as o";
            List<Member> resultList = em.createQuery(query4, Member.class).getResultList();
            resultList.forEach((member) -> {
                System.out.println("member.getName() = " + member.getName());
                System.out.println("member.getOrders().get(0).toString() = " + member.getOrders().get(0).toString());
            });
            */

            tx.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
