package com.fl.project.service;

import com.fl.project.repository.DbQueries;
import com.fl.project.service.serviceInterface.ProjectAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import static com.fl.project.config.Constant.*;

@Service
@RequiredArgsConstructor
public class ProjectAssignmentImpl implements ProjectAssignmentService {
    private final JdbcTemplate jdbcTemplate;
    private final DbQueries dbQueries;

    @Override
    public String assignBid(int bidid) {
        try {
            int inserted = jdbcTemplate.update(dbQueries.getAssignBid(), bidid);
            if (inserted > 0) {
                return INSERTED_SUCCESSFULLY;
            } else {
                return CANT_PROCESS_REQUEST;
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
