package com.fl.project.controller;

import com.fl.project.config.Constant;
import com.fl.project.model.Request.Project;
import com.fl.project.model.Response.CommonResponse;
import com.fl.project.model.Response.ProjectList;
import com.fl.project.model.Response.ProjectRes;
import com.fl.project.service.ProjectImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
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
    public ResponseEntity<CommonResponse> getAllProjects()
    {
        try {
                List<ProjectRes> projects=new ArrayList<>();
                projects=projectrepo.getAll();
                if(!projects.isEmpty())
                {
                    ProjectList res=new ProjectList();
                    res.setProjects(projects);

                    return new ResponseEntity<>(new CommonResponse<ProjectList>(res,HttpStatus.ACCEPTED.value()),HttpStatus.OK);
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
