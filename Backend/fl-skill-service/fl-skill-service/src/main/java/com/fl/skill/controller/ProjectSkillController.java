package com.fl.skill.controller;

import com.fl.skill.config.Constant;
import com.fl.skill.model.CommonResponse;
import com.fl.skill.model.Request.ProjectSkillsReq;
import com.fl.skill.model.Response.*;
import com.fl.skill.service.ProjectSkillsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/project-skills")
public class ProjectSkillController {

    @Autowired
    ProjectSkillsImpl projectSkillsRepo;

    @PostMapping("/add")
    public ResponseEntity<ProjectSkillsReq> addProjectSkills(@RequestBody ProjectSkillsReq projectSkillsReq)
    {
        return new  ResponseEntity<ProjectSkillsReq>(projectSkillsReq,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getProjectSkills()
    {
        try {
            List<ProjectSkills> projects = new ArrayList<>();
            projectSkillsRepo.getAllProjectId().forEach(projects::add);
            List<ProjectSkillList> lst = new ArrayList<>();
            if(!projects.isEmpty())
            {
                for(int i=0;i<projects.size();i++)
                {
                    ProjectSkillList ps;
                    List<ProjectSkills> skillRes=new ArrayList<>();
                    ProjectSkills obj = projects.get(i);
                    projectSkillsRepo.getByProjectId(obj.getProjectId()).forEach(skillRes::add);
                    ps=new ProjectSkillList(obj.getProjectId(), skillRes);
                    lst.add(ps);
                }
                // return new ResponseEntity<>(new CommonResponse<List<ProjectSkillList>>(lst,HttpStatus.ACCEPTED.value()),HttpStatus.OK);
                return new ResponseEntity<>(lst, HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>(new CommonResponse<String>(Constant.NO_RECORD_FOUND, HttpStatus.ACCEPTED.value()),HttpStatus.OK);
            }

        }
        catch (Exception e){
            return new ResponseEntity<>(new CommonResponse<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProjectSkillsById(@PathVariable("id") int id)
    {
        try {
            List<ProjectSkills> projects = new ArrayList<>();
            projectSkillsRepo.getProjectId(id).forEach(projects::add);
            List<ProjectSkillList> lst = new ArrayList<>();
            if(!projects.isEmpty())
            {
                for(int i=0;i<projects.size();i++)
                {
                    ProjectSkillList ps;
                    List<ProjectSkills> skillRes=new ArrayList<>();
                    ProjectSkills obj = projects.get(i);
                    projectSkillsRepo.getByProjectId(obj.getProjectId()).forEach(skillRes::add);
                    ps=new ProjectSkillList(obj.getProjectId(), skillRes);
                    lst.add(ps);
                }
                return new ResponseEntity<>(lst,HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>(new CommonResponse<String>(Constant.NO_RECORD_FOUND, HttpStatus.ACCEPTED.value()),HttpStatus.OK);
            }

        }
        catch (Exception e){
            return new ResponseEntity<>(new CommonResponse<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/skill/{id}")
    public ResponseEntity<Object> getSkillsByProjectId(@PathVariable("id") int id)
    {
        try {
            List<ProjectSkills> projects = new ArrayList<>();
            projectSkillsRepo.getProjectId(id).forEach(projects::add);
            List<ProjectSkills> skillRes=new ArrayList<>();
            if(!projects.isEmpty())
            {
                for(int i=0;i<projects.size();i++)
                {
                    ProjectSkills obj = projects.get(i);
                    projectSkillsRepo.getByProjectId(obj.getProjectId()).forEach(skillRes::add);
                }
                return new ResponseEntity<>(skillRes,HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>(new CommonResponse<String>(Constant.NO_RECORD_FOUND, HttpStatus.ACCEPTED.value()),HttpStatus.OK);
            }

        }
        catch (Exception e){
            return new ResponseEntity<>(new CommonResponse<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
