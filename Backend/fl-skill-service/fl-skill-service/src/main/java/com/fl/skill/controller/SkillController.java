package com.fl.skill.controller;

import com.fl.skill.exceptions.CategoryNotFoundException;
import com.fl.skill.exceptions.SkillNotFoundException;
import com.fl.skill.model.FlResponse;
import com.fl.skill.model.request.Skill;
import com.fl.skill.model.response.CategorySkills;
import com.fl.skill.model.response.SkillRes;
import com.fl.skill.service.serviceInterface.SkillService;
import com.fl.skill.util.FlResponseUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
@RequiredArgsConstructor
public class SkillController {

    private final SkillService skillService;
    private final FlResponseUtil flResponseUtil;

    @PostMapping
    public ResponseEntity<FlResponse<String>> createSkills(@Valid @RequestBody Skill skill) throws SkillNotFoundException {
        return flResponseUtil.getResponseEntity(HttpStatus.OK,skillService.insertSkills(skill),null );
    }

    @GetMapping
    public ResponseEntity<FlResponse<List<SkillRes>>> getSkills(
            @RequestParam(defaultValue = "0", required = false, name = "skillId") Integer skillId,
            @RequestParam(defaultValue = "0", required = false, name = "categoryId") Integer categoryId)
            throws SkillNotFoundException {
        return flResponseUtil.getResponseEntity(HttpStatus.OK,skillService.getSkills(skillId, categoryId), null);
    }

    @GetMapping("/categories")
    public ResponseEntity<FlResponse<List<CategorySkills>>> getAllCategorySkills()
            throws SkillNotFoundException, CategoryNotFoundException {
        return flResponseUtil.getResponseEntity(HttpStatus.OK,skillService.getAllCategorySkills(),null );
    }

    @PutMapping("/{skillId}")
    public ResponseEntity<FlResponse<String>> updateSkill(@PathVariable("skillId") Integer skillId, @Valid @RequestBody Skill skill)
            throws SkillNotFoundException {
        return flResponseUtil.getResponseEntity(HttpStatus.OK,skillService.updateSkill(skill, skillId),null);
    }

    @DeleteMapping("/{skillId}")
    public ResponseEntity<FlResponse<String>> deleteSkill(@PathVariable("skillId") Integer skillId) throws SkillNotFoundException {
        return flResponseUtil.getResponseEntity(HttpStatus.OK,skillService.deleteSkill(skillId),null );
    }

}
