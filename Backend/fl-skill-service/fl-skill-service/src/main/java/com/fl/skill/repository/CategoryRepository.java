package com.fl.skill.repository;

import com.fl.skill.model.Request.Category;
import com.fl.skill.model.Response.CategoryRes;

import java.util.List;

public interface CategoryRepository {
    String save (Category category);
    List<CategoryRes> getAll();
    List<CategoryRes> getById(int id);
    String delete(int id);
    String update(Category category, int id);
    int updateLogoUrl(String url,int id);
}
