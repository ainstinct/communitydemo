package com.community.hj.community.dto;

import com.community.hj.community.model.User;
import lombok.Data;
//主要加了一个user,因为其他是数据库关联的
@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}
