package com.fl.skill.controller;

import com.fl.skill.model.FlResponse;
import com.fl.skill.model.request.Skill;
import com.fl.skill.model.response.SkillRes;
import com.fl.skill.service.serviceInterface.SkillService;
import com.fl.skill.util.FlResponseUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fl.skill.config.Constant.*;

@RestController
@RequestMapping("/fls/skills")
@RequiredArgsConstructor
@CrossOrigin("*")
public class SkillController {

    private final SkillService skillService;
    private final FlResponseUtil flResponseUtil;

    @PostMapping
    public ResponseEntity<FlResponse<String>> createSkills(@Valid @RequestBody List<Skill> skillList) {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, skillService.insertSkills(skillList), String.format("%s" + INSERTED_SUCCESSFULLY, SKILL));
        } catch (Exception e) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null, String.format("%s " + INSERTION_FAILED, SKILL));
        }
    }

    @GetMapping
    public ResponseEntity<FlResponse<List<SkillRes>>> getSkills(
            @RequestParam(defaultValue = "0", required = false, name = "skillId") Integer skillId,
            @RequestParam(defaultValue = "0", required = false, name = "categoryId") Integer categoryId) {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, skillService.getSkills(skillId, categoryId), String.format("%s" + FETCHED_SUCCESSFULLY, SKILL));
        } catch (Exception e) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null, String.format("%s " + NO_RECORD_FOUND));
        }
    }

    @PutMapping("/{skillId}")
    public ResponseEntity<FlResponse<String>> updateSkill(@PathVariable("skillId") Integer skillId, @Valid @RequestBody Skill skill) {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, skillService.updateSkill(skill, skillId), String.format("%s" + UPDATED_SUCCESSFULLY, SKILL));
        } catch (Exception e) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null, String.format("%s " + UPDATION_FAILED, SKILL));
        }
    }

    @DeleteMapping("/{skillId}")
    public ResponseEntity<FlResponse<String>> deleteSkill(@PathVariable("skillId") Integer skillId) {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, skillService.deleteSkill(skillId), String.format("%s" + DELETED_SUCCESSFULLY, SKILL));
        } catch (Exception e) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null, String.format("%s " + DELETION_FAILED, SKILL));
        }
    }

}
