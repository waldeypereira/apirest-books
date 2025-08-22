package com.company.books.backend.service;

import com.company.books.backend.repository.ICategoryRepository;
import com.company.books.backend.model.Category;
import com.company.books.backend.response.CategoryResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final ICategoryRepository categoryDao;

    @Autowired
    public CategoryServiceImpl(ICategoryRepository categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponseRest findAll() {
        log.info("Iniciando consulta de categorias...");

        CategoryResponseRest response = new CategoryResponseRest();
        try {
            List<Category> categories = (List<Category>) categoryDao.findAll();
            response.getCategoryResponse().setCategory(categories);
            response.addMetadata("OK", "00", "Consulta exitosa");
        } catch (Exception e) {
            response.addMetadata("ERROR", "-1", "Error al consultar categorias");
            log.error("Error al consultar categorias: ", e);
        }

        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponseRest findById(Long id) {
        log.info("Consulta categoria por ID: {}", id);

        CategoryResponseRest response = new CategoryResponseRest();
        categoryDao.findById(id).ifPresentOrElse(
                category -> {
                    response.getCategoryResponse().setCategory(new ArrayList<>(List.of(category)));
                    response.addMetadata("OK", "00", "Consulta exitosa");
                },
                () -> response.addMetadata("ERROR", "-1", "Categoria no encontrada")
        );

        return response;
    }

}
