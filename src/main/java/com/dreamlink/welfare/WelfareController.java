package com.dreamlink.welfare;

import com.dreamlink.service.domain.Service;
import com.dreamlink.welfare.bo.WelfareBO;
import com.dreamlink.welfare.domain.Welfare;
import jakarta.servlet.http.HttpSession;
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
            @RequestParam(value = "prevId", required = false) Integer prevIdParam,
            @RequestParam(value = "nextId", required = false) Integer nextIdParam,
            Model model) {

        List<Service> serviceMenu = welfareBO.getService();
        // 데이터 가져오기
        List<Welfare> welfareAllList = welfareBO.getWelfareAllList(prevIdParam, nextIdParam);
        Integer prevId = 0;
        Integer nextId = 0;

        if (welfareAllList.isEmpty() == false) { // postList가 비어있지 않을 때 페이징 정보를 채운다.
            prevId = welfareAllList.get(0).getId(); // 첫 번째 칸의 postId를 넣기
            nextId = welfareAllList.get(welfareAllList.size() - 1).getId(); // 마지막 칸의 postId를 넣기

            // 이전이 없나? 그렇다면 0으로 하기
            // user가 쓴 글들 중 제일 큰 숫자가 prevId와 같으면 이전이 없는 것임
            if (welfareBO.isPrevLastPage(prevId)) {
                prevId = 0;
            }

            // 다음이 없나? 그렇다면 0으로 하기
            // user가 쓴 글들 중 제일 작은 숫자가 nextId와 같으면 다음이 없는 것임
            if (welfareBO.isNextLastPage(nextId)) {
                nextId = 0;
            }
        }

        List<List<String>> subjectList = welfareBO.getSubjectAllList();

        model.addAttribute("serviceMenu", serviceMenu);
        model.addAttribute("welfareAllList", welfareAllList);
        model.addAttribute("subjectList", subjectList);
        model.addAttribute("prevId", prevId);
        model.addAttribute("nextId", nextId);
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
//
//    @GetMapping("/post-list-view")
//    public String postListView(
//            @RequestParam(value = "prevId", required = false) Integer prevIdParam,
//            @RequestParam(value = "nextId", required = false) Integer nextIdParam,
//            Model model, HttpSession session) {
//        // 로그인 된 사람인지 검사
//        // 로그인 안 된 사람이 주소를 치고 들어와도 바로 로그인으로 가도록 하기 위해 Integer로 받을 거임
//        Integer userId = (Integer)session.getAttribute("userId");
//        if (userId == null) {
//            // 비로그인이면 로그인 화면으로 이동시킨다.
//            return "redirect:/user/sign-in-view";
//        }
//
//        // 데이터 가져오기
//        List<Post> postList = postBO.getPostListByUserId(userId, prevIdParam, nextIdParam);
//        int prevId = 0;
//        int nextId = 0;
//
//        if (postList.isEmpty() == false) { // postList가 비어있지 않을 때 페이징 정보를 채운다.
//            prevId = postList.get(0).getId(); // 첫 번째 칸의 postId를 넣기
//            nextId = postList.get(postList.size() - 1).getId(); // 마지막 칸의 postId를 넣기
//
//            // 이전이 없나? 그렇다면 0으로 하기
//            // user가 쓴 글들 중 제일 큰 숫자가 prevId와 같으면 이전이 없는 것임
//            if (postBO.isPrevLastPageByUserId(userId, prevId)) {
//                prevId = 0;
//            }
//
//            // 다음이 없나? 그렇다면 0으로 하기
//            // user가 쓴 글들 중 제일 작은 숫자가 nextId와 같으면 다음이 없는 것임
//            if (postBO.isNextLastPageByUserId(userId, nextId)) {
//                nextId = 0;
//            }
//        }
//
//        // 모델에 담기
//        model.addAttribute("prevId", prevId);
//        model.addAttribute("nextId", nextId);
//        model.addAttribute("postList", postList);
//
//        return "post/postList";
//    }
}
