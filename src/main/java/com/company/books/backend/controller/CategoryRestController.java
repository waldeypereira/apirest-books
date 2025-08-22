package com.company.books.backend.controller;

import com.company.books.backend.response.CategoryResponseRest;
import com.company.books.backend.service.ICategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        try {
            CategoryResponseRest response = service.findAll();
            return ResponseEntity.ok(response); // 200 OK
        } catch (Exception e) {
            CategoryResponseRest errorResponse = new CategoryResponseRest();
            errorResponse.addMetadata("ERROR", "-1", "Erro ao consultar categorias");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> getCategoryById(@PathVariable Long id) {
        CategoryResponseRest response = service.findById(id);
        if (response.getCategoryResponse().getCategory().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }


}
