package com.company.books.backend.repository;

import com.company.books.backend.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface IBookRepository extends CrudRepository<Book, Long> {
}
