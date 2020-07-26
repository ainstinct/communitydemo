package com.community.hj.community.service;

import com.community.hj.community.dto.PaginationDTO;
import com.community.hj.community.dto.QuestionDTO;
import com.community.hj.community.mapper.QuestionMapper;
import com.community.hj.community.mapper.UserMapper;
import com.community.hj.community.model.Question;
import com.community.hj.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;


    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.count();//总页数
        paginationDTO.setPagination(totalCount,page,size);

        if(page<1){
            page = 1;
        }
        if(page >paginationDTO.getTotalPage()){
            page = paginationDTO.getTotalPage();
        }


        Integer offset = size*(page -1);//设置分页偏移
        List<Question> questions = questionMapper.list(offset,size);//查到所有question对象
        List<QuestionDTO> questionDTOList = new ArrayList<>();


        for(Question question : questions){
            User user = userMapper.findById(question.getCreator());//获取当前的user
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);//快速的把question对象的所有属性拷贝到questionDTO
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);



        return paginationDTO;
    }
}
























