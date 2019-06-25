package com.yootk.ssm.service.impl;

import com.yootk.ssm.dao.INewsDAO;
import com.yootk.ssm.service.INewsService;
import com.yootk.ssm.vo.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class NewsServiceImpl implements INewsService {
    @Autowired
    private INewsDAO newsDAO ;
    @Override
    public boolean add(News vo) {
        return this.newsDAO.doCreate(vo);
    }

    @Override
    public News get(long id) {
        return this.newsDAO.findById(id);
    }

    @Override
    public Map<String, Object> list(String column, String keyword, long currentPage, int lineSize) {
        Map<String,Object> result = new HashMap<>() ;   // 实现结果的返回
        Map<String,Object> params = new HashMap<>() ;   // 实现参数的传递
        params.put("column",column) ;
        params.put("keyword",keyword) ;
        params.put("start",(currentPage - 1) * lineSize) ;
        params.put("lineSize",lineSize) ;
        result.put("allRecorders",this.newsDAO.getAllCount(params)) ;
        result.put("allNewses",this.newsDAO.findSplit(params)) ;
        return result;
    }
}
