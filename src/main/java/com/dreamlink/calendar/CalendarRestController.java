package com.dreamlink.calendar;

import com.dreamlink.calendar.bo.CalendarBO;
import com.dreamlink.calendar.domain.Calendar;
import com.dreamlink.user.domain.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/calendar")
public class CalendarRestController {

    private final CalendarBO calendarBO;

    @GetMapping("/show")
    public Map<String, Object> show(HttpSession session) {
        int userId = (int) session.getAttribute("userId");
        List<Calendar> calendarList = calendarBO.getCalendarByUserId(userId);

        Map<String, Object> result = new HashMap<>();
        if (calendarList != null) {
            // FullCalendar에 맞게 이벤트 리스트 변환
            List<Map<String, Object>> events = new ArrayList<>();
            for (Calendar calendar : calendarList) {
                Map<String, Object> event = new HashMap<>();
                event.put("title", calendar.getTitle());
                event.put("start", calendar.getStartDate().toString());  // 날짜 형식 'yyyy-MM-dd'
                event.put("end", calendar.getEndDate().toString());      // 날짜 형식 'yyyy-MM-dd'
                events.add(event);
            }

            result.put("code", 200);
            result.put("result", "성공");
            result.put("event", events);  // 변환된 event 리스트 반환
        } else {
            result.put("code", 500);
            result.put("error_message", "일정을 불러오는데 실패했습니다.");
        }

        return result;
    }

    @PostMapping("/add")
    public Map<String, Object> add(
            HttpSession session,
            @RequestParam("title") String title,
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate
            ) {

        int userId = (int) session.getAttribute("userId");
        boolean isAddOkay = calendarBO.addCalendar(userId, title, startDate, endDate);

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
