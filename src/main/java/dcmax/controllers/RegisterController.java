package dcmax.controllers;

import dcmax.forms.RegisterForm;
import dcmax.models.User;
import dcmax.services.NotificationService;
import dcmax.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notifyService;

    @Autowired
    private Validator userValidator;

    @RequestMapping("/users/register")
    public String register(RegisterForm registerForm) {
        return "users/register";
    }

    @RequestMapping(value = "/users/register", method = RequestMethod.POST)
    public String registerPage(@Valid RegisterForm registerForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill the form correctly!");
            return "users/register";
        }

        if (!userService.twoPasswordMatch(registerForm.getPassword(), registerForm.getRePassword())) {
            notifyService.addErrorMessage("Passwords don't match!");
            return "users/register";
        }

        User user = new User();
        user.setUsername(StringUtils.trimWhitespace(registerForm.getUsername()));
        user.setPassword(StringUtils.trimWhitespace(registerForm.getPassword()));
        user.setEmail(StringUtils.trimWhitespace(registerForm.getEmail()));
        user.setCreateTime(new Date());
        user.setLastUpdateTime(new Date());

        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("User already exists or registered!");
            return "users/register";
        }

        try {
            userService.registerUser(user);
        } catch (Exception e) {
            notifyService.addErrorMessage("Error in registeration!");
            return "users/register";
        }

        notifyService.addInfoMessage("Register successful");
        return "redirect:/";
    }

//    @RequestMapping(value = "/users/register", method = RequestMethod.POST)
//    public String registerUser(/*@Validated({User.CreateValidationGroup.class}) @ModelAttribute(value = "user") */User user, BindingResult bindingResult, HttpSession session)
//    {
//
//        if (bindingResult.hasErrors())
//        {
//            return "users/register";
//        }
//
//        userService.register(user);
//
//        return "users/login";
//        userService.authenticate(user);
//
//        Object regRef = session.getAttribute("regRef");
//
//        return "redirect:" + (StringUtils.isEmpty(regRef) ? "posts" : regRef.toString());
//    }
}
