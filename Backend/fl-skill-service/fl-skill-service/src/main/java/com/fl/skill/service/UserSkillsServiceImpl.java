package com.fl.skill.service;

import com.fl.skill.config.Constant;
import com.fl.skill.model.request.UserSkillsReq;
import com.fl.skill.model.response.*;
import com.fl.skill.repository.DbQueries;
import com.fl.skill.service.serviceInterface.UserSkillsService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserSkillsServiceImpl implements UserSkillsService {

    private final JdbcTemplate jdbcTemplate;
    private final DbQueries dbQueries;

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
    public List<UserSkillsResponse> getUserSkills(Integer userId, Integer skillId) {
        try {

            List<UserSkillsResponse> userSkillDetails = new ArrayList<>();
            List<UserSkills> userSkills;
            if (!skillId.equals(0)) {
                String query = dbQueries.getUserSkillsBySkillId();
                userSkills= jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(UserSkills.class), skillId);

            } else if (!userId.equals(0)) {
                String query = dbQueries.getUserSkillDetailsByUserId();
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
