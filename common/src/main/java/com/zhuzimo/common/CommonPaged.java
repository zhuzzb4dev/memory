package com.zhuzimo.common;

import java.util.List;

/**
 * 通用分页
 *
 * @param <T> t
 * @author t3
 * @date 2023/12/11
 */
public final class CommonPaged<T> {

    private int pageNumber;
    private int pageSize;
    private long total;
    private int totalPages;
    private boolean isFirst;
    private boolean isLast;
    private boolean hasPrevious;
    private boolean hasNext;

    private List<T> list;

    /**
     * 通用分页
     */
    private CommonPaged() {

    }

    /**
     * 构建
     * @param <T> t
     * @param pageNumber 页码
     * @param pageSize   页面大小
     * @param total      总
     * @param totalPages 总页数
     * @param list       列表
     * @return {@link CommonPaged}<{@link T}>
     */
    public static <T> CommonPaged<T> build(int pageNumber, int pageSize, long total, int totalPages, List<T> list) {
        CommonPaged<T> commonPaged = new CommonPaged<>();
        commonPaged.setPageNumber(pageNumber);
        commonPaged.setPageSize(pageSize);
        commonPaged.setTotal(total);
        commonPaged.setTotalPages(totalPages);
        commonPaged.setList(list);
        commonPaged.setFirst(pageNumber == 1);
        commonPaged.setLast(totalPages == pageNumber);
        commonPaged.setHasPrevious(pageNumber > 1);
        commonPaged.setHasNext(pageNumber < totalPages);
        return commonPaged;
    }

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

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isFirst() {
        return isFirst;
    }


    public boolean isLast() {
        return isLast;
    }


    public boolean isHasPrevious() {
        return hasPrevious;
    }



    public boolean isHasNext() {
        return hasNext;
    }


    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }
}
