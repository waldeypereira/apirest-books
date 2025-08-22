package com.company.books.backend.service;

import com.company.books.backend.response.CategoryResponseRest;

public interface ICategoryService {

    public CategoryResponseRest findAll();
    public CategoryResponseRest findById(Long id);

}
