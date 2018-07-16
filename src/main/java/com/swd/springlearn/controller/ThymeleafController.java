package com.swd.springlearn.controller;

import com.swd.springlearn.entity.Author;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class ThymeleafController {
    //返回一个ModelAndView对象 包含需要返回的页面信息和携带信息
    @GetMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        mv.addObject("title","第一个web页面");
        mv.addObject("desc","使用模板技术");
        Author swd = new Author();
        swd.setAge(26);
        swd.setEmail("554097550@qq.com");
        swd.setEnglishName("Swide");
        swd.setName("宋文东");
        mv.addObject("author",swd);
        return mv;
    }

    @GetMapping("/index1")
    public String index1(HttpServletRequest request){
        request.setAttribute("title","第一个web页面");
        request.setAttribute("desc","使用模板技术");
        Author swd = new Author();
        swd.setAge(26);
        swd.setEmail("554097550@qq.com");
        swd.setEnglishName("Swide2");
        swd.setName("宋文东2");
        request.setAttribute("author",swd);
        //默认返回至src/main/resources/templates/xxxx.html
        return "index";
    }
}
