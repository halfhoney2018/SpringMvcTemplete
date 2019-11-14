package com.yootk.ssm.service;

import com.yootk.ssm.vo.News;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.Map;

@CacheConfig(cacheNames = "news")   // 进行了缓存信息的统一配置处理
public interface INewsService {

    @CacheEvict
    public Boolean remove(Long id);

    @Cacheable(cacheNames = "news")
    @CachePut(key = "#vo.nid", unless = "#result==null")
    public News update(News vo);//一定要返回新的News对象实例

    public boolean add(News vo) ;
    @Cacheable(cacheNames = "news" ,condition = "#id<10",unless = "#result==null")
    public News get(long id) ;
    @Cacheable(cacheNames = "news",key = "#id",sync =true)
    public News get(long id,String title) ;
    public Map<String,Object> list(String column, String keyword, long currentPage, int lineSize) ;
}
