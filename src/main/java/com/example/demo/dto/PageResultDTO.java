package com.example.demo.dto;

import lombok.Data;

import java.util.List;

/**
 * @author linyida
 * @version 1.0
 * @created 2023/9/28.
 */
@Data
public class PageResultDTO <T>{
    private List<T> dataList;
    private Long totalCount;
}
