package com.dreamlink.calendar.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

@Mapper
public interface CalendarMapper {

    public int insertCalendar(
            @Param("title") String title,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
