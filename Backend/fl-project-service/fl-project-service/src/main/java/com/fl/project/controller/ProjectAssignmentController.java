package com.fl.project.controller;

import com.fl.project.model.FlResponse;
import com.fl.project.model.request.ProjectAssignmentRequest;
import com.fl.project.service.serviceInterface.ProjectAssignmentService;
import com.fl.project.util.FlResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.fl.project.config.Constant.*;

@RestController
@RequestMapping("/projectassignment")
@RequiredArgsConstructor
public class ProjectAssignmentController {
    private final ProjectAssignmentService projectAssignment;
    private final FlResponseUtil flResponseUtil;
    @PostMapping
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
