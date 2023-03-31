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
    private  String categories;

    @Value("${db.queries.categories.selectCategoryById}")
    private  String category;

    @Value("${db.queries.categories.updateCategory}")
    private  String updateCategory;

    @Value("${db.queries.categories.updateCategoryLogo}")
    private  String updateCategoryLogo;

    @Value("${db.queries.categories.removeCategory}")
    private  String removeCategory;

    @Value("${db.queries.skills.insertSkill}")
    private  String addSkill;

    @Value("${db.queries.skills.selectAllSkills}")
    private  String skills;

    @Value("${db.queries.skills.selectSkillById}")
    private  String skill;

    @Value("${db.queries.skills.selectSkillByCategory}")
    private  String skillsByCategory;

    @Value("${db.queries.skills.updateSkill}")
    private  String updateSkill;

    @Value("${db.queries.skills.removeSkill}")
    private  String removeSkill;

    @Value("${db.queries.projectSkills.insertProjectSkills}")
    private  String addProjectSkills;

    @Value("${db.queries.projectSkills.selectProjectSkillsById}")
    private  String projectSkills;

    @Value("${db.queries.projectSkills.selectAllProjectId}")
    private  String allProjectId;

    @Value("${db.queries.projectSkills.selectProjectId}")
    private  String projectId;

    @Value("${db.queries.userSkills.insertUserSkills}")
    private String addUserSkills;

    @Value("${db.queries.userSkills.selectUserSkillsById}")
    private String userSkills;

    @Value("${db.queries.userSkills.selectAllUserId}")
    private String allUserId;

    @Value("${db.queries.userSkills.selectUserId}")
    private String userId;

}
