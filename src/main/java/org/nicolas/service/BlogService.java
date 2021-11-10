package org.nicolas.service;

import org.nicolas.mapper.BlogListMapper;
import org.nicolas.pojo.BlogList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    private final BlogListMapper blogListMapper;

    public BlogService(BlogListMapper blogListMapper) {
        this.blogListMapper = blogListMapper;
    }

    public List<BlogList> getBlogList(){
        return blogListMapper.getBlogList();
    }
}
