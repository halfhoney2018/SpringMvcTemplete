package com.yootk.mybatis.test;

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
        // 1、通过输入流定义要读取的mybatis核心文件的路径信息；
        InputStream input = Resources.getResourceAsStream("mybatis/mybatis.cfg.xml");
        // 2、通过加载的操作流创建SqlSessionFactory接口实例，该接口实例依靠的是SqlSessionFactoryBuilder类完成
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(input);
        // 3、通过SqlSessionFactory连接工厂创建SqlSession数据操作接口实例
        SqlSession session = sessionFactory.openSession();
        News vo = new News();
        vo.setTitle("今天是一个好日子");
        vo.setContent("是一个充满朝气的人生第一天开始！");
        // 4、利用SqlSession调用执行的SQL语句，同时传递所需要的参数内容
        System.out.println(session.insert("com.yootk.mapper.NewsNS.doCreate", vo));
        session.commit();
        session.close();
        input.close();
    }
}
