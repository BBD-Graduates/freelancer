package com.fl.skill.controller;

import com.fl.skill.model.Request.Category;
import com.fl.skill.repository.CategoryRepository;
import com.fl.skill.service.FileStorageService;

import jakarta.validation.Valid;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryRepository catRepo;


    @Autowired
    Environment env;
    @Autowired
    private FileStorageService fileStorageService;

    @RequestMapping("ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("Category microservice", HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> createCategory(@Valid @RequestBody Category category) {

        return new ResponseEntity<>(catRepo.save(category), HttpStatus.OK);

    }

    @RequestMapping(value = "/image/{id}", method = RequestMethod.POST, consumes = {"multipart/form-data"}/*, consumes = MediaType.MULTIPART_FORM_DATA_VALUE*/)
    public String image(@RequestPart("img") MultipartFile img, @PathVariable("id") int id) {
        String path = env.getProperty("app.file.upload-dir");
        catRepo.updateLogoUrl(path + "/" + fileStorageService.storeFile(img), id);
        return "file found";
    }

    @GetMapping("/categoryId={categoryId}")
    public ResponseEntity<Object> getCategories(@RequestParam(defaultValue = "0", required = false) Integer categoryId) {
        if (!categoryId.equals(0)) {
            return new ResponseEntity<>(catRepo.getById(categoryId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(catRepo.getAll(), HttpStatus.OK);
        }
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable("id") Integer id, @Valid @RequestBody Category category) {
        return new ResponseEntity<>(catRepo.update(category, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable("id") int id) {
        return new ResponseEntity<>(catRepo.delete(id), HttpStatus.OK);
    }
}
