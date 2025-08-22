package com.company.books.backend.service;

import com.company.books.backend.dao.ICategoryDao;
import com.company.books.backend.model.Category;
import com.company.books.backend.response.CategoryResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final ICategoryDao categoryDao;

    @Autowired
    public CategoryServiceImpl(ICategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponseRest findall() {
        log.info("Iniciando consulta de categorias...");

        CategoryResponseRest response = new CategoryResponseRest();
        try {
            List<Category> categories = (List<Category>) categoryDao.findAll();
            response.getCategoryResponse().setCategoria(categories);
            response.addMetadata("OK", "00", "Consulta exitosa");
        } catch (Exception e) {
            response.addMetadata("ERROR", "-1", "Error al consultar categorias");
            log.error("Error al consultar categorias: ", e);
        }

        return response;
    }
}
