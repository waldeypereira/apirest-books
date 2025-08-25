package com.company.books.backend.response;

import com.company.books.backend.model.Book;

import java.util.List;

public class BookResponse {
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}

