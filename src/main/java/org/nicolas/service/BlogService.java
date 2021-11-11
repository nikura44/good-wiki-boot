package org.nicolas.service;

import org.nicolas.mapper.BlogListMapper;
import org.nicolas.pojo.BlogList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    private final Logger logger = LoggerFactory.getLogger(BlogService.class);

    private final BlogListMapper blogListMapper;

    public BlogService(BlogListMapper blogListMapper) {
        this.blogListMapper = blogListMapper;
    }

    public List<BlogList> getBlogList() {
        return blogListMapper.getBlogList();
    }
}
