package com.fl.skill.service.serviceInterface;

import com.fl.skill.exceptions.UserSkillNotFoundException;
import com.fl.skill.model.request.UserSkillsReq;
import com.fl.skill.model.response.UserSkills;

import java.util.List;

public interface UserSkillsService {

    String insertUserSkills(List<UserSkillsReq> userSkillReqList) throws UserSkillNotFoundException;
    List<UserSkills> getallUserSkills();
    List<UserSkills> getUserSkills(Integer id) throws UserSkillNotFoundException;
    List<UserSkills> getSkillsByUserId(int id);
    List<UserSkills> getAllUniqUserId();
    List<UserSkills> getUniqUserIdByUserId(int id);
}
