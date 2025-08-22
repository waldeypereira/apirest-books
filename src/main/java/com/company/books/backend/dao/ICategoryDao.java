package com.company.books.backend.dao;

import com.company.books.backend.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface ICategoryDao extends CrudRepository<Category, Long> {
}
