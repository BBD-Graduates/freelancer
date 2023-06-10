package com.fl.project.service.schedulerService;

import com.fl.project.model.response.ProjectResponse;
import com.fl.project.repository.DbQueries;
import com.fl.project.service.ProjectImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.fl.project.config.ProjectStatus.*;
@Service
@RequiredArgsConstructor
public class ProjectStatus {
    private final JdbcTemplate jdbcTemplate;
    private final DbQueries dbQueries;
    @Autowired
    ProjectImpl projectImpl;

    @Scheduled(cron = "0 0 * * * *")
    public void updateProjectStatus() {
        List<String> status = new ArrayList<>();
        status.add(POSTED.toString());
        status.add(BID_IN_PROGRESS.toString());

        List<ProjectResponse> projectStatus = projectImpl.getProject(0, 0, 0, 0, 0,status);
        Date currentDate = new Date();

        for (ProjectResponse project : projectStatus) {
            Date bidStartDate = project.getBidStartDate();
            Date bidEndDate = project.getBidEndDate();
            if (project.getStatus().equals(POSTED.toString())) {
                if (currentDate.after(bidStartDate) && currentDate.before(bidEndDate)) {
                    project.setStatus(BID_IN_PROGRESS.toString());
                }
            } else if (project.getStatus().equals(BID_IN_PROGRESS.toString()) && (currentDate.after(bidEndDate))) {
                project.setStatus(BID_COMPLETE.toString());
            }
            else{
                break;
            }
            jdbcTemplate.update(dbQueries.getUpdateProjectStatus(), project.getStatus(),project.getProjectId());
        }
    }

}
