package dcmax.services;

import dcmax.models.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    User findByUsername(String username);

    boolean usernameExists(String username);

    User create(User user);

    User edit(User user);

    void deleteById(Long id);

    void update(User user);

    boolean authenticate(String username, String password);

    boolean twoPasswordMatch(String password, String rePassword);

    int deleteUser(String username);
}