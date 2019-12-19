package com.dj.dto;

import java.util.List;

public class PageDto<T> {
    private Long total;
    private List<T> items;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
