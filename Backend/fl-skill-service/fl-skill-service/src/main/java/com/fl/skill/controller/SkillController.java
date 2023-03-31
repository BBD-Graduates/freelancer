package com.fl.skill.controller;

import com.fl.skill.model.request.Skill;
import com.fl.skill.model.response.CategorySkills;
import com.fl.skill.model.response.SkillRes;
import com.fl.skill.service.serviceInterface.SkillService;
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

    @PostMapping
    public ResponseEntity<String> createSkills(@Valid @RequestBody Skill skill) {
        return new ResponseEntity<>(skillService.insertSkills(skill), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SkillRes>> getSkills(@RequestParam(defaultValue = "0", required = false, name = "skillId") Integer skillId, @RequestParam(defaultValue = "0", required = false, name = "categoryId") Integer categoryId) {
        if (!skillId.equals(0)) {
            return new ResponseEntity<>(skillService.getSkillBySkillId(skillId), HttpStatus.OK);
        } else if (!categoryId.equals(0)) {

            return new ResponseEntity<>(skillService.getSkillByCategoryId(categoryId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(skillService.getAllSkills(), HttpStatus.OK);
        }
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategorySkills>> getAllCategorySkills() {
        return new ResponseEntity<>(skillService.getAllCategorySkills(), HttpStatus.OK);
    }

    @PutMapping("/{skillId}")
    public ResponseEntity<String> updateSkill(@PathVariable("skillId") Integer skillId, @Valid @RequestBody Skill skill) {
        return new ResponseEntity<>(skillService.updateSkill(skill, skillId), HttpStatus.OK);
    }

    @DeleteMapping("/{skillId}")
    public ResponseEntity<String> deleteSkill(@PathVariable("skillId") Integer skillId) {
        return new ResponseEntity<>(skillService.deleteSkill(skillId), HttpStatus.OK);
    }

}
