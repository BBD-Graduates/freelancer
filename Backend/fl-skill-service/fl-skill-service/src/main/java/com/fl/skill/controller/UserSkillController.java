package com.fl.skill.controller;

import com.fl.skill.model.FlResponse;
import com.fl.skill.model.request.UserSkillsReq;
import com.fl.skill.model.response.UserSkillsResponse;
import com.fl.skill.service.serviceInterface.UserSkillsService;
import com.fl.skill.util.FlResponseUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fl.skill.config.Constant.*;

@RestController
@RequestMapping("/fls/user-skills")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserSkillController {

    private final UserSkillsService userSkillsService;
    private final FlResponseUtil flResponseUtil;

    @PostMapping
    public ResponseEntity<FlResponse<String>> addUserSkills(@RequestBody List<UserSkillsReq> userSkillsReqsList) {

        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, userSkillsService.insertUserSkills(userSkillsReqsList), String.format("%s" + INSERTED_SUCCESSFULLY, USER_SKILLS));
        } catch (Exception e) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null, String.format("%s " + INSERTION_FAILED, USER_SKILLS));
        }
    }

    @GetMapping
    public ResponseEntity<FlResponse<List<UserSkillsResponse>>> getUserSkills(@RequestParam(name = "userId", required = false) Integer userId,
                                                                              @RequestParam(name = "skillIds", required = false)  List<Integer> skillIds,
                                                                              @RequestParam(name = "categoryId", required = false)Integer categoryId) {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, userSkillsService.getUserSkills(userId,skillIds,categoryId), String.format("%s" + FETCHED_SUCCESSFULLY, USER_SKILLS));
        } catch (Exception e) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null, String.format("%s " + NO_RECORD_FOUND));
        }
        }
}
