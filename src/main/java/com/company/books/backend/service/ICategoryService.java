package com.company.books.backend.service;

import com.company.books.backend.response.CategoryResponseRest;

public interface ICategoryService {

    CategoryResponseRest findAll();

    CategoryResponseRest findById(Long id);
}
