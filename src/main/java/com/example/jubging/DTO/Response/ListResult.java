package com.example.jubging.DTO.Response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class ListResult<T> extends CommonResult {
    private List<T> data;
}
