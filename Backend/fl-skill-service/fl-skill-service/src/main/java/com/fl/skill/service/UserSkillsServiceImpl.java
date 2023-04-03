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
            for (int i = 0; i < userSkillReqList.size(); i++) {
                UserSkillsReq userSkillsReq = new UserSkillsReq();
                UserSkillsReq obj = userSkillReqList.get(i);
                userSkillsReq.setUserId(obj.getUserId());
                userSkillsReq.setSkillId(obj.getSkillId());
                insertStatus = jdbcTemplate.update(dbQueries.getAddUserSkills(), userSkillsReq.getUserId(),
                        userSkillsReq.getSkillId());
            }
            if (insertStatus > 0) {
                return Constant.INSERTED_SUCCESSFULLY;
            } else {
                return Constant.CANT_PROCESS_REQUEST;
            }

        } catch (Exception e) {
            throw new UserSkillNotFoundException("Error to insert user skill ");
        }

    }

    @Override
    public List<UserSkills> getallUserSkills() {
        try {
            List<UserSkills> users = new ArrayList<>();
            getAllUniqUserId().forEach(users::add);
            List<UserSkills> skillRes = new ArrayList<>();
            if (!users.isEmpty()) {
                for (int i = 0; i < users.size(); i++) {
                    UserSkills obj = users.get(i);
                    getSkillsByUserId(obj.getUserId()).forEach(skillRes::add);
                }
            }
            return skillRes;
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public List<UserSkills> getUserSkills(Integer id) throws UserSkillNotFoundException {
        try {
            List<UserSkills> users = new ArrayList<>();
            if (!id.equals(0)) {
                getUniqUserIdByUserId(id).forEach(users::add);
            } else {
                getAllUniqUserId().forEach(users::add);
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
            throw new UserSkillNotFoundException("Error to fetch user skill " + e);
        }
    }

    @Override
    public List<UserSkills> getSkillsByUserId(int id) {
        return jdbcTemplate.query(dbQueries.getSelectUserSkillsByUserId(),
                BeanPropertyRowMapper.newInstance(UserSkills.class), id);
    }

    @Override
    public List<UserSkills> getAllUniqUserId() {
        return jdbcTemplate.query(dbQueries.getSelectUniqUserId(), BeanPropertyRowMapper.newInstance(UserSkills.class));
    }

    @Override
    public List<UserSkills> getUniqUserIdByUserId(int id) {
        return jdbcTemplate.query(dbQueries.getSelectUniqUserIdByUserId(),
                BeanPropertyRowMapper.newInstance(UserSkills.class), id);
    }
}
