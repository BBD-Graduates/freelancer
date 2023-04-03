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
    public ResponseEntity<FlResponse<String>> createCategory(@Valid @RequestBody Category category)
            throws CategoryNotFoundException {

        return flResponseUtil.getResponseEntity(HttpStatus.OK,categoryService.insertCatgories(category),null );
    }

    @RequestMapping(value = "/image/{id}", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public String image(@RequestPart("img") MultipartFile img, @PathVariable("id") int id)
            throws CategoryNotFoundException {
        String path = env.getProperty("app.file.upload-dir");
        categoryService.updateCategoryLogoUrl(path + "/" + fileStorageService.storeFile(img), id);
        return "file found";
    }

    // change response to categorySkills
    @GetMapping
    public ResponseEntity<FlResponse<List<CategoryRes>>> getCategories(
            @RequestParam(defaultValue = "0", required = false, name = "categoryId") Integer categoryId)
            throws CategoryNotFoundException {

        return flResponseUtil.getResponseEntity(HttpStatus.OK, categoryService.getCategories(categoryId),
                "Categories fetched");
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<FlResponse<String>> updateCategory(@PathVariable("categoryId") Integer categoryId,
            @Valid @RequestBody Category category) throws CategoryNotFoundException {
        return flResponseUtil.getResponseEntity(HttpStatus.OK,categoryService.updateCategory(category, categoryId),"");
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<FlResponse<String>> deleteCategory(@PathVariable("categoryId") int categoryId)
            throws CategoryNotFoundException {
        return flResponseUtil.getResponseEntity(HttpStatus.OK,categoryService.deleteCategory(categoryId),"");
    }
}
