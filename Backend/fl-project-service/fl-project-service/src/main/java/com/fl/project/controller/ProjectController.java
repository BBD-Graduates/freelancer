package com.fl.project.controller;

import com.fl.project.model.FlResponse;
import com.fl.project.model.request.ProjectAssignmentRequest;
import com.fl.project.model.request.ProjectRequest;
import com.fl.project.model.response.ProjectResponse;
import com.fl.project.service.serviceInterface.ProjectAssignmentService;
import com.fl.project.service.serviceInterface.ProjectService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.fl.project.util.FlResponseUtil;
import static com.fl.project.config.Constant.*;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectAssignmentService projectAssignment;
    private final FlResponseUtil flResponseUtil;

    @PostMapping
    public ResponseEntity<FlResponse<String>> createProject(@Valid @RequestBody ProjectRequest project) {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, projectService.saveProject(project),
                    PROJECT+INSERTED_SUCCESSFULLY);
        } catch (Exception e) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null,
                    String.format("%s " + INSERTION_FAILED, PROJECT));
        }
    }

    @GetMapping
    public ResponseEntity<FlResponse<List<ProjectResponse>>> getProject(
            @RequestParam(defaultValue = "0", required = false, name = "projectId") Integer projectId,
            @RequestParam(defaultValue = "0", required = false, name = "skillId") Integer skillId,
            @RequestParam(defaultValue = "0", required = false, name = "clientId") Integer clientId,
            @RequestParam(defaultValue = "", required = false, name = "status") List<String> status,
            @RequestParam(defaultValue = "0", required = false, name = "categoryId") Integer categoryId) {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, projectService.getProject(projectId,skillId,categoryId,clientId,status),
                    String.format("%s" + FETCHED_SUCCESSFULLY, PROJECT));
        } catch (Exception e) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null,
                    String.format(NO_RECORD_FOUND));
        }
        }

    @PutMapping("/{projectId}")
    public ResponseEntity<FlResponse<String>> updateProject(@PathVariable("projectId") int projectId,
            @Valid @RequestBody ProjectRequest project) {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, projectService.updateProject(project, projectId),
                    String.format("%s" + UPDATED_SUCCESSFULLY, PROJECT));
        } catch (Exception ex) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null,
                    String.format("%s " + UPDATION_FAILED, PROJECT));
        }
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<FlResponse<String>> deleteProject(@PathVariable("projectId") int projectId) {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, projectService.deleteProject(projectId),
                    String.format("%s" + DELETED_SUCCESSFULLY, PROJECT));
        } catch (Exception ex) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null,
                    String.format("%s " + DELETION_FAILED, PROJECT));
        }
    }
    @PostMapping("/bids")
    public ResponseEntity<FlResponse<String>> assignProjectBid(@Valid @RequestBody ProjectAssignmentRequest projectAssignmentRequest) {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, projectAssignment.assignBid(projectAssignmentRequest.getBidId()),
                    PROJECT_ASSIGN+INSERTED_SUCCESSFULLY);
        } catch (Exception e) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null,
                    String.format("%s " + INSERTION_FAILED, PROJECT));
        }
    }
}
