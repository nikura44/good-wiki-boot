package org.nicolas.util;

import java.io.Serializable;

/**
 * @author zorth
 */
public class
BaseQuery implements Serializable {
    //当前页
    private Integer pageNum = 1;

    //每页条数
    private Integer pageSize = 5;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
