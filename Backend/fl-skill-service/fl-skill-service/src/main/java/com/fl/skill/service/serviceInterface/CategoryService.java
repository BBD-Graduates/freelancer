package com.fl.skill.service.serviceInterface;
import com.fl.skill.model.request.Category;
import com.fl.skill.model.response.CategorySkillsResponse;

import java.util.List;

public interface CategoryService {
    String insertCategories(Category category)  ;
    List<CategorySkillsResponse> getCategoryWithSkills(Integer categoryId) ;
    String deleteCategory(int categoryId) ;
    String updateCategory(Category category, int categoryId) ;
}
