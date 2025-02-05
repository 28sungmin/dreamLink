package com.dreamlink.test;

import com.dreamlink.post.domain.Post;
import com.dreamlink.post.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class testController {
    private final PostMapper postMapper;

    @ResponseBody
    @GetMapping("/test1")
    public String test1() {
        return "<h2>test1 성공</h2>";
    }

    @ResponseBody
    @GetMapping("/test2")
    public Map<String, Object> test2() {
        Map<String, Object> map = new HashMap<>();

        map.put("사과", 111);
        map.put("포도", 23);
        map.put("배", 156);

        return map;
    }

    @GetMapping("/test3")
    public String test3() {
        return "test/test3";
    }

    @ResponseBody
    @GetMapping("/test4")
    public List<Post> test4() {
        return postMapper.selectPostListTest();
    }

}
