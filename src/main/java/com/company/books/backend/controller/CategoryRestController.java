package com.company.books.backend.controller;

import com.company.books.backend.model.Category;
import com.company.books.backend.response.CategoryResponseRest;
import com.company.books.backend.service.ICategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> getCategory(@PathVariable Long id) {
        CategoryResponseRest response = service.findById(id);

        if (response.getCategoryResponse().getCategories().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryResponseRest> createCategory(@RequestBody Category category) {
        CategoryResponseRest response = service.create(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        CategoryResponseRest response = service.update(id, category);
        return ResponseEntity.ok(response);
    }
}
