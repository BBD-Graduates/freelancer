package com.fl.skill.service.serviceInterface;

import com.fl.skill.exceptions.CategoryNotFoundException;
import com.fl.skill.model.request.Category;
import com.fl.skill.model.response.CategoryRes;

import java.util.List;

public interface CategoryService {
    String insertCatgories(Category category);
    List<CategoryRes> getAllCategories();
    List<CategoryRes> getCategoryById(int categoryId) throws CategoryNotFoundException;
    String deleteCategory(int id);
    String updateCategory(Category category, int id);
    int updateCategoryLogoUrl(String url, int id);
}
