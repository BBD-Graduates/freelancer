package com.fl.skill.service.serviceInterface;

import com.fl.skill.model.request.Skill;
import com.fl.skill.model.response.CategorySkillsResponse;
import com.fl.skill.model.response.SkillRes;

import java.util.List;

public interface SkillService {

    String insertSkills(List<Skill> skillList );
    List<SkillRes> getSkills(Integer skillId,Integer categoryId);
    String updateSkill(Skill skill, int skillId);
    String deleteSkill(int skillId);
}
