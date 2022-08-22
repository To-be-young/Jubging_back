package com.example.jubging.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListResult<T> extends CommonResult{
    private ListResult<T> list;
}
