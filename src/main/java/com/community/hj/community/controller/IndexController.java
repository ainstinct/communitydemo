package com.community.hj.community.controller;

import com.community.hj.community.dto.PaginationDTO;
import com.community.hj.community.dto.QuestionDTO;
import com.community.hj.community.mapper.QuestionMapper;
import com.community.hj.community.mapper.UserMapper;
import com.community.hj.community.model.Question;
import com.community.hj.community.model.User;
import com.community.hj.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class IndexController {


    @Autowired
    private QuestionService questionService;

    /*
    当访问首页的时候，循环去看所有的cookies，找到cookies=“token”的，拿到这个cookies去数据库里查，是不是有这个记录
    如果有将放到session中(request.getsession.setAattribute....),前端通过session来判断是否展示“我”或“登录”

    */
    @GetMapping("/")
    public String index(Model model,
                    @RequestParam(name="page",defaultValue = "1") Integer page,
                    @RequestParam(name="size",defaultValue = "5 ") Integer size   ){

        PaginationDTO pagination = questionService.list(page,size);
        model.addAttribute("pagination",pagination);

        return "index";
    }
}










