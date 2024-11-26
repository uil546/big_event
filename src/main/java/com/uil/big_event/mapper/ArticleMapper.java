package com.uil.big_event.mapper;

import com.uil.big_event.pojo.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {
    void add(Article article);

    List<Article> list(Integer userId, Integer categoryId, String state);

    void update(Article article);

    Article detail(Integer id);

    void delete(Integer id);
}
