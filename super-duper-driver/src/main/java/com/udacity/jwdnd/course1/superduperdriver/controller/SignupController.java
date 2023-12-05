package com.udacity.jwdnd.course1.superduperdriver.controller;

import com.udacity.jwdnd.course1.superduperdriver.common.Constants;
import com.udacity.jwdnd.course1.superduperdriver.model.entities.User;
import com.udacity.jwdnd.course1.superduperdriver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Objects;

@Controller
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    UserService userService;

    @GetMapping()
    public String signupView() {
        return "signup";
    }

    @PostMapping()
    public String signupUser(@ModelAttribute User user, Model model, RedirectAttributes redirectAttrs, Principal principal) {
        if (Objects.nonNull(principal)) {
            return Constants.REDIRECT_HOME;
        }

        String signupError = null;

        if (!userService.isUsernameAvailable(user.getUsername())) {
            signupError = "The username already exists.";
        }

        if (Objects.isNull(signupError)) {
            int rowsAdded = userService.createUser(user);
            if (rowsAdded < 0) {
                signupError = "There was an error signing you up. Please try again.";
            }
        }

        if (signupError == null) {
            redirectAttrs.addFlashAttribute("signupSuccess", true);
            return Constants.REDIRECT_LOGIN;
        } else {
            model.addAttribute("signupError", signupError);
            return "signup";
        }
    }
}
