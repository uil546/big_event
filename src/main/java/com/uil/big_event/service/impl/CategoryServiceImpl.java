package com.uil.big_event.service.impl;

import com.uil.big_event.mapper.CategoryMapper;
import com.uil.big_event.pojo.Category;
import com.uil.big_event.service.CategoryService;
import com.uil.big_event.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    
    @Override
    public void add(Category category) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        category.setCreateUser(userId);
        categoryMapper.add(category);
    }
    
    @Override
    public List<Category> list(){
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        return categoryMapper.list(userId);
    }
    
    @Override
    public Category findById(Integer id){
        Category c = categoryMapper.findById(id);
        return c;
    }
    
    @Override
    public void update(Category category){
        categoryMapper.update(category);
    }

    @Override
    public void delete(Integer id) {
        categoryMapper.delete(id);
    }
}
