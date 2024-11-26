package com.uil.big_event.service;

import com.uil.big_event.pojo.Article;
import com.uil.big_event.pojo.PageBean;

public interface ArticleService {
    void add(Article article);

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    void update(Article article);

    Article detail(Integer id);

    void delete(Integer id);
}
