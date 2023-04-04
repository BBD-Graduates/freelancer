package com.fl.skill.service.serviceInterface;

import com.fl.skill.exceptions.UserSkillNotFoundException;
import com.fl.skill.model.request.UserSkillsReq;
import com.fl.skill.model.response.UserSkills;

import java.util.List;

public interface UserSkillsService {

    String insertUserSkills(List<UserSkillsReq> userSkillReqList) throws UserSkillNotFoundException;
    List<UserSkills> getUserSkills(Integer userId) throws UserSkillNotFoundException;
    List<UserSkills> getSkillsByUserId(int userId) throws UserSkillNotFoundException;
    List<UserSkills> getAllUserId();
    List<UserSkills> getUserId(int userId);
}
