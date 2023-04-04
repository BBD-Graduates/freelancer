package com.fl.skill.controller;

import com.fl.skill.exceptions.ProjectNotFoundException;
import com.fl.skill.model.FlResponse;
import com.fl.skill.model.request.ProjectSkillsReq;
import com.fl.skill.model.response.ProjectSkills;
import com.fl.skill.service.serviceInterface.ProjectSkillsService;
import com.fl.skill.util.FlResponseUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project-skills")
@RequiredArgsConstructor
public class ProjectSkillController {

    private final ProjectSkillsService projectSkillsService;
    private final FlResponseUtil flResponseUtil;

    @PostMapping
    public ResponseEntity<FlResponse<String>> addProjectSkills(@RequestBody List<ProjectSkillsReq> projectSkillReqList) throws ProjectNotFoundException
    {
        return new FlResponseUtil().getResponseEntity(HttpStatus.OK,projectSkillsService.insertProjectSkills(projectSkillReqList),null);

    }

    @GetMapping
    public ResponseEntity<FlResponse<List<ProjectSkills>>> getProjectSkills(@RequestParam(defaultValue = "0",required = false) Integer projectId) throws ProjectNotFoundException
    {
        return flResponseUtil.getResponseEntity(HttpStatus.OK,projectSkillsService.getAllProjectSkills(projectId),null);

    }


}
