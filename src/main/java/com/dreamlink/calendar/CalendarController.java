package com.dreamlink.calendar;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalendarController {

    @GetMapping("/calendar")
    public String calendar(
            HttpSession session) {

        if (session.getAttribute("userId") == null) {
            return "redirect:/user/sign-in";
        }


        return "calendar";
    }
}
