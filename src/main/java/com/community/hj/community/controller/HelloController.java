package com.community.hj.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name") String name, Model model){
        model.addAttribute("name",name);//将浏览器传过来的值，放到model中。(localhost:8080/hello?name=hhh)
        return "hello";//返回,然后自动去找hello这个模板；去templates中找
    }
}
