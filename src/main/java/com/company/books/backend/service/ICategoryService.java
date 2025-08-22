package com.company.books.backend.service;

import com.company.books.backend.model.Category;
import com.company.books.backend.response.CategoryResponse;
import com.company.books.backend.response.CategoryResponseRest;

public interface ICategoryService {

    CategoryResponseRest findAll();

    CategoryResponseRest findById(Long id);

    CategoryResponseRest create(Category category);

    CategoryResponseRest update(Long id, Category category);


}
