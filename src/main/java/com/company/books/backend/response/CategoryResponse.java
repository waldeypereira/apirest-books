package com.company.books.backend.response;

import com.company.books.backend.model.Category;

import java.util.List;

public class CategoryResponse {

    private List<Category> categoria;

    public List<Category> getCategoria() {
        return categoria;
    }

    public void setCategoria(List<Category> categoria) {
        this.categoria = categoria;
    }
}
