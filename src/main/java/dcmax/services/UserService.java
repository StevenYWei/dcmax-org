package dcmax.services;

import dcmax.models.User;

import java.util.List;

public interface UserService
{

    List<User> findAll();

    User findById(Long id);

    User findByUsername(String username);

    User create(User user);

    User edit(User user);

    void deleteById(Long id);

    void registerUser(User user);

    boolean authenticate(String username, String password);

    boolean twoPasswordMatch(String password, String rePassword);

}