package com.attust.mp.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.List;

/**
 * @author fqlStart
 * {@code @create} 2026-06-09-20:09
 */
@Data
public class PageResult<T> {
    private Long total;
    private Long pageSize;
    private Long totalPage;
    private Long currentPage;
    private List<T> list;

    public PageResult(IPage<T> page) {
        this.total = page.getTotal();
        this.pageSize = page.getSize();
        this.totalPage = page.getPages();
        this.currentPage = page.getCurrent();
        this.list = page.getRecords();
    }
}
