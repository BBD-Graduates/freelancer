package com.fl.skill.service;

import com.fl.skill.config.Constant;
import com.fl.skill.exceptions.UserSkillNotFoundException;
import com.fl.skill.model.request.UserSkillsReq;
import com.fl.skill.model.response.UserSkills;
import com.fl.skill.repository.DbQueries;
import com.fl.skill.service.serviceInterface.UserSkillsService;

import lombok.RequiredArgsConstructor;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSkillsServiceImpl implements UserSkillsService {

    private final JdbcTemplate jdbcTemplate;
    private final DbQueries dbQueries;

    @Override
    public String insertUserSkills(List<UserSkillsReq> userSkillReqList) throws UserSkillNotFoundException {
        try {
            int insertStatus = 0;
            for (UserSkillsReq userSkillsReq:userSkillReqList) {
                insertStatus += jdbcTemplate.update(dbQueries.getAddUserSkill(), userSkillsReq.getUserId(),
                        userSkillsReq.getSkillId());
            }
            if (insertStatus > 0) {
                return Constant.INSERTED_SUCCESSFULLY;
            } else {
                return Constant.CANT_PROCESS_REQUEST;
            }

        } catch (Exception e) {
            throw new UserSkillNotFoundException("Error inserting user skills ");
        }

    }


    @Override
    public List<UserSkills> getUserSkills(Integer userId) throws UserSkillNotFoundException {
        try {
            List<UserSkills> users = new ArrayList<>();
            if (!userId.equals(0)) {
                getUserId(userId).forEach(users::add);
            } else {
                getAllUserId().forEach(users::add);
            }
            List<UserSkills> skillRes = new ArrayList<>();
            if (!users.isEmpty()) {
                for (int i = 0; i < users.size(); i++) {
                    UserSkills obj = users.get(i);
                    getSkillsByUserId(obj.getUserId()).forEach(skillRes::add);
                }
            }
            return skillRes;
        } catch (DataAccessException e) {
            throw new UserSkillNotFoundException("Error fetching user skills " + e);
        }
    }

    @Override
    public List<UserSkills> getSkillsByUserId(int userId) {
        return jdbcTemplate.query(dbQueries.getUserSkillsByUserId(),
                BeanPropertyRowMapper.newInstance(UserSkills.class), userId);
    }

    @Override
    public List<UserSkills> getAllUserId() {
        return jdbcTemplate.query(dbQueries.getAllUserId(), BeanPropertyRowMapper.newInstance(UserSkills.class));
    }

    @Override
    public List<UserSkills> getUserId(int userId) {
        return jdbcTemplate.query(dbQueries.getUserId(),
                BeanPropertyRowMapper.newInstance(UserSkills.class), userId);
    }
}
