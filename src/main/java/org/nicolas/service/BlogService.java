package org.nicolas.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.nicolas.mapper.BlogListMapper;
import org.nicolas.mapper.BlogMapper;
import org.nicolas.pojo.Blog;
import org.nicolas.pojo.BlogList;
import org.nicolas.pojo.Status;
import org.nicolas.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        Page page = PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<BlogList> lists = blogListMapper.getBlogList();
        PageInfo pageResult = new PageInfo(lists);
        logger.info("request : " + pageResult.getTotal());
        Response response = new Response();
        response.setRespBody(lists);
        response.setRespInt(pageResult.getTotal());
        return response;
    }

    public Response getBlogContent(Integer blogId) {
        Response response = new Response();
        //查询成功后，需要增加访问量
        Blog blog = blogMapper.getBlogContent(blogId);
        Integer views = blog.getViews() + 1;
        blogMapper.blogViewsAdd(blogId, views);
        blogListMapper.blogViewsAddList(blogId, views);
        blog.setViews(views);
        response.setRespBody(blog);
        return response;
    }

    public Response insertBlog(Blog blog) {
        Response response = new Response();
        BlogList blogList = new BlogList();
        Date date = new Date();// 获取当前时间
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        String time = sdf.format(date);
        blog.setCreateTime(time);
        blog.setUpdateTime(time);
        blog.setViews(0);
        blog.setCommentTime(0);
        blogList.setAuthor(blog.getAuthor());
        blogList.setUpdateTime(time);
        blogList.setCreateTime(time);
        blogList.setCommentTime(0);
        blogList.setViews(0);
        blogList.setClassification("CSS");
        blogList.setStatus(Status.active);
        blogList.setTitle(blog.getTitle());
        blogMapper.insertNewBlog(blog);
        blogListMapper.insertIntoBlogList(blogList);
        return response;
    }

}
