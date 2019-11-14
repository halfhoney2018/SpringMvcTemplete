package com.yootk.ssm.action;

import com.yootk.ssm.service.INewsService;
import com.yootk.ssm.util.action.AbstractAction;
import com.yootk.ssm.vo.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pages/admin/news/*")
public class NewsAction extends AbstractAction {
    @Autowired
    private INewsService newsService ;
    @RequestMapping("add_pre")
    public String addPre() {
        return super.getMessage("news.add.page") ;
    }
    @RequestMapping("add")
    public ModelAndView add(News vo) {
        ModelAndView modelAndView = new ModelAndView(super.getMessage("news.add.show"));
        modelAndView.addObject("istrue",this.newsService.add(vo));
        return modelAndView ;
    }
}
