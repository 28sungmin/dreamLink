package com.dreamlink.calendar;

import com.dreamlink.calendar.bo.CalendarBO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/calendar")
public class CalendarRestController {

    private final CalendarBO calendarBO;

    @PostMapping("/add")
    public Map<String, Object> add(
            @RequestParam("title") String title,
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate
            ) {

        boolean isAddOkay = calendarBO.addCalendar(title, startDate, endDate);

        Map<String, Object> result = new HashMap<>();
        if (isAddOkay) {
            result.put("code", 200);
            result.put("result", "성공");
        } else {
            result.put("code", 500);
            result.put("error_message", "일정 추가에 실패했습니다.");
        }

        return result;
    }
}
