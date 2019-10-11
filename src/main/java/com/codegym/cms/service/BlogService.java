package com.codegym.cms.service;

import com.codegym.cms.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {

    List<Blog> findAll();

    Page<Blog> findAll(Pageable pageable);

    List<Blog> search(String keyword);

    Page<Blog> search(String keyword, Pageable pageable);

    Blog findOne(Long id);

    Blog save(Blog blog);

    List<Blog> save(List<Blog> blogs);

    boolean exists(Long id);

    List<Blog> findAll(List<Long> ids);

    Long count();

    void  delete(Long id);

    void delete(Blog blog);

    void deleteAll();
}
