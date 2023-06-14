package com.fl.project.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@RefreshScope
public class DbQueries {
    @Value("${db.queries.projects.insertProject}")
    private String addProject;

    @Value("${db.queries.projects.selectAllProject}")
    private String selectAllProject;

    @Value("${db.queries.projects.selectAllStatusProjects}")
    private String allStatusProjects;

    @Value("${db.queries.projects.selectProjectByProjectId}")
    private String selectProjectByProjectId;

    @Value("${db.queries.projects.selectAssignedProjectsByProjectIds}")
    private String assignedProjectsByProjectIds;

    @Value("${db.queries.projects.selectProjectsByClientIdAndStatus}")
    private String selectProjectsByClientIdAndStatus;

    @Value("${db.queries.projects.selectProjectByProjectIds}")
    private String selectProjectByProjectIds;
    
    @Value("${db.queries.projects.updateProjectByProjectId}")
    private String updateProjectByProjectId;

    @Value("${db.queries.projects.updateProjectStatus}")
    private String updateProjectStatus;

    @Value("${db.queries.projects.deleteProjectByProjectId}")
    private String deleteProjectByProjectId;
    
    @Value("${db.queries.paymentType.insertPaymentType}")
    private String addPaymentType;
    
    @Value("${db.queries.paymentType.selectAllPaymentType}")
    private String selectAllPaymentType;

    @Value("${db.queries.rating.insertRating}")
    private String addRating;

    @Value("${db.queries.rating.selectAllRating}")
    private String allRating;

    @Value("${db.queries.rating.selectRatingByRatingId}")
    private String ratingByRatingId;

    @Value("${db.queries.rating.selectRatingByUserId}")
    private String ratingByUserId;

    @Value("${db.queries.rating.selectRatingByProjectId}")
    private String ratingByProjectId;

    @Value("${db.queries.rating.updateRatingByRatingId}")
    private String updateRatingByRatingId;

    @Value("${db.queries.rating.removeRatingByRatingId}")
    private String removeRatingByRatingId;

    @Value("${db.queries.projectAssignment.assignBid}")
    private String assignBid;
}
