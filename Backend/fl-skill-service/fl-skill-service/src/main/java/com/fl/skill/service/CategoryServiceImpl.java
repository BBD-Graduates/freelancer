package com.fl.skill.service;

import com.fl.skill.config.Constant;
import com.fl.skill.model.request.Category;
import com.fl.skill.model.response.CategoryRes;
import com.fl.skill.model.response.CategorySkillsResponse;
import com.fl.skill.model.response.SkillRes;
import com.fl.skill.repository.DbQueries;
import com.fl.skill.service.serviceInterface.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final DbQueries dbQueries;

    private final JdbcTemplate jdbcTemplate;

    @Override
    public String insertCategories(Category category) {
        try {
            int insertStatus = jdbcTemplate.update(dbQueries.getAddCategory(), category.getName());
            if (insertStatus > 0) {
                return Constant.INSERTED_SUCCESSFULLY;
            } else {
                return Constant.CANT_PROCESS_REQUEST;
            }
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<CategorySkillsResponse> getCategoryWithSkills(Integer categoryId) {
        try {
            List<CategorySkillsResponse> categorySkillDetails = new ArrayList<>();
            List<CategoryRes> categorySkills;

            if (!categoryId.equals(0)) {
                categorySkills = jdbcTemplate.query(dbQueries.getCategorySkillsByCategoryId(), BeanPropertyRowMapper.newInstance(CategoryRes.class), categoryId);
            } else {
                categorySkills = jdbcTemplate.query(dbQueries.getAllCategorySkills(), BeanPropertyRowMapper.newInstance(CategoryRes.class));
            }

            List<Integer> categoryIdList = categorySkills.stream()
                    .map(CategoryRes::getCategoryId).distinct().collect(Collectors.toList());
            for (Integer fetchCategoryId : categoryIdList) {
                CategorySkillsResponse categorySkillsResponse = new CategorySkillsResponse();
                categorySkillsResponse.setCategoryId(fetchCategoryId);
                categorySkills.stream().filter(categoryRes -> categoryRes.getCategoryId() == fetchCategoryId)
                        .forEach(categoryRes -> {
                            categorySkillsResponse.setCategoryName(categoryRes.getCategoryName());
                            categorySkillsResponse.setLogoURl(categoryRes.getLogoURl());
                            categorySkillsResponse.getSkills()
                                    .add(SkillRes.builder().skillId(categoryRes.getSkillId()).skillName(categoryRes.getSkillName()).isDeleted(categoryRes.isDeleted())
                                            .createdDate(categoryRes.getCreatedDate()).categoryId(categoryRes.getCategoryId()).build());
                        });
                categorySkillDetails.add(categorySkillsResponse);
            }
            return categorySkillDetails;

        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public String deleteCategory(int categoryId) {
        try {
            int deleteStatus = jdbcTemplate.update(dbQueries.getRemoveCategory(), categoryId);
            if (deleteStatus > 0) {
                return Constant.DELETED_SUCCESSFULLY;
            } else {
                return Constant.CANT_PROCESS_REQUEST;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String updateCategory(Category category, int categoryId) {
        try {
            int updateStatus = jdbcTemplate.update(dbQueries.getUpdateCategory(), category.getName(), categoryId);
            if (updateStatus > 0) {
                return Constant.UPDATED_SUCCESSFULLY;
            } else {
                return Constant.CANT_PROCESS_REQUEST;
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
