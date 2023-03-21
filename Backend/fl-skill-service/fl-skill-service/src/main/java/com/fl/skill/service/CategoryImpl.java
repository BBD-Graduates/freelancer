package com.fl.skill.service;

import com.fl.skill.model.Request.Category;
import com.fl.skill.model.Response.CategoryRes;
import com.fl.skill.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Repository
public class CategoryImpl implements CategoryRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public int save(String category) {
        return jdbcTemplate.update("INSERT INTO category (name) values (?)",new Object[]{category});
    }

    @Override
    public List<CategoryRes> getAll() {
        return jdbcTemplate.query("select * from category", BeanPropertyRowMapper.newInstance(CategoryRes.class));
    }

    @Override
    public List<CategoryRes> getById(int id) {
        return jdbcTemplate.query("select * from category where categoryId = ?", BeanPropertyRowMapper.newInstance(CategoryRes.class),id);
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("delete from category where categoryId = ?",id);
    }

    @Override
    public int update(Category category, int id) {
        return jdbcTemplate.update("update category set name = ? where categoryId = ?",new Object[]{category.getName(),id});
    }
}
