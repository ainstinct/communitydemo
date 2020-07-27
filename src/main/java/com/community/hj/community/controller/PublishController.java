package com.community.hj.community.controller;

import com.community.hj.community.mapper.QuestionMapper;
import com.community.hj.community.model.Question;
import com.community.hj.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;


    @GetMapping("/publish")//form表单提交
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(//接受参数
    @RequestParam("title") String title,
    @RequestParam("description") String description,
    @RequestParam("tag") String tag,
    HttpServletRequest request,
    Model model
    ){
        model.addAttribute("title",title);//是为了会写到页面上去
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if(title == null || title ==""){//判断接受的参数是否为空
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description == null || description ==""){
            model.addAttribute("error","问题补充不能为空");
            return "publish";
        }
        if(tag == null || tag ==""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

//        //验证是否登录
//        User user = null;
//        Cookie[] cookies = request.getCookies();
//        if(cookies != null && cookies.length != 0){
//            for(Cookie cookie : cookies){
//                if(cookie.getName().equals("token")){
//                    String token = cookie.getValue();
//                     user = userMapper.findByToken(token);//通过token拿到数据库里存的user信息
//                    if(user != null){//user信息存在就绑定到session上去
//                        request.getSession().setAttribute("user",user);
//                    }
//                    break;
//                }
//            }
//        }
        User user = (User)request.getSession().getAttribute("user");
        if(user == null){//user不存在，将信息(用户未登录)返回到前端页面上去
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        //全部工作以后，构造一个question对象，创建一个questionMapper插入到数据库中
        //执行mvn flyway:migrate
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.create(question);
        return "redirect:/";
    }
}








