package com.yootk.test.service;

import com.yootk.ssm.service.INewsService;
import com.yootk.ssm.vo.News;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:spring/spring-base.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestNewsService {
    @Autowired
    private INewsService newsService;

    @Test
    public void testAdd() {
        News vo = new News();
        vo.setTitle("Feel倍儿爽");
        vo.setContent("期待奇迹的到来");
        System.out.println(this.newsService.add(vo));
    }
    @Test
    public void testGet() {

        System.out.println(this.newsService.get(9L));
        System.out.println(this.newsService.get(9L));
    }
    @Test
    public void testGet2() {
        System.out.println(this.newsService.get(18L));
        System.out.println(this.newsService.get(18L,"1573376478742"));

    }
    @Test
    public void testList() {
        System.out.println(this.newsService.list("title","%开心%",1,10));
    }
    @Test
    public void update(){
        News vo=new News();
        vo.setNid(1L);
        vo.setTitle("修改完成后的标题");
        vo.setContent("996");
        System.out.println(this.newsService.update(vo));
        System.out.println(this.newsService.get(1L));
    }
    @Test
    public void delete(){
        System.out.println(this.newsService.get(7L));
        System.out.println(this.newsService.remove(7L));
        System.out.println(this.newsService.get(7L));
    }
}
