package com.codegym.cms.controller;

import com.codegym.cms.model.Blog;
import com.codegym.cms.model.Category;
import com.codegym.cms.service.BlogService;
import com.codegym.cms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Pageable;

import javax.print.attribute.standard.Media;
import java.awt.*;
import java.util.Optional;
import java.util.List;

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

    @GetMapping("home")
    public ModelAndView home(@RequestParam("name") Optional<String> name, Pageable pageable){
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC,"id"));
        pageable = new PageRequest(pageable.getPageNumber(),2,sort);
        ModelAndView modelAndView = new ModelAndView("blog/home");
        Page<Blog> blogs;
        if (name.isPresent()){
            blogs= blogService.search(name.get(),pageable);
        }else {
            blogs=blogService.findAll(pageable);
        }

        modelAndView.addObject("blogs",blogs);

        return modelAndView;
    }

    @GetMapping("/info/{id}")
    public ModelAndView info(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView("blog/info","blog",blogService.findOne(id));
        return modelAndView;
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
    public String addBlog(@Validated Blog blog, BindingResult bindingResult){
        System.out.println(bindingResult.hasFieldErrors());
        if (bindingResult.hasFieldErrors()){
            return "create";
        }else {


            blogService.save(blog);
            return "redirect:/blog";
        }
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
    // Web Service
    @RequestMapping(value = "/", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Blog>> lisBlog(){
        List<Blog> blogs = blogService.findAll();
        if (blogs.isEmpty()){
            return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Blog>>(blogs,HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> bodyBlog(@PathVariable long id){
        Blog blog=blogService.findOne(id);
        if(blog==null){
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<String>(blog.getBody(),HttpStatus.OK);
    }
}
