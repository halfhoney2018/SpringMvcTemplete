package com.yootk.mybatis.test;

import com.yootk.mybatis.util.MyBatisSessionFactory;
import com.yootk.mybatis.vo.News;
import org.junit.Test;

public class TestNewsTemp {
    @Test
    public void testGet() throws Exception {
        News vo = MyBatisSessionFactory.getSession()
                .selectOne("com.yootk.mapper.NewsNS.findById", 1L);
        System.out.println(vo);
        MyBatisSessionFactory.close();
    }
    @Test
    public void testAdd() throws Exception {
        News vo = new News();
        vo.setTitle("今天是一个好日子");
        vo.setContent("是一个充满朝气的人生第一天开始！");
        System.out.println("增加数据行数：" + MyBatisSessionFactory.getSession()
                .delete("com.yootk.mapper.NewsNS.doCreate", vo));
        System.out.println("增加后的nid主键：" + vo.getNid());
        MyBatisSessionFactory.getSession().commit();
        MyBatisSessionFactory.close();
    }
}
