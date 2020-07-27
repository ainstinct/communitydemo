package com.community.hj.community.controller;

import com.community.hj.community.dto.PaginationDTO;
import com.community.hj.community.model.User;
import com.community.hj.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    @Autowired
    private QuestionService questionService;

    //浏览器访问/profile的时候进入这个里面
    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request, @PathVariable(name="action") String action, Model model,
                          @RequestParam(name="page",defaultValue = "1") Integer page,
                          @RequestParam(name="size",defaultValue = "5 ") Integer size ){
        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            return "redirect:/";//返回到index页面
        }

        if("questions".equals(action)){//如果前端的网址是questions则执行下面逻辑
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if("replies".equals(action)){//如果前端的网址是replies则执行下面逻辑
            model.addAttribute("section","replies");//将数据传到前端
            model.addAttribute("sectionName","最新回复");
        }
        PaginationDTO paginationDTO = questionService.list(user.getId(), page, size);
        model.addAttribute("pagination",paginationDTO);
        return "profile";//返回到视图
    }
}













