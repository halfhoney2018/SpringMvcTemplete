package com.yootk.mybatis.test;

import com.yootk.mybatis.util.MyBatisSessionFactory;
import com.yootk.mybatis.vo.News;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestNews {
    @Test
    public void testSelect() throws Exception {
        Map<Long,String> infos = new HashMap<>() ;  // key存储nid、value存储title
        MyBatisSessionFactory.getSession()
                .select("com.yootk.mapper.NewsNS.findAll" , new ResultHandler<News>() {
                    @Override
                    public void handleResult(ResultContext resultContext) {
                        News news = (News) resultContext.getResultObject() ;
                        infos.put(news.getNid(),news.getTitle()) ;
                    }
                });
        System.out.println(infos);
        MyBatisSessionFactory.close();
    }
    @Test
    public void testSelectMap() throws Exception {
        Map<Object, Object> title = MyBatisSessionFactory.getSession().selectMap("com.yootk.mapper.NewsNS.findById", 9L, "nid");
        System.out.println(title);
        MyBatisSessionFactory.close();
    }
    @Test
    public void testSelectTitle() throws Exception {
        Map<Object, Object> title = MyBatisSessionFactory.getSession().selectMap("com.yootk.mapper.NewsNS.findAllTitle","nid");
        System.out.println(title);
        MyBatisSessionFactory.close();
    }

    @Test
    public void testSelectCondition() throws Exception {
        Map<String,Object> params = new HashMap<>() ;
        params.put("nid",2L) ;
        params.put("title","今天是一个好日子") ;
//        params.put("content","NOContent") ;
        System.out.println(MyBatisSessionFactory.getSession()
                .selectList("com.yootk.mapper.NewsNS.findAllCondition",params));
        MyBatisSessionFactory.close();
    }

    @Test
    public void testSplit() throws Exception {
        Map<String,Object> splitParams = new HashMap<String,Object>() ; // 保存分页相关参数
        splitParams.put("column", "title");
        splitParams.put("keyword", "%%");
        splitParams.put("start", 5); // (currentPage - 1) * lineSize
        splitParams.put("lineSize", 5);
        List<News> all = MyBatisSessionFactory.getSession().selectList("com.yootk.mapper.NewsNS.findSplit",splitParams);
        Long count = MyBatisSessionFactory.getSession().selectOne("com.yootk.mapper.NewsNS.getAllCount",splitParams);
        System.out.println("【分页数据】" + all);
        System.out.println("【数据行统计】" + count);
        MyBatisSessionFactory.close();
    }

    @Test
    public void testList() throws Exception {
        Map<String,Object> params = new HashMap<>() ;
        params.put("title","今天是一个好日子") ;
        List<News> all = MyBatisSessionFactory.getSession()
                .selectList("com.yootk.mapper.NewsNS.findAll",params);
        System.out.println(all);
        MyBatisSessionFactory.close();
    }
    @Test
    public void testGet() throws Exception {
        News vo = MyBatisSessionFactory.getSession().selectOne("com.yootk.mapper.NewsNS.findById", 9L);
        System.out.println(vo);
        MyBatisSessionFactory.close();
    }
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
//        vo.setContent("你是个屌丝，梦里成为富翁");
//        vo.setNid(1L); // 修改数据时需要设置准确的编号
        System.out.println("数据修改：" + MyBatisSessionFactory.getSession().update("com.yootk.mapper.NewsNS.doEdit", vo));
        MyBatisSessionFactory.getSession().commit();
        MyBatisSessionFactory.close();
    }

    @Test
    public void testAdd() throws Exception {
        News vo = new News();
        vo.setTitle("今天是一个好日子");
        // vo.setContent("是一个充满朝气的人生第一天开始！");
        System.out.println("增加数据行数：" + MyBatisSessionFactory.getSession()
                .delete("com.yootk.mapper.NewsNS.doCreate", vo));
        System.out.println("增加后的nid主键：" + vo.getNid());
        MyBatisSessionFactory.getSession().commit();
        MyBatisSessionFactory.close();
    }
}
