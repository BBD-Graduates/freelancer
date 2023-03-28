package com.fl.skill.controller;

import com.fl.skill.config.Constant;
import com.fl.skill.model.CommonResponse;
import com.fl.skill.model.Request.Skill;
import com.fl.skill.model.Response.SkillList;
import com.fl.skill.model.Response.SkillRes;
import com.fl.skill.service.SkillImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillController {

    @Autowired
    private SkillImpl skillrepo;

    @RequestMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("Skills service", HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> createSkills(@Valid @RequestBody Skill skill) {
        try {
            int ins = skillrepo.save(skill);
            if(ins > 0)
                return new ResponseEntity<>(new CommonResponse<String>(Constant.INSERTED_SUCCESSFULLY, HttpStatus.CREATED.value()), HttpStatus.OK);
            else
                return new ResponseEntity<>(new CommonResponse<String>(Constant.CANT_PROCESS_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
        } catch (Exception e)
        {
            return new ResponseEntity<>(new CommonResponse<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @GetMapping("/")
    public ResponseEntity<Object> getSkills()
    {
        try {
            List<SkillRes> skills=new ArrayList<>();
            skillrepo.getAll().forEach(skills::add);
            SkillList res=new SkillList();
            if(!skills.isEmpty())
            {
                res.setSkills(skills);
                return new ResponseEntity<>(new CommonResponse<SkillList>(res,HttpStatus.ACCEPTED.value()),HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(new CommonResponse<String>(Constant.NO_RECORD_FOUND,HttpStatus.ACCEPTED.value()),HttpStatus.OK);
            }
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new CommonResponse<String >(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSkillById(@PathVariable("id") int id)
    {
        try{
            List<SkillRes> skills=new ArrayList<>();
            skillrepo.getById(id).forEach(skills::add);
            SkillList res=new SkillList();

            if(!skills.isEmpty())
            {
                res.setSkills(skills);
                return new ResponseEntity<>(new CommonResponse<SkillList>(res,HttpStatus.ACCEPTED.value()),HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(new CommonResponse<String>(Constant.NO_RECORD_FOUND,HttpStatus.ACCEPTED.value()),HttpStatus.OK);
            }

        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new CommonResponse<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Object> getSkillByCategoryId(@PathVariable("id") int id)
    {
        try{
            List<SkillRes> skills=new ArrayList<>();
            skillrepo.getByCategoryId(id).forEach(skills::add);
            SkillList res=new SkillList();

            if(!skills.isEmpty())
            {
                res.setSkills(skills);
                return new ResponseEntity<>(new CommonResponse<SkillList>(res,HttpStatus.ACCEPTED.value()),HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(new CommonResponse<String>(Constant.NO_RECORD_FOUND,HttpStatus.ACCEPTED.value()),HttpStatus.OK);
            }

        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new CommonResponse<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Object> updateSkill(@PathVariable("id") int id,@Valid @RequestBody Skill skill)
    {
        try{
            List<SkillRes> lstskill = new ArrayList<>();
            skillrepo.getById(id).forEach(lstskill::add);
            if(!lstskill.isEmpty()){
                int updated = skillrepo.update(skill,id);
                if(updated!=0)
                    return new ResponseEntity<>(new CommonResponse<String>(Constant.UPDATED_SUCCESSFULLY,HttpStatus.OK.value()),HttpStatus.OK);
                else
                    return new ResponseEntity<>(new CommonResponse<String>(Constant.CANT_PROCESS_REQUEST,HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);
            }
            else
                return new ResponseEntity<>(new CommonResponse<String>(Constant.NO_RECORD_FOUND,HttpStatus.OK.value()),HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new CommonResponse<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSkill(@PathVariable("id") int id)
    {
        try
        {
            int deleted=skillrepo.delete(id);
            if(deleted==0)
            {
                return new ResponseEntity<>(new CommonResponse<String>(Constant.NO_RECORD_FOUND,HttpStatus.OK.value()),HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(new CommonResponse<String>(Constant.DELETED_SUCCESSFULLY,HttpStatus.OK.value()),HttpStatus.OK);
            }
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new CommonResponse<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
