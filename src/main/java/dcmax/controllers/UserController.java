package dcmax.controllers;

import dcmax.models.User;
import dcmax.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static dcmax.utils.constants.UserConstants.FAIL_MSG;
import static dcmax.utils.constants.UserConstants.SUCCESS_MSG;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Validator userValidator;

    @PostMapping(value = "/register")
    public User registerNewUser(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping(value = "/getUser/{username}")
    public User getUser(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @GetMapping(value = "/deleteUser/{username}")
    public String deleteUser(@PathVariable String username) {
        if(userService.deleteUser(username) == 1) {
            return FAIL_MSG;
        }
        return SUCCESS_MSG;
    }

    @PostMapping(value = "/update")
    public User updateUserInfo(@RequestBody User user)
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return userService.update(user);
    }

    @GetMapping(value = "/getUserList")
    public List<String> getUsers() {
        return userService.getUserList();
    }

    @GetMapping(value = "/ping")
    public String userPing() {
        return "Success! User service is up and running!";
    }


}
