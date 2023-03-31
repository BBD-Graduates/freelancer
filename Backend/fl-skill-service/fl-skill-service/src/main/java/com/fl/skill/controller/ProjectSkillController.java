package com.fl.skill.controller;

import com.fl.skill.model.request.ProjectSkillsReq;
import com.fl.skill.model.response.ProjectSkills;
import com.fl.skill.service.serviceInterface.ProjectSkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project-skills")
public class ProjectSkillController {

    @Autowired
    ProjectSkillsService projectSkillsService;

    @PostMapping
    public ResponseEntity<String> addProjectSkills(@RequestBody List<ProjectSkillsReq> projectSkillReqList)
    {
        return new ResponseEntity<>(projectSkillsService.insertProjectSkills(projectSkillReqList),HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<ProjectSkills>> getProjectSkills(@RequestParam(defaultValue = "0",required = false) Integer projectId)
    {
        if(!projectId.equals(0))
        {
            return new ResponseEntity<>(projectSkillsService.projectSkillsBySkillId(projectId),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(projectSkillsService.getallProjectSkills(),HttpStatus.OK);
        }

    }


}
