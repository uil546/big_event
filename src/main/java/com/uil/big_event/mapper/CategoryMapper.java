package com.uil.big_event.mapper;

import com.uil.big_event.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    void add(Category category);

    List<Category> list(Integer userId);

    Category findById(Integer id);

    void update(Category category);

    void delete(Integer id);
}
