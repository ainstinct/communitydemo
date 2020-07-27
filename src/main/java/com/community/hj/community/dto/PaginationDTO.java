package com.community.hj.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalPage, Integer page) {
        this.totalPage = totalPage;
        this.page = page;
        pages.add(page);
        for(int i=1 ;i<=3;i++){
            if(page - i> 0){
                pages.add(0,page-i);//往头部插入
            }
            if(page +i <= totalPage){
                pages.add(page+i);//往尾部插入
            }

        }

        //是否展示上一页  "<"这个符号
        if(page == 1){
            showPrevious = false;//等于1的时候是没有上一页的，也就是没有上一页那个符号
        }else{
            showPrevious = true;
        }

        //是否展示下一页
        if(page == totalPage){
            showNext = false;//当最后一页的时候，没有下一页
        }else{
            showNext = true;
        }

        //是否展示第一页,也就是"<<"按钮
        if(pages.contains(1)){
            showFirstPage = false;//当前列表数组里面有第一页所以不展示第一页
        }else{
            showFirstPage = true;//当前列表数组里面没有第一页，所以展示去往第一页的按钮
        }

        //是否展示最后一页
        if(pages.contains(totalPage)){
            showEndPage = false;
        }else{
            showEndPage = true;
        }

    }
}



















