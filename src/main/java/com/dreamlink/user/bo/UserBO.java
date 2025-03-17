package com.dreamlink.user.bo;

import com.dreamlink.user.domain.User;
import com.dreamlink.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserBO {

    private final UserMapper userMapper;

    public List<String> getInterestList() {
        return userMapper.selectInterestList();
    }

    // true: 중복됨
    public boolean getUserByLoginId(String loginId) {
        String isDuplicate = userMapper.selectUserByLoginId(loginId);

        if (isDuplicate != null && isDuplicate.equals(loginId)) {
            return true;
        }
        return false;
    }

    public User getUserByLoginIdPassword(String loginId, String password) {
        return userMapper.selectUserByLoginIdPassword(loginId, password);
    }

    public boolean addUser(String gender, LocalDate birth, String region, String interest,
                           String name, String phone, String loginId, String password) {

        int rowCount = userMapper.insertUser(gender, birth, region, interest, name, phone, loginId, password);

        return rowCount > 0;
    }

}
