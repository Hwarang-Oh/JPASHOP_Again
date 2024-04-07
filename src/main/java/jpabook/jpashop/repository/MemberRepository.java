package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Component Scan의 대상 -> Spring Bean에 등록되어 있음
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) { // find( type, pk )
        return em.find(Member.class, id);
    }

    public List<Member> findAll() { // JPQL이 들어가 있음 -> from의 대상이 table이 아닌 Entity 객체
        return em.createQuery("select m from Member  m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name) // parameter setting with ":name"
                .getResultList();
    }
}
