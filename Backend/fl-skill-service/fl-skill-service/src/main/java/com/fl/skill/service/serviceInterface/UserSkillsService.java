package com.fl.skill.service.serviceInterface;

import com.fl.skill.model.request.UserSkillsReq;
import com.fl.skill.model.response.UserSkills;

import java.util.List;

public interface UserSkillsService {

    String insertUserSkills(List<UserSkillsReq> userSkillReqList);
    List<UserSkills> getallUserSkills();
    List<UserSkills> userSkillsByUserId(Integer id);
    List<UserSkills> getSkillsByUserId(int id);
    List<UserSkills> getAllUserId();
    List<UserSkills> getUserId(int id);
}
