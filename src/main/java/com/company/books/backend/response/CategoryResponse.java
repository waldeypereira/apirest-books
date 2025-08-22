package com.company.books.backend.response;

import com.company.books.backend.model.Category;
import java.util.ArrayList;
import java.util.List;

public class CategoryResponse {

    private List<Category> categories = new ArrayList<>(); // always initialized

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
