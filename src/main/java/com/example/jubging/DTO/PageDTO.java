package com.example.jubging.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PageDTO <T> {
    @JsonProperty
    private int totalPage;
    @JsonProperty
    private Long totalElements;
    @JsonProperty
    private int pageSize;
    @JsonProperty
    private int page;
    @JsonProperty
    private List<T> content;

    public PageDTO(int totalPage, Long totalElements, int pageSize, int page, List<T> content) {
        this.totalPage = totalPage;
        this.totalElements = totalElements;
        this.pageSize = pageSize;
        this.page = page;
        this.content = content;
    }
}
