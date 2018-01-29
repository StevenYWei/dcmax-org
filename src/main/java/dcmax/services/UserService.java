package dcmax.services;

import dcmax.models.User;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    User findByUsername(String username);

    boolean usernameExists(String username);

    User create(User user);

    User edit(User user);

    void deleteById(Long id);

    User update(User user) throws InvocationTargetException, IllegalAccessException;

    boolean authenticate(String username, String password);

    boolean twoPasswordMatch(String password, String rePassword);

    int deleteUser(String username);

    boolean isAdmin();

    User currentUser();
}