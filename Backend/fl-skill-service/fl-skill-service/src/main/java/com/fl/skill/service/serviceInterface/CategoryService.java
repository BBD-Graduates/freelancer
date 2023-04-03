package com.fl.skill.service.serviceInterface;

import com.fl.skill.exceptions.CategoryNotFoundException;
import com.fl.skill.model.request.Category;
import com.fl.skill.model.response.CategoryRes;

import java.util.List;

public interface CategoryService {
    String insertCatgories(Category category) throws CategoryNotFoundException;
    List<CategoryRes> getCategories(Integer categoryId) throws CategoryNotFoundException;
    String deleteCategory(int id) throws CategoryNotFoundException;
    String updateCategory(Category category, int id) throws CategoryNotFoundException;
    int updateCategoryLogoUrl(String url, int id) throws CategoryNotFoundException;
}
