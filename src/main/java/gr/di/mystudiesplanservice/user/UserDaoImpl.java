package gr.di.mystudiesplanservice.user;

import gr.di.mystudiesplanservice.JpaDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends JpaDao<User, Long> implements UserDao {

    public boolean findByEmail(String email) {
        return entityManager.createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList()
                .isEmpty();
    }

    public User findByEmailAndPassword(String email, String password) {
        List<User> users;
        users = entityManager.createQuery("select u from User u where u.email =:email and u.password =:password", User.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getResultList();

        if (users == null || users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    public User findUserByEmail(String email) {
        List<User> users = entityManager.createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList();

        if (users == null || users.isEmpty()) {
            return null;
        }

        return users.get(0);
    }

    @Override
    public User findUserByUsername(String username) {
        List<User> users = entityManager.createQuery("select u from User u where u.username = :username", User.class)
                .setParameter("username", username).getResultList();
        if (users == null || users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    public List<User> findAllUsers() {
        return entityManager.createQuery("select u from User u", User.class)
                .getResultList();
    }
}
