package com.fl.project.controller;

import com.fl.project.config.Constant;
import com.fl.project.model.Request.Project;
import com.fl.project.model.Response.CommonResponse;
import com.fl.project.model.Response.ProjectList;
import com.fl.project.model.Response.ProjectRes;
import com.fl.project.model.Response.ProjectSkill;
import com.fl.project.model.Response.Skill;
import com.fl.project.service.ProjectImpl;
import jakarta.validation.Valid;
import lombok.var;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProjectImpl projectrepo;

    @RequestMapping("/ping")
    public ResponseEntity<String> ping(){return new ResponseEntity<>("Project Service", HttpStatus.OK);}

    @PostMapping("/add")
    public ResponseEntity<CommonResponse> createProject(@Valid @RequestBody Project project){
        try {
            int ins = projectrepo.save(project);
            return new ResponseEntity<>(new CommonResponse<String>(Constant.INSERTED_SUCCESSFULLY,HttpStatus.CREATED.value()),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CommonResponse<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllProjects()
    {
        try {
                List<ProjectRes> projects=projectrepo.getAll();
                // List<ProjectSkill> apiRes = restTemplate.getForObject("http://host.docker.internal:8082/project-skills/", List.class);
                if(!projects.isEmpty())
                {
                    for(int i=0;i<projects.size();i++){
                        String url = "http://divyeshg.bbdnet.bbd.co.za:8082/project-skills/skill/"+projects.get(i).getProjectId();
                        List<Skill> apiRes = restTemplate.getForObject(url, List.class);
                        System.out.println(apiRes);
                        projects.get(i).setSkill(apiRes);
                    }
                    return new ResponseEntity<>(projects,HttpStatus.OK);
                }
                else
                {
                    return new ResponseEntity<>(new CommonResponse<String>(Constant.NO_RECORD_FOUND,HttpStatus.ACCEPTED.value()),HttpStatus.OK);
                }
        } catch (Exception e)
        {
            return new ResponseEntity<>(new CommonResponse<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
