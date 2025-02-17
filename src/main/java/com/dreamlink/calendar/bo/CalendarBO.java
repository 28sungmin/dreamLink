package com.dreamlink.calendar.bo;

import com.dreamlink.calendar.mapper.CalendarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class CalendarBO {

    private final CalendarMapper calendarMapper;

    // true: 삽입 성공
    public boolean addCalendar(String title, LocalDate startDate, LocalDate endDate) {

        int rowCount = calendarMapper.insertCalendar(title, startDate, endDate);

        if (rowCount > 0) {
            return true;
        }
        return false;
    }

}
