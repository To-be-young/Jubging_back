package com.example.jubging.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PageDTO <T> {
    @JsonProperty
    private List<T> content;
    @JsonProperty
    private int page;
    @JsonProperty
    private int pageSize;
    @JsonProperty
    private int totalPage;
    @JsonProperty
    private Long totalElements;

    public PageDTO(List<T> content, int page, int pageSize, int totalPage, Long totalElements) {
        this.content = content;
        this.page = page;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.totalElements = totalElements;
    }
}
