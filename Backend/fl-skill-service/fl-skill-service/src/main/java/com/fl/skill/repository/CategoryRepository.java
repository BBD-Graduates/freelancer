package com.fl.skill.repository;

import com.fl.skill.model.Request.Category;
import com.fl.skill.model.Response.CategoryRes;

import java.util.List;

public interface CategoryRepository {
    int save (Category category);
    List<CategoryRes> getAll();
    List<CategoryRes> getById(int id);
    int delete(int id);
    int update(Category category,int id);
    int updateLogoUrl(String url,int id);
}
