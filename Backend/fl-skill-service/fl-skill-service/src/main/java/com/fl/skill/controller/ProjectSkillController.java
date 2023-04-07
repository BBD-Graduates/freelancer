package com.fl.skill.controller;

import com.fl.skill.model.FlResponse;
import com.fl.skill.model.request.ProjectSkillsReq;
import com.fl.skill.model.response.ProjectSkillsResponse;
import com.fl.skill.service.serviceInterface.ProjectSkillsService;
import com.fl.skill.util.FlResponseUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fl.skill.config.Constant.*;

@RestController
@RequestMapping("/project-skills")
@RequiredArgsConstructor
public class ProjectSkillController {
    private final ProjectSkillsService projectSkillsService;
    private final FlResponseUtil flResponseUtil;

    @PostMapping
    public ResponseEntity<FlResponse<String>> addProjectSkills(@RequestBody List<ProjectSkillsReq> projectSkillReqList) {
        try {
            return new FlResponseUtil().getResponseEntity(HttpStatus.OK, projectSkillsService.insertProjectSkills(projectSkillReqList), String.format("%s" + INSERTED_SUCCESSFULLY, PROJECT_SKILLS));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public ResponseEntity<FlResponse<List<ProjectSkillsResponse>>> getProjectSkills(@RequestParam(defaultValue = "0", required = false) Integer projectId) {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, projectSkillsService.getProjectSkills(projectId), String.format("%s" + FETCHED_SUCCESSFULLY, PROJECT_SKILLS));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
