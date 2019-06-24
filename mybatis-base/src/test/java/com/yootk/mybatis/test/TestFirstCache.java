package com.yootk.mybatis.test;

import com.yootk.mybatis.util.MyBatisSessionFactory;
import com.yootk.mybatis.vo.News;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.junit.Test;

import java.util.*;

public class TestFirstCache {
    @Test
    public void testGet() throws Exception {
        System.out.println("【第一次数据查询】SqlSession = " + MyBatisSessionFactory.getSession());
        News vo1 = MyBatisSessionFactory.getSession()
                .selectOne("com.yootk.mapper.NewsNS.findById", 3L);
        System.out.println("【第一次查询结果】News = " + vo1);
        System.out.println("========================== 防止混乱的分隔符 ==========================");
        System.out.println("【第二次数据查询】SqlSession = " + MyBatisSessionFactory.getSession());
        News vo2 = MyBatisSessionFactory.getSession()
                .selectOne("com.yootk.mapper.NewsNS.findById", 3L);
        System.out.println("【第二次查询结果】News = " + vo2);
        MyBatisSessionFactory.close();
    }
    @Test
    public void testGetChange() throws Exception {
        System.out.println("【第一次数据查询】SqlSession = " + MyBatisSessionFactory.getSession());
        News vo1 = MyBatisSessionFactory.getSession()
                .selectOne("com.yootk.mapper.NewsNS.findById", 3L);
        vo1.setNid(99L); // 修改了ID
        vo1.setTitle("就是这么滴嗨皮");    // 修改缓存数据
        vo1.setContent("嗨皮到天亮！");    // 修改缓存数据
        System.out.println("【第一次查询结果】News = " + vo1);
        System.out.println("========================== 防止混乱的分隔符 ==========================");
        System.out.println("【第二次数据查询】SqlSession = " + MyBatisSessionFactory.getSession());
        News vo2 = MyBatisSessionFactory.getSession()
                .selectOne("com.yootk.mapper.NewsNS.findById", 3L);
        System.out.println("【第二次查询结果】News = " + vo2);
        MyBatisSessionFactory.close();
    }
    @Test
    public void testClearChange() throws Exception {
        System.out.println("【第一次数据查询】SqlSession = " + MyBatisSessionFactory.getSession());
        News vo1 = MyBatisSessionFactory.getSession()
                .selectOne("com.yootk.mapper.NewsNS.findById", 3L);
        vo1.setNid(99L); // 修改了ID
        vo1.setTitle("就是这么滴嗨皮");    // 修改缓存数据
        vo1.setContent("嗨皮到天亮！");    // 修改缓存数据
        System.out.println("【第一次查询结果】News = " + vo1);
        System.out.println("========================== 防止混乱的分隔符 ==========================");
        MyBatisSessionFactory.getSession().clearCache(); // 清除一级缓存
        System.out.println("【第二次数据查询】SqlSession = " + MyBatisSessionFactory.getSession());
        News vo2 = MyBatisSessionFactory.getSession()
                .selectOne("com.yootk.mapper.NewsNS.findById", 3L);
        System.out.println("【第二次查询结果】News = " + vo2);
        MyBatisSessionFactory.close();
    }
}
