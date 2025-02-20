package com.dreamlink.welfare;

import com.dreamlink.service.domain.Service;
import com.dreamlink.welfare.bo.WelfareBO;
import com.dreamlink.welfare.domain.Welfare;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class WelfareController {

    private final WelfareBO welfareBO;

    @GetMapping("/welfare")
    public String calendar(
            Model model) {

        List<Service> serviceMenu = welfareBO.getService();

        model.addAttribute("serviceMenu", serviceMenu);
        return "welfare";
    }

    @GetMapping("/welfare/serviceCard")
    public String serviceCard(
            Model model,
            @RequestParam(value = "service", required = false) String service, // 아동
            @RequestParam("entity") String entity) { // 중앙부처

        // 서비스가 선택되었으면, 해당 서비스에 대한 추가 처리
        if (service != null) {
            // 선택된 서비스에 대한 추가 로직 예: 서비스 상세 정보 가져오기
            List<Welfare> selectedService = welfareBO.getServiceListByService(entity, service);
            List<List<String>> subjectList = welfareBO.getSubjectListBySelectedService(selectedService);

            if (subjectList != null) {
                model.addAttribute("subjectList", subjectList);
                model.addAttribute("welfareList", selectedService);
            }
        }

        return "serviceCard";
    }

    @PostMapping("/welfare/entity")
    public String entity(
            Model model,
            @RequestParam("entity") String entity) {

        List<Welfare> welfareList = welfareBO.getWelfareListByEntity(entity);
        List<List<String>> subjectList = welfareBO.getSubjectList(entity);

        if (subjectList != null) {
            model.addAttribute("subjectList", subjectList);
            model.addAttribute("welfareList", welfareList);
        }

        return "serviceCard";
    }
}
