package org.nicolas.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.nicolas.mapper.BlogListMapper;
import org.nicolas.pojo.BlogList;
import org.nicolas.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {
    private final Logger logger = LoggerFactory.getLogger(BlogService.class);

    private final BlogListMapper blogListMapper;

    public BlogService(BlogListMapper blogListMapper) {
        this.blogListMapper = blogListMapper;
    }


    public Response getBlogListUltimate(BaseQuery request) {
        logger.info("request : " + request.getPageNum());
        Page page = PageHelper.startPage(request.getPageNum(),request.getPageSize());
        List<BlogList> lists = blogListMapper.getBlogList();
        PageInfo pageResult = new PageInfo(lists);
        logger.info("request : " + pageResult.getTotal());
        Response response = new Response();
        response.setRespBody(lists);
        return response;
    }

}
