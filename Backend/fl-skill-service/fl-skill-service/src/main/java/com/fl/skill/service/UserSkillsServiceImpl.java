package com.fl.skill.service;

import com.fl.skill.config.Constant;
import com.fl.skill.model.request.UserSkillsReq;
import com.fl.skill.model.response.UserSkills;
import com.fl.skill.repository.DbQueries;
import com.fl.skill.service.serviceInterface.UserSkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSkillsServiceImpl implements UserSkillsService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DbQueries dbQueries;


    @Override
    public String insertUserSkills(List<UserSkillsReq> userSkillReqList) {
        try {
            int insertStatus=0;
            for(int i=0;i<userSkillReqList.size();i++)
            {
                UserSkillsReq userSkillsReq=new UserSkillsReq();
                UserSkillsReq obj=userSkillReqList.get(i);
                userSkillsReq.setUserId(obj.getUserId());
                userSkillsReq.setSkillId(obj.getSkillId());
                insertStatus = jdbcTemplate.update(dbQueries.getAddUserSkills(),userSkillsReq.getUserId(),userSkillsReq.getSkillId());
            }
            if (insertStatus > 0)
            {
                return Constant.INSERTED_SUCCESSFULLY;
            } else {
                return Constant.CANT_PROCESS_REQUEST;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<UserSkills> getallUserSkills() {
        return null;
    }

    @Override
    public List<UserSkills> userSkillsByUserId(Integer id) {
        try {
            List<UserSkills> users = new ArrayList<>();
            getUserId(id).forEach(users::add);
            List<UserSkills> skillRes=new ArrayList<>();
            if(!users.isEmpty())
            {
                for(int i=0;i<users.size();i++)
                {
                    UserSkills obj = users.get(i);
                    getSkillsByUserId(obj.getUserId()).forEach(skillRes::add);
                }
            }
            return skillRes;
        }
        catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public List<UserSkills> getSkillsByUserId(int id) {
        return jdbcTemplate.query(dbQueries.getUserSkills(), BeanPropertyRowMapper.newInstance(UserSkills.class), id);
    }

    @Override
    public List<UserSkills> getAllUserId() {
        return jdbcTemplate.query(dbQueries.getAllUserId(), BeanPropertyRowMapper.newInstance(UserSkills.class));
    }

    @Override
    public List<UserSkills> getUserId(int id) {
        return jdbcTemplate.query(dbQueries.getUserId(), BeanPropertyRowMapper.newInstance(UserSkills.class), id);
    }
}
