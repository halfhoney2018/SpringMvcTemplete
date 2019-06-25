package com.yootk.ssm.dao.impl;

import com.yootk.ssm.dao.INewsDAO;
import com.yootk.ssm.vo.News;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public class NewsDAOImpl implements INewsDAO {
    @Autowired
    private SqlSessionFactory sessionFactory ;
    @Override
    public boolean doCreate(News vo) {
        return this.sessionFactory.openSession().insert("com.yootk.mapper.NewsNS.doCreate",vo) > 0;
    }
    @Override
    public News findById(Long id) {
        return this.sessionFactory.openSession().selectOne("com.yootk.mapper.NewsNS.findById",id);
    }
    @Override
    public List<News> findSplit(Map<String, Object> params) {
        return this.sessionFactory.openSession().selectList("com.yootk.mapper.NewsNS.findSplit",params);
    }

    @Override
    public Long getAllCount(Map<String, Object> params) {
        return this.sessionFactory.openSession().selectOne("com.yootk.mapper.NewsNS.getAllCount",params);
    }
}
