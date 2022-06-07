package web.service;

import org.springframework.stereotype.Component;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class UserService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public User save (User user) {
        em.persist(user);
        return user;
    }

    public User find(long id) {
        return em.find(User.class, id);
    }

    public List<User> getAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> rootEntry = cq.from(User.class);
        CriteriaQuery<User> all = cq.select(rootEntry);
        TypedQuery<User> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    @Transactional
    public User update (User user) {
        em.merge(user);
        return user;
    }

    @Transactional
    public User delete (User user) {
        if (em.contains(user)) {
            em.remove(user);
        } else {
            em.remove(em.merge(user));
        }
        return user;
    }

}