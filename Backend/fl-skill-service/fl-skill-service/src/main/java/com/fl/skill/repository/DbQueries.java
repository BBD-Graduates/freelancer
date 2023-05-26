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

    @Value("${db.queries.categories.selectAllCategorySkills}")
    private  String allCategorySkills;

    @Value("${db.queries.categories.selectCategorySkillsByCategoryId}")
    private  String categorySkillsByCategoryId;

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

    @Value("${db.queries.projectSkills.selectProjectSkillDetails}")
    private String projectSkillDetails;

    @Value("${db.queries.projectSkills.selectProjectSkillDetailsByProjectId}")
    private String projectSkillDetailsByProjectId;

    @Value("${db.queries.projectSkills.selectProjectSkillDetailsBySkillId}")
    private String projectSkillDetailsBySkillId;

    @Value("${db.queries.projectSkills.selectProjectSkillDetailsByCategoryId}")
    private String projectSkillDetailsByCategoryId;

    @Value("${db.queries.userSkills.insertUserSkill}")
    private String addUserSkill;

    @Value("${db.queries.userSkills.selectUserSkillDetails}")
    private String userSkillDetails;

    @Value("${db.queries.userSkills.selectUserSkillsByUserId}")
    private String userSkillsByUserId;

    @Value("${db.queries.userSkills.selectUserSkillsInSkillIds}")
    private String userSkillsInSkillIds;

    @Value("${db.queries.userSkills.selectUserSkillsByCategoryId}")
    private String userSkillsByCategoryId;

}
