package org.nicolas.controller;

import com.github.pagehelper.PageInfo;
import org.nicolas.pojo.Blog;
import org.nicolas.pojo.BlogList;
import org.nicolas.service.BlogService;
import org.nicolas.util.BaseQuery;
import org.nicolas.util.Request;
import org.nicolas.util.Response;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
//@RequestMapping("/Blog")
public class BlogPageController {



    private final BlogService blogService;

    public BlogPageController(BlogService blogService) {
        this.blogService = blogService;
    }



//    @PostMapping("/insertBlog")
//    public Message insertBlog(@RequestBody Blog blog){
//        Message message = new Message();
//
//        Integer count = blogService.insertBlog(blog);
//
//        if (count == 1) {
//            message.setOk(true);
//            message.setResult("success");
//            return message;
//        }
//
//        return message;
//    }

//    @GetMapping("/getBlogList")
//    public List<BlogList> getBlogList(BaseQuery baseQueryRequest) {
//        return blogService.getBlogListUltimate(baseQueryRequest);
//    }

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

//    @PostMapping("/insertBlog")
//    public Response insertBlog(){
//
//    }
}
