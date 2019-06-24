package com.yootk.mybatis.test;

import com.yootk.mybatis.util.MyBatisSessionFactory;
import com.yootk.mybatis.vo.News;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class TestSecondCache {
    @Test
    public void testGet() throws Exception {
        SqlSession sessionA = MyBatisSessionFactory.getSessionFactory().openSession() ;
        System.out.println("【第一次数据查询】SqlSession = " + sessionA);
        News vo1 = sessionA.selectOne("com.yootk.mapper.NewsNS.findById", 3L);
        System.out.println("【第一次查询结果】News = " + vo1);
        sessionA.close(); // 关闭SqlSession，意味着写入二级缓存
        System.out.println("========================== 防止混乱的分隔符 ==========================");
        SqlSession sessionB = MyBatisSessionFactory.getSessionFactory().openSession() ;
        System.out.println("【第二次数据查询】SqlSession = " + sessionB);
        News vo2 = sessionB.selectOne("com.yootk.mapper.NewsNS.findById", 3L);
        System.out.println("【第二次查询结果】News = " + vo2);
        MyBatisSessionFactory.close();
    }
}
