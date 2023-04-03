package com.fl.skill.controller;

import com.fl.skill.exceptions.UserSkillNotFoundException;
import com.fl.skill.model.FlResponse;
import com.fl.skill.model.request.UserSkillsReq;
import com.fl.skill.model.response.UserSkills;
import com.fl.skill.service.serviceInterface.UserSkillsService;
import com.fl.skill.util.FlResponseUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-skills")
@RequiredArgsConstructor
public class UserSkillController {
    
    private final UserSkillsService userSkillsService;
    private final FlResponseUtil flResponseUtil;
    @PostMapping
    public ResponseEntity<FlResponse<String>> addUserSkills(@RequestBody List<UserSkillsReq> userSkillsReqsList) throws UserSkillNotFoundException{
        return flResponseUtil.getResponseEntity(HttpStatus.OK,userSkillsService.insertUserSkills(userSkillsReqsList),null );
    }
    @GetMapping
    public ResponseEntity<List<UserSkills>> getUserSkills(@RequestParam(defaultValue = "0",required = false) Integer userId)throws UserSkillNotFoundException{
            return new ResponseEntity<>(userSkillsService.getUserSkills(userId),HttpStatus.OK);   
    }
}
