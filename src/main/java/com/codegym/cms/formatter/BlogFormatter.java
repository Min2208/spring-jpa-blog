package com.codegym.cms.formatter;


import com.codegym.cms.model.Blog;
import com.codegym.cms.service.BlogService;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class BlogFormatter implements Formatter<Blog> {
    private BlogService blogService;

    public BlogFormatter(BlogService blogService){
        this.blogService=blogService;

    }
    @Override
    public Blog parse(String text, Locale locale) throws ParseException {
        return blogService.findOne(Long.parseLong(text));
    }

    @Override
    public String print(Blog object, Locale locale) {
        return "[" + object.getId()+ "," +object.getName()+"]";
    }
}
