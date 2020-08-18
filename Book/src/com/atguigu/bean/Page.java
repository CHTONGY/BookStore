package com.atguigu.bean;

import java.util.List;

/**
 * @author YanTong
 * @Description
 * @create 2020-07-23 8:46 上午
 */
public class Page<T> {
    public static final int PAGE_SIZE = 4;

    private Integer pageNo; // 当前页码
    private Integer pageTotal;  // 总页码
    private Long pageTotalCount; // 总记录数
    private Integer pageSize = PAGE_SIZE;   // 每页显示数量
    private List<T> items;   // 当前页数据
    private String url; // 分页条请求地址

    public Page() {
        super();
    }

    public Page(Integer pageNo, Integer pageTotal, Long pageTotalCount, List<T> items) {
        this.pageNo = pageNo;
        this.pageTotal = pageTotal;
        this.pageTotalCount = pageTotalCount;
        this.items = items;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo, int pageTotal) {
        /* 边界数据的有效检查 */
        if(pageNo < 1) {
            pageNo = 1;
        } else if(pageNo > pageTotal) {
            pageNo = pageTotal;
        }
        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Long getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Long pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageTotalCount=" + pageTotalCount +
                ", pageSize=" + pageSize +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
