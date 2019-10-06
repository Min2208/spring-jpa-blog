package com.codegym.cms.service;

import com.codegym.cms.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findOne(Long id);

    Category save(Category category);

    List<Category> save(List<Category> categories);

    boolean exists(Long id);

    List<Category> findAll(List<Long> ids);

    long count();

    void delete(Long id);

    void delete(Category category);

    void delete(List<Category> categories);

    void deleteAll();

}
