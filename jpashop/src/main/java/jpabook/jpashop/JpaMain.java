package jpabook.jpashop;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            /**
             * 현재 도메인 클래스들의 문제점
             * 객체 셜계를 테이블 설계에 맞춘 방식
             * 테이블의 외래키를 그대로 가져옴 ex) memberId
             * 객체 그래프 탐색이 불가능
             * order.getMemberId(); 를해서 또 해당 멤버를 찾아야함
             * 참조가 없으므로 UML도 잘못됨
             */

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
