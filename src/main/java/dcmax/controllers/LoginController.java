package dcmax.controllers;

import dcmax.forms.LoginForm;
import dcmax.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static dcmax.utils.constants.CommonConstants.FAIL_MSG;
import static dcmax.utils.constants.CommonConstants.SUCCESS_MSG;

@CrossOrigin
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/users/login")
    public String userLogin(@RequestBody @Valid LoginForm loginForm) {
        return userService.authenticate (loginForm) ? SUCCESS_MSG : FAIL_MSG;
    }

}
