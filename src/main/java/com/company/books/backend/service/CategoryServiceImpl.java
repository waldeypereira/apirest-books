package com.company.books.backend.service;

import com.company.books.backend.repository.ICategoryRepository;
import com.company.books.backend.model.Category;
import com.company.books.backend.response.CategoryResponse;
import com.company.books.backend.response.CategoryResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final ICategoryRepository categoryRepository;

    public CategoryServiceImpl(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponseRest findAll() {
        log.info("Fetching all categories");

        CategoryResponseRest response = new CategoryResponseRest();
        try {
            List<Category> categories = (List<Category>) categoryRepository.findAll();
            response.getCategoryResponse().setCategories(categories);
            response.addMetadata("OK", "00", "Successful query");
        } catch (Exception e) {
            response.getCategoryResponse().setCategories(new ArrayList<>());
            response.addMetadata("ERROR", "-1", "Error fetching categories");
            log.error("Error fetching categories", e);
        }
        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponseRest findById(Long id) {
        log.info("Fetching category by ID: {}", id);

        CategoryResponseRest response = new CategoryResponseRest();
        try {
            Category category = categoryRepository.findById(id).orElse(null);
            if (category != null) {
                response.getCategoryResponse().setCategories(List.of(category));
                response.addMetadata("OK", "00", "Successful query");
            } else {
                response.getCategoryResponse().setCategories(new ArrayList<>());
                response.addMetadata("ERROR", "-1", "Category not found");
            }
        } catch (Exception e) {
            response.getCategoryResponse().setCategories(new ArrayList<>());
            response.addMetadata("ERROR", "-1", "Error fetching category");
            log.error("Error fetching category by ID", e);
        }
        return response;
    }

    @Override
    @Transactional
    public CategoryResponseRest create(Category category) {
        log.info("Creating new category");

        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> list = new ArrayList<>();

        try {
            Category savedCategory = categoryRepository.save(category);
            list.add(savedCategory);

            response.getCategoryResponse().setCategories(list);
            response.addMetadata("OK", "00", "Category created successfully");

            log.info("Category created successfully with ID: {}", savedCategory.getId());
        } catch (Exception e) {
            log.error("Error creating category", e);
            response.addMetadata("ERROR", "-1", "Error creating category");
        }

        return response;
    }

    @Override
    @Transactional
    public CategoryResponseRest update(Long id, Category category) {
        log.info("Updating category with ID: {}", id);

        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> list = new ArrayList<>();

        try {
            Category existingCategory = categoryRepository.findById(id).orElse(null);
            if (existingCategory != null) {
                existingCategory.setName(category.getName());
                existingCategory.setDescription(category.getDescription());

                Category updatedCategory = categoryRepository.save(existingCategory);
                list.add(updatedCategory);

                response.getCategoryResponse().setCategories(list);
                response.addMetadata("OK", "00", "Category updated successfully");

                log.info("Category updated successfully with ID: {}", updatedCategory.getId());
            } else {
                response.addMetadata("ERROR", "-1", "Category not found");
                log.warn("Category with ID: {} not found for update", id);
            }
        } catch (Exception e) {
            log.error("Error updating category", e);
            response.addMetadata("ERROR", "-1", "Error updating category");
        }

        return response;
    }

}
