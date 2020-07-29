package com.community.hj.community.controller;

import com.community.hj.community.dto.QuestionDTO;
import com.community.hj.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    //接收浏览器的地址
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id, Model model){
    //接下就是，拿到Id之后去数据库中查询这个Id是否存在，存在的 话把里面的数据拿出来，然后跳转到页面,数据是通过model传入到页面的
        QuestionDTO questionDTO = questionService.getById(id);
        questionService.incView(id);//累加阅读数
        model.addAttribute("question",questionDTO);//将questionDTO传到页面
        return "question";
    }
}
