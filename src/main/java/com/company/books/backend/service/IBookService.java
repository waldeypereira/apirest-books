package com.company.books.backend.service;

import com.company.books.backend.model.Book;
import com.company.books.backend.response.BookResponseRest;

public interface IBookService {

    BookResponseRest findAll();
    BookResponseRest findById(Long id);
    BookResponseRest create(Book book);
    BookResponseRest update(Book book, Long id);
    BookResponseRest delete(Long id);
}
