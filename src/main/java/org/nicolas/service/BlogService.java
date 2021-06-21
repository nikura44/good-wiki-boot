package org.nicolas.service;

import org.nicolas.mapper.BlogMapper;
import org.nicolas.pojo.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogMapper blogMapper;

    public Integer insertBlog(Blog blog){
        return blogMapper.insertNewBlog(blog);
    }

    public List showWikiList(){
        return blogMapper.showWikiList();
    }

    public Integer updateBlog(Blog blog){
        return blogMapper.updateBlog(blog);
    }
}
