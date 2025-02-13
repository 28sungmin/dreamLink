package com.dreamlink.user;

import com.dreamlink.user.bo.UserBO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {

    private final UserBO userBO;

    @GetMapping("/sign-up/preferences")
    public String signInPreferences(Model model) {
        List<String> interestList = userBO.getInterestList();

        model.addAttribute("interestList", interestList);

        return "user/signUp/preferences";
    }

    @GetMapping("/sign-up/info")
    public String signUpInfo() {
        return "user/signUp/info";
    }

    @GetMapping("/sign-in")
    public String signIn() {
        return "user/signIn";
    }

    @GetMapping("/sign-out")
    public String signOut(HttpSession session) {
        // session을 비운다.
        session.removeAttribute("userId");
        session.removeAttribute("userLoginId");
        session.removeAttribute("userName");

        // 화면으로 이동한다.(redirect)
        return "redirect:/home";
    }
}
