package com.fl.project.service;

import com.fl.project.model.Request.Project;
import com.fl.project.model.Response.ProjectRes;
import com.fl.project.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ProjectImpl implements ProjectRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public int save(Project project) {
        return jdbcTemplate.update("insert into projects" +
                "(ClientId,ProjectName,ProjectDescription,IsConfidential,PaymentTypeId,BidStartDate,BidEndDate,MinPrice,MaxPrice)" +
                "values(?,?,?,?,?,?,?,?,?)",
                new Object[]{project.getClientId(),project.getProjectName(),project.getProjectDescription(),project.getIsConfidential()
        ,project.getPaymentTypeId(),project.getBidStartDate(),project.getBidEndDate(),project.getMinPrice(),project.getMaxPrice()});
    }


    @Override
    public List<ProjectRes> getAll() {
        return jdbcTemplate.query("select * from projects", BeanPropertyRowMapper.newInstance(ProjectRes.class));
    }

    @Override
    public List<ProjectRes> getById(int id) {
        return null;
    }

    @Override
    public int update(Project project, int id) {
        return 0;
    }

    @Override
    public int delete(int id) {

        return 0;
    }
}
