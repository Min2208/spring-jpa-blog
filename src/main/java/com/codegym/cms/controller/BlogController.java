package com.codegym.cms.controller;

import com.codegym.cms.model.Blog;
import com.codegym.cms.model.Category;
import com.codegym.cms.service.BlogService;
import com.codegym.cms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Controller
@RequestMapping("blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("category")
    public Iterable<Category> allCategory(){
        return categoryService.findAll();
    }

    @GetMapping
    public ModelAndView listBlog(@RequestParam("name") Optional<String> name, Pageable pageable){
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC,"id"));
        pageable = new PageRequest(pageable.getPageNumber(),2,sort);
        ModelAndView modelAndView = new ModelAndView("blog/list");
        Page<Blog> blogs;
        if (name.isPresent()){
            blogs= blogService.search(name.get(),pageable);
        }else {
            blogs=blogService.findAll(pageable);
        }

        modelAndView.addObject("blogs",blogs);

        return modelAndView;
    }

    @GetMapping("edit")
    public ModelAndView editForm(long id){
        ModelAndView modelAndView = new ModelAndView("blog/edit","blogs",blogService.findOne(id));
        return modelAndView;
    }

    @PostMapping("edit")
  public String editBlog(Blog blog){
        blogService.save(blog);
        return "redirect:/blog";

    }

    @GetMapping("add")
    public ModelAndView addForm(){
        ModelAndView modelAndView = new ModelAndView("blog/create", "blog", new Blog());


        return modelAndView;
    }
    @PostMapping("add")
    public String addBlog(Blog blog){
        blogService.save(blog);
        return "redirect:/blog";

    }

    @GetMapping("remove")
    public ModelAndView deleteForm(Long id){
        ModelAndView modelAndView= new ModelAndView("blog/delete" , "blogs",blogService.findOne(id));
        return modelAndView;
    }
    @PostMapping("remove")
    public String remoBlog(Long id){
        blogService.delete(id);
        return "redirect:/blog";
    }

}
