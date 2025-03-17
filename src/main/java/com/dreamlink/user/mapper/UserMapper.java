package com.dreamlink.user.mapper;

import com.dreamlink.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface UserMapper {

    public List<String> selectInterestList();

    public String selectUserByLoginId(String loginId);

    public User selectUserByLoginIdPassword(
            @Param("loginId") String loginId,
            @Param("password") String password);

    public int insertUser(
            @Param("gender") String gender,
            @Param("birth") LocalDate birth,
            @Param("region") String region,
            @Param("interest") String interest,
            @Param("name") String name,
            @Param("phone") String phone,
            @Param("loginId") String loginId,
            @Param("password") String password);
}
