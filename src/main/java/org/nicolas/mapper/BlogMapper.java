package org.nicolas.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.nicolas.pojo.Blog;

import java.util.List;

@Mapper
public interface BlogMapper {
    Blog getBlogContent(Integer blogId);
}
