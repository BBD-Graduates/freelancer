package com.fl.skill.controller;

import com.fl.skill.model.request.UserSkillsReq;
import com.fl.skill.model.response.UserSkills;
import com.fl.skill.service.serviceInterface.UserSkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-skills")
public class UserSkillController {
    @Autowired
    UserSkillsService userSkillsService;
    @PostMapping
    public ResponseEntity<String> addUserSkills(@RequestBody List<UserSkillsReq> userSkillsReqsList)
    {
        return new ResponseEntity<>(userSkillsService.insertUserSkills(userSkillsReqsList), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<UserSkills>> getUserSkills(@RequestParam(defaultValue = "0",required = false) Integer userId)
    {
        if(!userId.equals(0))
        {
            return new ResponseEntity<>(userSkillsService.userSkillsByUserId(userId),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(userSkillsService.getallUserSkills(),HttpStatus.OK);
        }
    }
}
