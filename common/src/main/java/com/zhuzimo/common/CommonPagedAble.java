package com.zhuzimo.common;

/**
 * 可分页
 *
 * @author t3
 * @date 2023/12/11
 */
public class CommonPagedAble {

    private static final  int DEFAULT_PAGE_SIZE = 10;
    private int pageNumber = 1;
    private int pageSize = DEFAULT_PAGE_SIZE;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
