package com.fl.skill.service;

import com.fl.skill.config.Constant;
import com.fl.skill.exceptions.CategoryNotFoundException;
import com.fl.skill.model.request.Category;
import com.fl.skill.model.response.CategoryRes;
import com.fl.skill.repository.DbQueries;
import com.fl.skill.service.serviceInterface.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final DbQueries dbQueries;

    private final JdbcTemplate jdbcTemplate;

    @Override
    public String insertCatgories(Category category) throws CategoryNotFoundException {
        try {
            int insertStatus = jdbcTemplate.update(dbQueries.getAddCategory(), category.getName());
            if (insertStatus > 0) {
                return Constant.INSERTED_SUCCESSFULLY;
            } else {
                return Constant.CANT_PROCESS_REQUEST;
            }
        } catch (Exception e) {
            throw new CategoryNotFoundException("Error to insert category" + e);
        }

    }

    @Override
    public List<CategoryRes> getCategories(Integer categoryId) throws CategoryNotFoundException {

        try {
            if (!categoryId.equals(0))
                return jdbcTemplate.query(dbQueries.getSelectCategoryByCategoryId(), BeanPropertyRowMapper.newInstance(CategoryRes.class),
                        categoryId);
            else
                return jdbcTemplate.query(dbQueries.getSelectAllCategories(),
                        BeanPropertyRowMapper.newInstance(CategoryRes.class));

        } catch (DataAccessException e) {
            throw new CategoryNotFoundException("Error Fetching Categories" + e);
        }
    }

    @Override
    public String deleteCategory(int categoryId) throws CategoryNotFoundException {
        try {
            int deleteStatus = jdbcTemplate.update(dbQueries.getRemoveCategoryByCategoryId(), categoryId);
            if (deleteStatus > 0) {
                return Constant.DELETED_SUCCESSFULLY;
            } else {
                return Constant.CANT_PROCESS_REQUEST;
            }
        } catch (Exception e) {
            throw new CategoryNotFoundException("Error to delete category " + e);
        }
    }

    @Override
    public String updateCategory(Category category, int categoryId) throws CategoryNotFoundException {
        try {
            int updateStatus = jdbcTemplate.update(dbQueries.getUpdateCategory(), category.getName(), categoryId);
            if (updateStatus > 0) {
                return Constant.UPDATED_SUCCESSFULLY;
            } else {
                return Constant.CANT_PROCESS_REQUEST;
            }
        } catch (Exception e) {
            throw new CategoryNotFoundException("Error to update category " + e);
        }
    }

    @Override
    public int updateCategoryLogoUrl(String url, int categoryId) throws CategoryNotFoundException {
        try {
            return jdbcTemplate.update(dbQueries.getUpdateCategoryLogo(), url, categoryId);
        } catch (Exception e) {
            throw new CategoryNotFoundException("Error to update LogoUrl " + e);
        }
    }
}
