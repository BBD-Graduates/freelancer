package com.fl.skill.service;

import com.fl.skill.config.Constant;
import com.fl.skill.model.Request.Category;
import com.fl.skill.model.Response.CategoryRes;
import com.fl.skill.queries.DbQueries;
import com.fl.skill.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryImpl implements CategoryRepository {

    @Autowired
    DbQueries dbQueries;
    @Autowired
    private Environment env;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public String save(Category category) {
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
    public List<CategoryRes> getAll() {
        try {
            List<CategoryRes> categories =new ArrayList<>();
            categories= jdbcTemplate.query(dbQueries.getCategories(), BeanPropertyRowMapper.newInstance(CategoryRes.class));
            return categories;
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CategoryRes> getById(int id) {

        try {
            List<CategoryRes> category = new ArrayList<>();
            category= jdbcTemplate.query(dbQueries.getCategory(), BeanPropertyRowMapper.newInstance(CategoryRes.class),id);
            return category;
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String delete(int id) {
        try {
            int deleteStatus =  jdbcTemplate.update(dbQueries.getRemoveCategory(),id);
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
    public String update(Category category, int id) {
        try {
            int updateStatus =  jdbcTemplate.update(dbQueries.getUpdateCategory(),category.getName(),id);
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
    public int updateLogoUrl(String url, int id) {
        return jdbcTemplate.update(dbQueries.getUpdateCategoryLogo(),url,id);
    }
}
