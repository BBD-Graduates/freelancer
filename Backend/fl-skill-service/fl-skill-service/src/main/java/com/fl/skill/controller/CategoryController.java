package com.fl.skill.controller;

import com.fl.skill.config.Constant;
import com.fl.skill.model.CommonResponse;
import com.fl.skill.model.Request.Category;
import com.fl.skill.model.Response.CategoryList;
import com.fl.skill.model.Response.CategoryRes;
import com.fl.skill.service.CategoryImpl;
import com.fl.skill.service.FileStorageService;

import jakarta.validation.Valid;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryImpl catRepo;
    
    @Autowired
    private Environment env;

    @Autowired
    private FileStorageService fileStorageService;
    @RequestMapping("ping")
    public ResponseEntity<String> ping(){
        return new ResponseEntity<>("Category microservice", HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> createCategory(@Valid @RequestBody Category category){
        try {
            int ins = catRepo.save(category.getName());
            if(ins>0){
                return new ResponseEntity<>(new CommonResponse<String>("Inserted",HttpStatus.CREATED.value()),HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(new CommonResponse<String>("Something went wrong",HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(new CommonResponse<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/image/{id}", method = RequestMethod.POST, consumes = {"multipart/form-data"}/*, consumes = MediaType.MULTIPART_FORM_DATA_VALUE*/)
    public String image(@RequestPart("img") MultipartFile img,@PathVariable("id") int id) {
        String path=env.getProperty("app.file.upload-dir");
        catRepo.updateLogoUrl(path+"/"+fileStorageService.storeFile(img),id) ;
        return "file found";
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllCategory(){
        try {
            List<CategoryRes> category = catRepo.getAll();
           
            if(!category.isEmpty()){
                return new ResponseEntity<>(new CommonResponse<List<CategoryRes>>(category,HttpStatus.ACCEPTED.value()),HttpStatus.OK);
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
    public ResponseEntity<Object> updateCategory(@PathVariable("id") int id,@Valid @RequestBody Category category){
        try {
            List<CategoryRes> lstcategory = new ArrayList<>();
            catRepo.getById(id).forEach(lstcategory::add);
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
    public ResponseEntity<Object> getCategoryById(@PathVariable("id") int id){
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
    public ResponseEntity<Object> deleteCategory(@PathVariable("id") int id){
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
