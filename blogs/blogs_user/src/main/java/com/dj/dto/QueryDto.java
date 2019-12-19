package com.dj.dto;

import com.dj.tool.Constant;

public class QueryDto {
    private Integer pageNo= Constant.PAGE_NO;

    private Integer pageSize=Constant.PAGE_SIZE;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
