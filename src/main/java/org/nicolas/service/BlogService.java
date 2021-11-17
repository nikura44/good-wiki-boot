package org.nicolas.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.nicolas.mapper.BlogListMapper;
import org.nicolas.mapper.BlogMapper;
import org.nicolas.pojo.BlogList;
import org.nicolas.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    private final Logger logger = LoggerFactory.getLogger(BlogService.class);

    private final BlogListMapper blogListMapper;
    private final BlogMapper blogMapper;

    public BlogService(BlogListMapper blogListMapper, BlogMapper blogMapper) {
        this.blogListMapper = blogListMapper;
        this.blogMapper = blogMapper;
    }


    public Response getBlogListUltimate(BaseQuery request) {
        logger.info("request : " + request.getPageNum());
        Page page = PageHelper.startPage(request.getPageNum(),request.getPageSize());
        List<BlogList> lists = blogListMapper.getBlogList();
        PageInfo pageResult = new PageInfo(lists);
        logger.info("request : " + pageResult.getTotal());
        Response response = new Response();
        response.setRespBody(lists);
        response.setRespInt(pageResult.getTotal());
        return response;
    }

    public Response getBlogContent(Integer blogId){
        Response response = new Response();
        response.setRespBody(blogMapper.getBlogContent(blogId));
        return response;
    }

}
