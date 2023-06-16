package com.fl.skill.controller;

import com.fl.skill.model.FlResponse;
import com.fl.skill.model.request.Category;
import com.fl.skill.model.response.CategorySkillsResponse;
import com.fl.skill.service.serviceInterface.CategoryService;

import com.fl.skill.util.FlResponseUtil;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fl.skill.config.Constant.*;

@RestController
@RequestMapping("/fls/categories")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CategoryController {

    private final CategoryService categoryService;
    private final FlResponseUtil flResponseUtil;

    @PostMapping
    public ResponseEntity<FlResponse<String>> createCategory(@Valid @RequestBody Category category) {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, categoryService.insertCategories(category), String.format("%s " + INSERTED_SUCCESSFULLY, CATEGORY));
        } catch (Exception e) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null, String.format("%s " + INSERTION_FAILED, CATEGORY));
        }

    }

    @GetMapping
    public ResponseEntity<FlResponse<List<CategorySkillsResponse>>> getCategories(
            @RequestParam(defaultValue = "0", required = false, name = "categoryId") Integer categoryId) {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, categoryService.getCategoryWithSkills(categoryId), String.format("%s " + FETCHED_SUCCESSFULLY, CATEGORY));
        } catch (Exception e) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null, String.format("%s " + NO_RECORD_FOUND));
        }
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<FlResponse<String>> updateCategory(@PathVariable("categoryId") Integer categoryId,
                                                             @Valid @RequestBody Category category) {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, categoryService.updateCategory(category, categoryId), String.format("%s" + UPDATED_SUCCESSFULLY, CATEGORY));
        } catch (Exception e) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null, String.format("%s " + UPDATION_FAILED, CATEGORY));
        }
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<FlResponse<String>> deleteCategory(@PathVariable("categoryId") int categoryId) {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, categoryService.deleteCategory(categoryId), String.format("%s" + DELETED_SUCCESSFULLY, CATEGORY));
        } catch (Exception e) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null, String.format("%s " + DELETION_FAILED, CATEGORY));
        }
    }
}
