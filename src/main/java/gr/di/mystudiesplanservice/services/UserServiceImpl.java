package gr.di.mystudiesplanservice.services;

import gr.di.mystudiesplanservice.user.User;
import gr.di.mystudiesplanservice.user.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    private final UserDaoImpl userDaoImpl;

    @Autowired
    public UserServiceImpl(UserDaoImpl userRepository) {
        this.userDaoImpl = userRepository;
    }

    @Override
    public User findUserByEmail(String email) {

        return this.userDaoImpl.findUserByEmail(email);

    }
}
