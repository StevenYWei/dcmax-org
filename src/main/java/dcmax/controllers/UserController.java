package dcmax.controllers;

import dcmax.forms.RegisterForm;
import dcmax.models.Role;
import dcmax.models.User;
import dcmax.services.NotificationService;
import dcmax.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Validator userValidator;

    @PostMapping(value = "/register")
    public String registerNewUser(@RequestBody User user) {
        userService.create(user);
        return "success";
    }

    @GetMapping(value = "/getUser/{username}")
    public User getUser(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @GetMapping(value = "/deleteUser/{username}")
    public String deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return "success";
    }

    @PostMapping(value = "/update")
    public String updateUserInfo(@RequestBody User user) {
        userService.update(user);
        return "success";
    }


    @GetMapping(value = "/ping")
    public String userPing() {
        return "Success! User serive is up and running!";
    }


}
