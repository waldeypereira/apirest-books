package com.company.books.backend.response;

public class CategoryResponseRest extends ResponseRest {

    private CategoryResponse categoryResponse;

    public CategoryResponseRest() {
        this.categoryResponse = new CategoryResponse(); // always initialized
    }

    public CategoryResponse getCategoryResponse() {
        return categoryResponse;
    }

    public void setCategoryResponse(CategoryResponse categoryResponse) {
        this.categoryResponse = categoryResponse;
    }
}
