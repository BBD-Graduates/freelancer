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
    private  String allCategories;

    @Value("${db.queries.categories.selectCategoryByCategoryId}")
    private  String categoryByCategoryId;

    @Value("${db.queries.categories.updateCategory}")
    private  String updateCategory;

    @Value("${db.queries.categories.updateCategoryLogo}")
    private  String updateCategoryLogo;

    @Value("${db.queries.categories.removeCategory}")
    private  String removeCategory;

    @Value("${db.queries.skills.insertSkill}")
    private  String addSkill;

    @Value("${db.queries.skills.selectAllSkills}")
    private  String allSkills;

    @Value("${db.queries.skills.selectSkillBySkillId}")
    private  String skillBySkillId;

    @Value("${db.queries.skills.selectSkillByCategoryId}")
    private  String skillByCategoryId;

    @Value("${db.queries.skills.updateSkill}")
    private  String updateSkill;

    @Value("${db.queries.skills.removeSkill}")
    private  String removeSkill;

    @Value("${db.queries.projectSkills.insertProjectSkill}")
    private  String addProjectSkill;

    @Value("${db.queries.projectSkills.selectProjectSkillsByProjectId}")
    private  String projectSkillsByProjectId;

    @Value("${db.queries.projectSkills.selectAllProjectId}")
    private  String allProjectId;

    @Value("${db.queries.projectSkills.selectProjectId}")
    private  String projectId;

    @Value("${db.queries.userSkills.insertUserSkill}")
    private String addUserSkill;

    @Value("${db.queries.userSkills.selectUserSkillsByUserId}")
    private String userSkillsByUserId;

    @Value("${db.queries.userSkills.selectAllUserId}")
    private String allUserId;

    @Value("${db.queries.userSkills.selectUserId}")
    private String UserId;

}
