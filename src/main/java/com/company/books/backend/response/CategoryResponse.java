package com.company.books.backend.response;

import com.company.books.backend.model.Category;

import java.util.List;

public class CategoryResponse {

    private List<Category> category;

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }
}
