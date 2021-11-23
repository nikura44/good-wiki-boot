package org.nicolas.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.nicolas.pojo.BlogList;

import java.util.List;

@Mapper
public interface BlogListMapper {
    List<BlogList> getBlogList();

    Integer blogViewsAddList(Integer blogId, Integer views);

    Integer insertIntoBlogList(BlogList blogList);
}
