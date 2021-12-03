package org.nicolas.controller;

import org.nicolas.pojo.Blog;
import org.nicolas.service.BlogService;
import org.nicolas.util.BaseQuery;
import org.nicolas.util.Request;
import org.nicolas.util.Response;
import org.springframework.web.bind.annotation.*;

/**
 * @author zorth
 */
@RestController
@CrossOrigin
public class BlogPageController {



    private final BlogService blogService;

    public BlogPageController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/getBlogList")
    public Response getBlogListUltimate(@RequestBody BaseQuery baseQueryRequest) {
        return blogService.getBlogListUltimate(baseQueryRequest);
    }

    @PostMapping("/getBlogContent")
    public Response getBlogContent(@RequestBody Request request) {
        Integer blogId = request.getReqInt();
        return blogService.getBlogContent(blogId);
    }

    @PostMapping("/insertBlog")
    public Response insertBlog(@RequestBody Blog blog) {
        return blogService.insertBlog(blog);
    }

}
