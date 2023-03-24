package com.fl.skill.controller;

import com.fl.skill.config.Constant;
import com.fl.skill.model.CommonResponse;
import com.fl.skill.model.Response.CategoryRes;
import com.fl.skill.model.Response.CategorySkillList;
import com.fl.skill.model.Response.SkillRes;
import com.fl.skill.service.CategoryImpl;
import com.fl.skill.service.SkillImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController

public class CategorySkillController {
    @Autowired
    private CategoryImpl catRepo;
    @Autowired
    private SkillImpl skillRepo;
    @GetMapping("/categorysubcategory")
    public ResponseEntity<CommonResponse> getAllCategorySubCategory(){
        try {
            List<CategoryRes> category = new ArrayList<CategoryRes>();
            catRepo.getAll().forEach(category::add);
            List<CategorySkillList> lst = new ArrayList<CategorySkillList>();
            if(!category.isEmpty()){
                for(int i = 0;i<category.size();i++){

                    CategorySkillList cs = new CategorySkillList();
                    List<SkillRes> skillRes = new ArrayList<SkillRes>();

                    CategoryRes obj = category.get(i);
                    cs.setCategoryId(obj.getCategoryId());
                    cs.setCategoryName(obj.getCategoryName());
                    cs.setLogoURl(obj.getLogoURl());
                    cs.setIsDeleted(obj.isIsDeleted());
                    cs.setCreatedDate(obj.getCreatedDate());
                    skillRepo.getByCategoryId(obj.getCategoryId()).forEach(skillRes::add);
                    cs.setSkillRes(skillRes);
                    lst.add(cs);
                }
                return new ResponseEntity<>(new CommonResponse<List<CategorySkillList>>(lst,HttpStatus.ACCEPTED.value()),HttpStatus.OK);
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
