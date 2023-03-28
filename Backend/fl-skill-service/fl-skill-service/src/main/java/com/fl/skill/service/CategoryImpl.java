package com.fl.skill.service;

import com.fl.skill.model.Request.Category;
import com.fl.skill.model.Response.CategoryRes;
import com.fl.skill.queries.DbQueries;
import com.fl.skill.repository.CategoryRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;


@Getter
@Component
@RefreshScope
public class CategoryImpl implements CategoryRepository {

    @Autowired
    DbQueries dbQueries;
    @Autowired
    private Environment env;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public int save(Category category) {
        return jdbcTemplate.update(dbQueries.getAddCategory(),category.getName());
    }

    @Override
    public List<CategoryRes> getAll() {
        return jdbcTemplate.query(dbQueries.getCategories(), BeanPropertyRowMapper.newInstance(CategoryRes.class));
    }

    @Override
    public List<CategoryRes> getById(int id) {

        return jdbcTemplate.query(dbQueries.getCategory(), BeanPropertyRowMapper.newInstance(CategoryRes.class),id);
    }

    @Override
    public int delete(int id) {

        return jdbcTemplate.update(dbQueries.getRemoveCategory(),id);
    }

    @Override
    public int update(Category category, int id) {

        return jdbcTemplate.update(dbQueries.getUpdateCategory(),category.getName(),id);
    }

    @Override
    public int updateLogoUrl(String url, int id) {
        return jdbcTemplate.update(dbQueries.getUpdateCategoryLogo(),url,id);
    }
}
