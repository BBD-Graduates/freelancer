package com.fl.skill.controller;

//import com.fl.skill.config.ResponseEntity;
import com.fl.skill.config.Constant;
import com.fl.skill.model.CommonResponse;
import com.fl.skill.model.Request.Category;
//import com.fl.skill.model.Response;
import com.fl.skill.model.Response.CategoryList;
import com.fl.skill.model.Response.CategoryRes;
import com.fl.skill.service.CategoryImpl;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
@Autowired
    private CategoryImpl catRepo;
    @RequestMapping("ping")
    public ResponseEntity<String> ping(){
        return new ResponseEntity<>("Category microservice", HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CommonResponse> createCategory(@Valid @RequestBody Category category){
        try {
            int ins = catRepo.save(category.getName());
            if(ins>0){
                return new ResponseEntity<>(new CommonResponse<String>("Inserted",HttpStatus.CREATED.value()),HttpStatus.OK);
            }
//                return new ResponseEntity<>("Inserted",HttpStatus.CREATED);
            else{
                return new ResponseEntity<>(new CommonResponse<String>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
            }
//                return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e){
//            return  new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(new CommonResponse<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<CommonResponse> getAllCategory(){
        try {
            List<CategoryRes> category = new ArrayList<CategoryRes>();
            catRepo.getAll().forEach(category::add);
            CategoryList res = new CategoryList();
            if(!category.isEmpty()){
                res.setCategories(category);
                return new ResponseEntity<>(new CommonResponse<CategoryList>(res,HttpStatus.ACCEPTED.value()),HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>(new CommonResponse<String>(Constant.NO_RECORD_FOUND,HttpStatus.ACCEPTED.value()),HttpStatus.OK);
            }

        }
        catch (Exception e){
            return new ResponseEntity<>(new CommonResponse<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<CommonResponse> updateCategory(@PathVariable("id") int id,@Valid @RequestBody Category category){
        try {
            List<CategoryRes> lstcategory = new ArrayList<CategoryRes>();
            catRepo.getById(id).forEach(lstcategory::add);
            CategoryList res = new CategoryList();
            if(!lstcategory.isEmpty()){
                int updated = catRepo.update(category,id);
                if(updated!=0)
                    return new ResponseEntity<>(new CommonResponse<String>(Constant.UPDATED_SUCCESSFULLY,HttpStatus.OK.value()),HttpStatus.OK);
                else
                    return new ResponseEntity<>(new CommonResponse<String>(Constant.CANT_PROCESS_REQUEST,HttpStatus.OK.value()),HttpStatus.OK);
            }
            else
                return new ResponseEntity<>(new CommonResponse<String>(Constant.NO_RECORD_FOUND,HttpStatus.OK.value()),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new CommonResponse<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse> getCategoryById(@PathVariable("id") int id){
        try {
            List<CategoryRes> category = new ArrayList<CategoryRes>();
            catRepo.getById(id).forEach(category::add);
            CategoryList res = new CategoryList();
            if(!category.isEmpty()){
                res.setCategories(category);
                return new ResponseEntity<>(new CommonResponse<CategoryList>(res,HttpStatus.ACCEPTED.value()),HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>(new CommonResponse<String>(Constant.NO_RECORD_FOUND,HttpStatus.ACCEPTED.value()),HttpStatus.OK);
            }

        }
        catch (Exception e){
            return new ResponseEntity<>(new CommonResponse<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse> deleteCategory(@PathVariable("id") int id){
        try{
            int deleted = catRepo.delete(id);
            if(deleted == 0){
                return new ResponseEntity<>(new CommonResponse<String>(Constant.NO_RECORD_FOUND,HttpStatus.OK.value()),HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(new CommonResponse<String>(Constant.DELETED_SUCCESSFULLY,HttpStatus.OK.value()),HttpStatus.OK);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(new CommonResponse<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
