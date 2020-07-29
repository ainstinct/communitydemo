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
        Integer totalPage;

        Integer totalCount = questionMapper.count();//总页数

        if(totalCount % size == 0){
            totalPage = totalCount / size;//计算有多少页数，如果整除说明满页
        }else{
            totalPage = totalCount / size +1;//如果不能整除说明必须要多一页，其实这边可以用向上取整来做
        }

        if(page<1){
            page = 1;
        }
        if(page >totalPage){
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage,page);

        Integer offset = size*(page -1);//设置分页偏移
        List<Question> questions = questionMapper.list(offset,size);//查到所有question对象
        List<QuestionDTO> questionDTOList = new ArrayList<>();


        for(Question question : questions){
            User user = userMapper.selectByPrimaryKey(question.getCreator());//获取当前的user
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);//快速的把question对象的所有属性拷贝到questionDTO
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;

        Integer totalCount = questionMapper.countByUserId(userId);//总页数

        if(totalCount % size == 0){
            totalPage = totalCount / size;//计算有多少页数，如果整除说明满页
        }else{
            totalPage = totalCount / size +1;//如果不能整除说明必须要多一页，其实这边可以用向上取整来做
        }

        if(page<1){
            page = 1;
        }
        if(page >totalPage){
            page = totalPage;
        }
        paginationDTO.setPagination(totalCount,page);
        Integer offset = size*(page -1);//设置分页偏移
        List<Question> questions = questionMapper.listByUserId(userId,offset,size);//查到所有question对象
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for(Question question : questions){
            User user = userMapper.selectByPrimaryKey(question.getCreator());//获取当前的user
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);//快速的把question对象的所有属性拷贝到questionDTO
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }
    //通过QuestionSevice去调用QuestionMapper
    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.getById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);//将question赋值到questionDTO
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId() == null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.create(question);
        }else{
            //更新
            question.setGmtModified(question.getGmtCreate());
            questionMapper.update(question);
        }
    }
}
























