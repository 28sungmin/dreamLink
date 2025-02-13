package com.dreamlink.user;

import com.dreamlink.user.bo.UserBO;
import com.dreamlink.user.domain.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserRestController {

    private final UserBO userBO;

    /**
     * preferences 회원가입 API
     * @param session
     * @param gender
     * @param year
     * @param month
     * @param day
     * @param bigRegion
     * @param smallRegion
     * @param interest
     * @return
     */
    @PostMapping("/sign-up/preferences")
    public Map<String, Object> signUpPreferences(
            HttpSession session,
            @RequestParam("gender") String gender,
            @RequestParam("year") int year,
            @RequestParam("month") int month,
            @RequestParam("day") int day,
            @RequestParam("bigRegion") String bigRegion,
            @RequestParam("smallRegion") String smallRegion,
            @RequestParam("interest") List<String> interest) {

        LocalDate birth = LocalDate.of(year, month, day);

        // region
        String region = String.join(",", bigRegion, smallRegion);

        // interest
        String interestStr = String.join(",", interest);

        session.setAttribute("gender", gender);
        session.setAttribute("birth", birth);
        session.setAttribute("region", region);
        session.setAttribute("interest", interestStr);

        Map<String, Object> result = new HashMap<>();

        result.put("code", 200);
        result.put("result", "성공");

        return result;

    }

    /**
     * info 회원가입 API
     * @param session
     * @param name
     * @param phone
     * @param loginId
     * @param password
     * @return
     */
    @PostMapping("/sign-up/info")
    public Map<String, Object> signUpInfo(
            HttpSession session,
            @RequestParam("name") String name,
            @RequestParam("phone") String phone,
            @RequestParam("loginId") String loginId,
            @RequestParam("password") String password
    ) {
        // session
        String gender = (String) session.getAttribute("gender");
        LocalDate birth = (LocalDate) session.getAttribute("birth");
        String region = (String) session.getAttribute("region");
        String interest = (String) session.getAttribute("interest");

        // db insert
        boolean isSuccess = userBO.addUser(gender, birth, region, interest, name, phone, loginId, password);

        Map<String, Object> result = new HashMap<>();

        if (isSuccess) {
            result.put("code", 200);
            result.put("result", "성공");
        } else {
            result.put("code", 500);
            result.put("error_message", "회원가입에 실패했습니다.");
        }

        return result;
    }

    /**
     * 로그인 아이디 중복 확인
     * @param loginId
     * @return
     */
    @PostMapping("/is-duplicate-id")
    public Map<String, Object> isDuplicateId(
            @RequestParam("loginId") String loginId
    ) {

        boolean isDuplicate = userBO.getUserByLoginId(loginId);

        Map<String, Object> result = new HashMap<>();

        result.put("code", 200);
        result.put("is_duplicate_id", isDuplicate);

        return result;
    }

    /**
     * 로그인 API
     * @param session
     * @param loginId
     * @param password
     * @return
     */
    @PostMapping("/sign-in")
    public Map<String, Object> signIn(
            HttpSession session,
            @RequestParam("loginId") String loginId,
            @RequestParam("password") String password
    ) {

        // DB select
        User user = userBO.getUserByLoginIdPassword(loginId, password);

        // 응답값
        Map<String, Object> result = new HashMap<>();

        if (user != null) {
            // 로그인 성공 시 서버에 세션 주머니를 만든다.(브라우저 마다 만드는 것!!)
            session.setAttribute("userId", user.getId());
            session.setAttribute("userLoginId", user.getLoginId());
            session.setAttribute("userName", user.getName());

            result.put("code", 200);
            result.put("result", "성공");
        } else {
            result.put("code", 300);
            result.put("error_message", "존재하지 않는 사용자입니다.");
        }
        return result;
    }
}
