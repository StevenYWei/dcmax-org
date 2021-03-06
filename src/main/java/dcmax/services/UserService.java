package dcmax.services;

import dcmax.forms.LoginForm;
import dcmax.models.User;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface UserService {

    List<User> findAll();

    List<String> getUserList();

    User findById(Long id);

    User findByUsername(String username);

    boolean usernameExists(String username);

    User findByEmail(String email);

    boolean userEmailExists(String email);

    User create(User user);

    User register(User user);

    User edit(User user);

    void deleteById(Long id);

    User update(User user) throws InvocationTargetException, IllegalAccessException;

    boolean authenticate(LoginForm loginForm);

    void authenticate(User user);

    boolean isAuthenticated();

//    boolean twoPasswordMatch(String password, String passwordConfirmation); // please implement this on the front end

    int deleteUser(String username);

    boolean isAdmin();

    boolean isEventOrganizer();

    User currentUser();
}