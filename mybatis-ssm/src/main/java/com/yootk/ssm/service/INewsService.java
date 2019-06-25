package com.yootk.ssm.service;

import com.yootk.ssm.vo.News;

import java.util.Map;

public interface INewsService {
    public boolean add(News vo) ;
    public News get(long id) ;
    public Map<String,Object> list(String column, String keyword, long currentPage, int lineSize) ;
}
