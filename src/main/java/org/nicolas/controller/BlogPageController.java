package org.nicolas.controller;

import org.nicolas.pojo.Blog;
import org.nicolas.pojo.BlogList;
import org.nicolas.pojo.Message;
import org.nicolas.service.BlogService;
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

    @GetMapping("/getBlogList")
    public List<BlogList> getBlogList(){
        return blogService.getBlogList();
    }
}
