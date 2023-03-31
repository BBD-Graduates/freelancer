package com.fl.skill.service;

import com.fl.skill.config.Constant;
import com.fl.skill.exceptions.CategoryNotFoundException;
import com.fl.skill.model.request.Category;
import com.fl.skill.model.response.CategoryRes;
import com.fl.skill.repository.DbQueries;
import com.fl.skill.service.serviceInterface.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private final  DbQueries dbQueries;

    private final Environment env;

    private final  JdbcTemplate jdbcTemplate;

    @Override
    public String insertCatgories(Category category) {
        try {
            int insertStatus =  jdbcTemplate.update(dbQueries.getAddCategory(),category.getName());
            if (insertStatus > 0) {
                return Constant.INSERTED_SUCCESSFULLY;
            } else {
                return Constant.CANT_PROCESS_REQUEST;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<CategoryRes> getAllCategories() {
        try {
            List<CategoryRes> categories =new ArrayList<>();
            categories= jdbcTemplate.query(dbQueries.getCategories(), BeanPropertyRowMapper.newInstance(CategoryRes.class));
            return categories;
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CategoryRes> getCategoryById(int categoryId) throws CategoryNotFoundException {

        try {

            return jdbcTemplate.query(dbQueries.getCategory(), BeanPropertyRowMapper.newInstance(CategoryRes.class),categoryId);

        } catch (DataAccessException e) {
            throw new CategoryNotFoundException("Error Fetching Categories"+e);
        }
    }

    @Override
    public String deleteCategory(int categoryId) {
        try {
            int deleteStatus =  jdbcTemplate.update(dbQueries.getRemoveCategory(),categoryId);
            if (deleteStatus > 0) {
                return Constant.DELETED_SUCCESSFULLY;
            } else {
                return Constant.CANT_PROCESS_REQUEST;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String updateCategory(Category category, int categoryId) {
        try {
            int updateStatus =  jdbcTemplate.update(dbQueries.getUpdateCategory(),category.getName(),categoryId);
            if (updateStatus > 0) {
                return Constant.UPDATED_SUCCESSFULLY;
            } else {
                return Constant.CANT_PROCESS_REQUEST;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateCategoryLogoUrl(String url, int categoryId) {
        return jdbcTemplate.update(dbQueries.getUpdateCategoryLogo(),url,categoryId);
    }
}
