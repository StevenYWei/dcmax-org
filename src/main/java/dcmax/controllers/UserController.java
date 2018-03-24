package dcmax.controllers;

import dcmax.forms.RegisterForm;
import dcmax.models.User;
import dcmax.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static dcmax.utils.constants.UserConstants.FAIL_MSG;
import static dcmax.utils.constants.UserConstants.SUCCESS_MSG;

@CrossOrigin
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping(value = "/register")
    public String registerNewUser(@RequestBody RegisterForm registerForm) {
        if(!registerForm.getPassword().equals(registerForm.getPasswordConfirmation()))
            return FAIL_MSG + ": the two passwords do not match";
        else if(userService.usernameExists(registerForm.getUsername()))
            return FAIL_MSG + ": this username already exists";
        else if(userService.userEmailExists(registerForm.getEmail()))
            return FAIL_MSG + ": this email already exists";

        //Checking for non alphanumerical characters in the username.
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        if(pattern.matcher(registerForm.getUsername()).find())
            return FAIL_MSG + ": No special characters are allowed in the username";

        userService.create(new User(registerForm.getUsername(), registerForm.getPassword(), registerForm.getEmail(),true));
        return SUCCESS_MSG + ": User created";
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
