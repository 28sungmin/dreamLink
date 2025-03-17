package com.dreamlink.welfare;

import com.dreamlink.welfare.bo.WelfareBO;
import com.dreamlink.welfare.domain.Welfare;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class WelfareRestController {

    private final WelfareBO welfareBO;

    /**
     * service의 세부 메뉴 API
     * @param serviceId
     * @return
     */
    @PostMapping("/welfare/service")
    public Map<String, Object> welfare(
            @RequestParam("serviceId") int serviceId) {

        List<String> serviceList = welfareBO.getServiceListByServiceId(serviceId);

        Map<String, Object> result = new HashMap<>();
        if (serviceList != null) {
            result.put("code", 200);
            result.put("result", "성공");
            result.put("service", serviceList);
        } else {
            result.put("code", 500);
            result.put("error_message", "데이터 전송에 실패했습니다.");
        }

        return result;
    }
}
