package com.dreamlink.calendar;

import com.dreamlink.calendar.bo.CalendarBO;
import com.dreamlink.calendar.domain.Calendar;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CalendarController {

    private final CalendarBO calendarBO;

    @GetMapping("/calendar")
    public String calendar(
            HttpSession session,
            Model model) {

        if (session.getAttribute("userId") == null) {
            return "redirect:/user/sign-in";
        }


        return "calendar";
    }
}
