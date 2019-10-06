package com.codegym.cms.service.impl;

import com.codegym.cms.model.Category;
import com.codegym.cms.repository.CategoryRepository;
import com.codegym.cms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return streamAll().collect(Collectors.toList());
    }
    private Stream<Category> streamAll() {
        return StreamSupport.stream(categoryRepository.findAll().spliterator(), false);
    }

    @Override
    public Category findOne(Long id) {
        return null;
    }

    @Override
    public Category save(Category category) {
        return null;
    }

    @Override
    public List<Category> save(List<Category> categories) {
        return null;
    }

    @Override
    public boolean exists(Long id) {
        return false;
    }

    @Override
    public List<Category> findAll(List<Long> ids) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void delete(Category category) {

    }

    @Override
    public void delete(List<Category> categories) {

    }

    @Override
    public void deleteAll() {

    }
}
