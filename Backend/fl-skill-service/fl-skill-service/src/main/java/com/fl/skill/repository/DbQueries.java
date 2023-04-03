package com.fl.skill.repository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@RefreshScope
public class DbQueries {
    @Value("${db.queries.categories.insertCategory}")
    private  String addCategory;

    @Value("${db.queries.categories.selectAllCategories}")
    private  String selectAllCategories;

    @Value("${db.queries.categories.selectCategoryByCategoryId}")
    private  String selectCategoryByCategoryId;

    @Value("${db.queries.categories.updateCategory}")
    private  String updateCategory;

    @Value("${db.queries.categories.updateCategoryLogo}")
    private  String updateCategoryLogo;

    @Value("${db.queries.categories.removeCategoryByCategoryId}")
    private  String removeCategoryByCategoryId;

    @Value("${db.queries.skills.insertSkill}")
    private  String addSkill;

    @Value("${db.queries.skills.selectAllSkills}")
    private  String skills;

    @Value("${db.queries.skills.selectSkillBySkillId}")
    private  String selectSkillBySkillId;

    @Value("${db.queries.skills.selectSkillByCategory}")
    private  String skillsByCategoryId;

    @Value("${db.queries.skills.updateSkill}")
    private  String updateSkill;

    @Value("${db.queries.skills.removeSkillBySkillId}")
    private  String removeSkillBySkillId;

    @Value("${db.queries.projectSkills.insertProjectSkills}")
    private  String addProjectSkills;

    @Value("${db.queries.projectSkills.selectProjectSkillsByProjectId}")
    private  String selectProjectSkillsByProjectId;

    @Value("${db.queries.projectSkills.selectAllProject}")
    private  String selectAllProject;

    @Value("${db.queries.projectSkills.selectProjectByProjectId}")
    private  String selectProjectByProjectId;

    @Value("${db.queries.userSkills.insertUserSkills}")
    private String addUserSkills;

    @Value("${db.queries.userSkills.selectUserSkillsByUserId}")
    private String selectUserSkillsByUserId;

    @Value("${db.queries.userSkills.selectUniqUserId}")
    private String selectUniqUserId;

    @Value("${db.queries.userSkills.selectUniqUserIdByUserId}")
    private String selectUniqUserIdByUserId;

}
