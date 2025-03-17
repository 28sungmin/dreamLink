package com.dreamlink.calendar.mapper;

import com.dreamlink.calendar.domain.Calendar;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface CalendarMapper {

    public List<Calendar> selectCalendarByUserId(int userId);

    public int insertCalendar(
            @Param("userId") int userId,
            @Param("title") String title,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
