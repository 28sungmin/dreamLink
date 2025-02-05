package com.dreamlink.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@Controller
public class UserController {

    @GetMapping("/sign-up/preferences")
    public String signInPreferences() {
        return "/user/signUp/preferences";
    }

    @GetMapping("/sign-up/info")
    public String signUpInfo() {
        return "/user/signUp/info";
    }

    @GetMapping("/sign-in")
    public String signIn() {
        return "/user/signIn";
    }
}
