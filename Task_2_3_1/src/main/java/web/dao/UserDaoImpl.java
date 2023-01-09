package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    public List<User> getAllUsers() {
        String jpql = "SELECT c FROM User c";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        return query.getResultList();
    }

    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }

    public void update(int id, User user) {
        User user1 = entityManager.find(User.class, id);
        user1 = entityManager.merge(user);
    }

    public void delete(int id) {
        entityManager.remove(entityManager.find(User.class,id));
    }
}
