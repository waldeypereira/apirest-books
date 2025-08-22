package com.company.books.backend.controller;

import com.company.books.backend.response.CategoryResponseRest;
import com.company.books.backend.service.ICategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CategoryRestController {

    private final ICategoryService service;

    public CategoryRestController(ICategoryService service) {
        this.service = service;
    }

    @GetMapping("/categories")
    public ResponseEntity<CategoryResponseRest> getCategories() {
        CategoryResponseRest response = service.findAll();
        return ResponseEntity.ok(response);
    }
}
