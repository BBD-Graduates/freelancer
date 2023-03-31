package com.fl.skill.controller;

import com.fl.skill.exceptions.CategoryNotFoundException;
import com.fl.skill.model.FlResponse;
import com.fl.skill.model.request.Category;
import com.fl.skill.model.response.CategoryRes;
import com.fl.skill.service.serviceInterface.CategoryService;
import com.fl.skill.service.FileStorageService;

import com.fl.skill.util.FlResponseUtil;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final Environment env;
    private final FileStorageService fileStorageService;
    private final FlResponseUtil flResponseUtil;

    @PostMapping
    public ResponseEntity<String> createCategory(@Valid @RequestBody Category category) {

        return new ResponseEntity<>(categoryService.insertCatgories(category), HttpStatus.OK);
    }

    @RequestMapping(value = "/image/{id}", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public String image(@RequestPart("img") MultipartFile img, @PathVariable("id") int id)
    {
        String path = env.getProperty("app.file.upload-dir");
        categoryService.updateCategoryLogoUrl(path + "/" + fileStorageService.storeFile(img), id);
        return "file found";
    }

    //change response to categorySkills
    @GetMapping
    public ResponseEntity<FlResponse<List<CategoryRes>>> getCategories(@RequestParam(defaultValue = "0", required = false,name = "categoryId") Integer categoryId) throws CategoryNotFoundException {
//        try {
            if (!categoryId.equals(0)) {
                return flResponseUtil.getResponseEntity(HttpStatus.OK,categoryService.getCategoryById(categoryId),"Categories fetched");
            } else {
                return flResponseUtil.getResponseEntity(HttpStatus.OK,categoryService.getAllCategories(),"Categories fetched");
            }
//        }
//        catch(CategoryNotFoundException e) {
//           return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR,null,e.getMessage());
//
//        }
    }



    @PutMapping("/{categoryId}")
    public ResponseEntity<String> updateCategory(@PathVariable("categoryId") Integer categoryId, @Valid @RequestBody Category category) {
        return new ResponseEntity<>(categoryService.updateCategory(category, categoryId), HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable("categoryId") int categoryId) {
        return new ResponseEntity<>(categoryService.deleteCategory(categoryId), HttpStatus.OK);
    }
}
