package dcmax.controllers;

import dcmax.forms.LoginForm;
import dcmax.models.User;
import dcmax.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static dcmax.utils.constants.CommonConstants.FAIL_MSG;
import static dcmax.utils.constants.CommonConstants.SUCCESS_MSG;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/users/login")
    public String userLogin(@RequestBody @Valid LoginForm loginForm) {
        return userService.authenticate(loginForm) ? SUCCESS_MSG : FAIL_MSG;
    }

}
