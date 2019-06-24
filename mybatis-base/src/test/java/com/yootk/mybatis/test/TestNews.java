package com.yootk.mybatis.test;

import com.yootk.mybatis.util.MyBatisSessionFactory;
import com.yootk.mybatis.vo.News;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

public class TestNews {
    @Test
    public void testAdd() throws Exception {
        News vo = new News();
        vo.setTitle("今天是一个好日子");
        vo.setContent("是一个充满朝气的人生第一天开始！");
        System.out.println(MyBatisSessionFactory.getSession().insert("com.yootk.mapper.NewsNS.doCreate", vo));
        MyBatisSessionFactory.getSession().commit();
        MyBatisSessionFactory.close();
    }
}