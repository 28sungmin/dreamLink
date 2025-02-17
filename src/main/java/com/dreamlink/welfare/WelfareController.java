package com.dreamlink.welfare;

import com.dreamlink.service.domain.Service;
import com.dreamlink.user.bo.UserBO;
import com.dreamlink.welfare.bo.WelfareBO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
