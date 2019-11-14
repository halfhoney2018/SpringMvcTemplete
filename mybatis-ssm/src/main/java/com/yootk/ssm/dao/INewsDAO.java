package com.yootk.ssm.dao;


import com.yootk.ssm.vo.News;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;
import java.util.Map;

public interface INewsDAO {
//    @Insert("INSERT INTO news(title,content) VALUES (#{title},#{content})")
//    @SelectKey(before = false,keyProperty ="nid",keyColumn ="nid" ,resultType = java.lang.Long.class,statement ="SELECT LAST_INSERT_ID()")
    public boolean doCreate(News vo) ;
    public News findById(Long id) ;
    /**
     * 进行数据的分页显示处理操作
     * @param params 可以传递的参数内容如下：
     * 1、key = column、value = 要进行数据查询的列；
     * 2、key = keyword、value = 模糊查询关键字；
     * 3、key = start、value = 开始行；
     * 4、key = lineSize、value = 每页显示数据行。
     *  如果此时没有传递column或者是keyword参数，则表示对全部数据进行分页
     * @return 数据的全部内容
     */
    public List<News> findSplit(Map<String,Object> params) ;
    /**
     * 数据个数的统计处理
     * @param params 可以传递的参数内容如下：
     * 1、key = column、value = 要进行数据查询的列；
     * 2、key = keyword、value = 模糊查询关键字；
     *  如果此时没有传递column或者是keyword参数，则表示统计全部数据
     * @return 返回数据行的个数
     */
    public Long getAllCount(Map<String,Object> params) ;
}
