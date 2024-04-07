package gr.di.mystudiesplanservice.user;

import gr.di.mystudiesplanservice.Dao;

import java.util.List;

public interface UserDao extends Dao<User, Long> {
    User findByEmailAndPassword(String email, String password);

    User findUserByEmail(String email);

    User findUserByUsername(String Username);

    boolean findByEmail(String email);

    List<User> findAllUsers();
}
