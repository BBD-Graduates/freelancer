package com.fl.skill.service;

import com.fl.skill.config.Constant;
import com.fl.skill.model.request.UserSkillsReq;
import com.fl.skill.model.response.SkillRes;
import com.fl.skill.model.response.UserSkills;
import com.fl.skill.model.response.UserSkillsResponse;
import com.fl.skill.repository.DbQueries;
import com.fl.skill.service.serviceInterface.UserSkillsService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSkillsServiceImpl implements UserSkillsService {

    private final JdbcTemplate jdbcTemplate;
    private final DbQueries dbQueries;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public String insertUserSkills(List<UserSkillsReq> userSkillReqList) {
        try {
            int[] insertStatus = jdbcTemplate.batchUpdate(dbQueries.getAddUserSkill(),
                    new BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps, int row) throws SQLException {
                            ps.setInt(1, userSkillReqList.get(row).getUserId());
                            ps.setInt(2, userSkillReqList.get(row).getSkillId());
                        }

                        @Override
                        public int getBatchSize() {
                            return userSkillReqList.size();
                        }
                    }
            );
            if (insertStatus.length > 0) {
                return Constant.INSERTED_SUCCESSFULLY;
            } else {
                return Constant.CANT_PROCESS_REQUEST;
            }

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<UserSkillsResponse> getUserSkills(Integer userId, List<Integer> skillIds, Integer categoryId) {
        try {
            List<UserSkillsResponse> userSkillDetails = new ArrayList<>();
            List<UserSkills> userSkills;
            if (skillIds != null) {
                String query = dbQueries.getUserSkillsInSkillIds();
                SqlParameterSource parameters=new MapSqlParameterSource("skillIds",skillIds);
                userSkills = namedParameterJdbcTemplate.query(query, parameters,BeanPropertyRowMapper.newInstance(UserSkills.class));
            } else if (categoryId != null) {
                String query = dbQueries.getUserSkillsByCategoryId();
                userSkills = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(UserSkills.class), categoryId);
            } else if (userId != null) {
                String query = dbQueries.getUserSkillsByUserId();
                userSkills = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(UserSkills.class), userId);
            } else {
                String query = dbQueries.getUserSkillDetails();
                userSkills = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(UserSkills.class));
            }
            List<Integer> userIdList = userSkills.stream().map(UserSkills::getUserId).distinct().toList();
            for (Integer fetchUserId : userIdList) {
                UserSkillsResponse userSkillsResponse = new UserSkillsResponse();
                userSkillsResponse.setUserId(fetchUserId);
                userSkills.stream().filter(userSkill -> userSkill.getUserId() == fetchUserId).forEach(userSkill -> userSkillsResponse.getSkills()
                        .add(SkillRes.builder().skillId(userSkill.getSkillId()).skillName(userSkill.getSkillName()).categoryId(userSkill.getCategoryId())
                                .build()));
                userSkillDetails.add(userSkillsResponse);
            }
            return userSkillDetails;

        } catch (DataAccessException e) {
            throw e;
        }
    }
}
