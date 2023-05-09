package com.fl.skill.service;

import com.fl.skill.config.Constant;
import com.fl.skill.model.request.Skill;
import com.fl.skill.model.response.SkillRes;
import com.fl.skill.repository.DbQueries;
import com.fl.skill.service.serviceInterface.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final DbQueries dbQueries;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public String insertSkills(List<Skill> skillList) {
        try {
            int[] insertStatus = jdbcTemplate.batchUpdate(dbQueries.getAddSkill(),
                    new BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps, int row) throws SQLException {
                            ps.setString(1, skillList.get(row).getSkillName());
                            ps.setInt(2, skillList.get(row).getCategoryId());
                        }
                        @Override
                        public int getBatchSize() {
                            return skillList.size();
                        }
                    });
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
    public List<SkillRes> getSkills(Integer skillId, Integer categoryId) {

        try {
            if (!skillId.equals(0)) {
                return jdbcTemplate.query(dbQueries.getSkillBySkillId(),
                        BeanPropertyRowMapper.newInstance(SkillRes.class), skillId);
            } else if (!categoryId.equals(0)) {
                return jdbcTemplate.query(dbQueries.getSkillByCategoryId(), BeanPropertyRowMapper.newInstance(SkillRes.class), categoryId);
            } else {
                return jdbcTemplate.query(dbQueries.getAllSkills(), BeanPropertyRowMapper.newInstance(SkillRes.class));
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String updateSkill(Skill skill, int skillId) {
        try {
            int updateStatus = jdbcTemplate.update(dbQueries.getUpdateSkill(), skill.getSkillName(),
                    skill.getCategoryId(), skillId);
            if (updateStatus > 0) {
                return Constant.UPDATED_SUCCESSFULLY;
            } else {
                return Constant.CANT_PROCESS_REQUEST;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String deleteSkill(int skillId) {
        try {
            int removeStatus = jdbcTemplate.update(dbQueries.getRemoveSkill(), skillId);
            if (removeStatus > 0) {
                return Constant.DELETED_SUCCESSFULLY;
            } else {
                return Constant.CANT_PROCESS_REQUEST;
            }
        } catch (Exception e) {
            throw e;
        }
    }
}

