package com.fl.skill.controller;

import com.fl.skill.model.Request.Skill;
import com.fl.skill.repository.SkillRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/skills")
public class SkillController {

    @Autowired
    private SkillRepository skillrepo;

    @RequestMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("Skills service", HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> createSkills(@Valid @RequestBody Skill skill) {
        return new ResponseEntity<>(skillrepo.save(skill), HttpStatus.OK);
    }

    @GetMapping("/skillId={skillId}/categoryId={categoryId}")
    public ResponseEntity<Object> getSkills(@RequestParam(defaultValue = "0",required = false) Integer skillId,@RequestParam(defaultValue = "0",required = false) Integer categoryId)
    {
            if(!skillId.equals(0))
            {
                return new ResponseEntity<>(skillrepo.getById(skillId),HttpStatus.OK);
            }
            else if(!categoryId.equals(0))
            {

                return new ResponseEntity<>(skillrepo.getByCategoryId(categoryId),HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>(skillrepo.getAll(),HttpStatus.OK);
            }
        }

    @GetMapping("/categoryskills")
    public ResponseEntity<Object> getAllCategoryskills(){
        return new ResponseEntity<>(skillrepo.getAllCategorySkills(),HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Object> updateSkill(@PathVariable("id") Integer id,@Valid @RequestBody Skill skill)
    {
        return new ResponseEntity<>(skillrepo.update(skill,id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteSkill(@PathVariable("id") Integer id)
    {
        return new ResponseEntity<>(skillrepo.delete(id), HttpStatus.OK);
    }

}
