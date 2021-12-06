package org.nicolas.util;

import com.github.pagehelper.PageInfo;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author nicolas
 */
public class PageUtil<T> {

    public PageInfo<T> listToPage(BaseQuery pageInfo, List<T> list) {
        Integer pageNum = pageInfo.getPageNum();
        Integer pageSize = pageInfo.getPageSize();

        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 5;
        }

        if (CollectionUtils.isEmpty(list)) {
            PageInfo<T> result = new PageInfo<>();
            result.setPageNum(pageNum);
            result.setPageSize(pageSize);
            result.setSize(0);
            result.setList(list);
            return result;
        }

        int size = list.size();
        int pageIndex = (pageNum - 1) * pageSize;
        pageIndex = Math.min(pageIndex, size);
        pageIndex = Math.max(pageIndex, 0);
        int pageEnd = Math.min(pageIndex + pageSize, size);
        List<T> ts = list.subList(pageIndex, pageEnd);
        PageInfo<T> result = new PageInfo<>(ts);
        result.setPageNum(pageNum);
        result.setPageSize(pageSize);
        result.setSize(size);
        result.setTotal(size);
        result.setPages((int) Math.round(size / pageSize + 0.5));
        return result;
    }
}


