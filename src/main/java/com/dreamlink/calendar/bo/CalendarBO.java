package com.dreamlink.calendar.bo;

import com.dreamlink.calendar.domain.Calendar;
import com.dreamlink.calendar.mapper.CalendarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CalendarBO {

    private final CalendarMapper calendarMapper;

    public List<Calendar> getCalendarByUserId(int userId) {
        return calendarMapper.selectCalendarByUserId(userId);
    }

    // true: 삽입 성공
    public boolean addCalendar(int userId, String title, LocalDate startDate, LocalDate endDate) {

        int rowCount = calendarMapper.insertCalendar(userId, title, startDate, endDate);

        if (rowCount > 0) {
            return true;
        }
        return false;
    }

}
