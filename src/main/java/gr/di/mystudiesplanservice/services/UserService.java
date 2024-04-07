package gr.di.mystudiesplanservice.services;


import gr.di.mystudiesplanservice.user.User;

public interface UserService {

    public User findUserByEmail(String email);

//    public User getAuthenticatedUser();

}
