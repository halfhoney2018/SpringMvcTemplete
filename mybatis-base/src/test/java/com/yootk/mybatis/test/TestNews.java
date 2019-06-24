package com.yootk.mybatis.test;

import com.yootk.mybatis.util.MyBatisSessionFactory;
import com.yootk.mybatis.vo.News;
import org.junit.Test;

public class TestNews {
    @Test
    public void testDeletet() throws Exception {
        System.out.println("数据删除：" + MyBatisSessionFactory.getSession()
                .update("com.yootk.mapper.NewsNS.doRemove", 3L));
        MyBatisSessionFactory.getSession().commit();
        MyBatisSessionFactory.close();
    }
    @Test
    public void testEdit() throws Exception {
        News vo = new News();
        vo.setTitle("人生最舒服的日子？");
        vo.setContent("你是个屌丝，梦里成为富翁");
        vo.setNid(9L); // 修改数据时需要设置准确的编号
        System.out.println("数据修改：" + MyBatisSessionFactory.getSession().update("com.yootk.mapper.NewsNS.doEdit", vo));
        MyBatisSessionFactory.getSession().commit();
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
