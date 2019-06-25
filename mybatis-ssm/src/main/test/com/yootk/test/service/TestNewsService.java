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
        System.out.println(this.newsService.get(3L));
    }
    @Test
    public void testList() {
        System.out.println(this.newsService.list("title","%开心%",1,10));
    }
}
