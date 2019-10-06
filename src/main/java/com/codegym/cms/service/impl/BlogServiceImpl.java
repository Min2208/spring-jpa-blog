package com.codegym.cms.service.impl;

import com.codegym.cms.model.Blog;
import com.codegym.cms.repository.BlogRepository;
import com.codegym.cms.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    private Iterable<Blog> blogs;


    @Override
    public List<Blog> findAll() {
        return streamAll().collect(Collectors.toList());
    }

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public List<Blog> search(String keyword) {
        Iterable<Blog> searchResult = blogRepository
                .findAllByNameContainsOrDescriptionContains(keyword, keyword);
        return streamAll(searchResult).collect(Collectors.toList());
    }

    @Override
    public Page<Blog> search(String keyword, Pageable pageable) {
        return blogRepository
                .findAllByNameContainsOrDescriptionContains(keyword, keyword, pageable);
    }

    @Override
    public Blog findOne(Long id) {
        return blogRepository.findOne(id);
    }

    @Override
    public Blog save(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public List<Blog> save(List<Blog> blogs) {
        Iterable<Blog> updateBlog = blogRepository.save(blogs);
        return streamAll(updateBlog).collect(Collectors.toList());
    }

    @Override
    public boolean exists(Long id) {
        return blogRepository.exists(id);
    }

    @Override
    public List<Blog> findAll(List<Long> ids) {
        Iterable<Blog> customers = blogRepository.findAll(ids);
        return streamAll(customers).collect(Collectors.toList());
    }

    private Stream<Blog> streamAll(Iterable<Blog> blogs) {
        this.blogs = blogs;
        return StreamSupport.stream(blogs.spliterator(), false);
    }

    private Stream<Blog> streamAll() {
        return StreamSupport.stream(blogRepository.findAll().spliterator(), false);
    }
    @Override
    public Long count() {
        return blogRepository.count();
    }

    @Override
    public void delete(Long id) {
        blogRepository.delete(id);
    }

    @Override
    public void delete(Blog blog) {
        blogRepository.delete(blog);
    }

    @Override
    public void deleteAll() {
        blogRepository.deleteAll();
    }
}
