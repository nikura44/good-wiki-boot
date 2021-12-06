package org.nicolas.service;

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

import java.util.List;

/**
 * @author zorth
 */
@Service
public class BlogService {
    private final Logger logger = LoggerFactory.getLogger(BlogService.class);

    private final BlogListMapper blogListMapper;
    private final BlogMapper blogMapper;

    public BlogService(BlogListMapper blogListMapper, BlogMapper blogMapper) {
        this.blogListMapper = blogListMapper;
        this.blogMapper = blogMapper;
    }


    public Response<List<BlogList>> getBlogListUltimate(BaseQuery request) {
        logger.info("request : " + request.getPageNum());
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<BlogList> lists = blogListMapper.getBlogList();
        PageInfo pageResult = new PageInfo(lists);
        logger.info("request : " + pageResult.getTotal());
        Response<List<BlogList>> response = new Response<List<BlogList>>();
        response.setRespBody(lists);
        response.setRespInt(pageResult.getTotal());
        return response;
    }

    public Response<Blog> getBlogContent(Integer blogId) {
        Response<Blog> response = new Response<>();
        //查询成功后，需要增加访问量
        Blog blog = blogMapper.getBlogContent(blogId);
        Integer views = blog.getViews() + 1;
        blogMapper.blogViewsAdd(blogId, views);
        blogListMapper.blogViewsAddList(blogId, views);
        blog.setViews(views);
        response.setRespBody(blog);
        return response;
    }

    /**
     * insert blog and blogList into mysql database
     * @param blog
     * @return Response
     */
    public Response insertBlog(Blog blog) {
        Response response = new Response();
        String time = DateFormat.getCurrentTime();
        blog.setCreateTime(time);
        blog.setUpdateTime(time);
        blog.setViews(0);
        blog.setCommentTime(0);
        BlogList blogList = new BlogList(blog.getTitle(), blog.getAuthor(), time, time, 0, Status.active, 0, "CSS");
        blogMapper.insertNewBlog(blog);
        blogListMapper.insertIntoBlogList(blogList);
        return response;
    }

}
