import javax.persistence.*;
import java.util.List;

public class JpaMain {
    @SuppressWarnings("JpaQlInspection")
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.changeTeam(team);
            em.persist(member);

            System.out.println(member);
            em.flush();
            em.clear();

            Integer singleResult = em.createQuery("select t.members.size from Team t", Integer.class)
                    .getSingleResult();

            System.out.println(singleResult);


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }
}
