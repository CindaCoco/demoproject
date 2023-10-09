package com.example.demo.model;

import lombok.Data;

import java.util.List;

/**
 * @author linyida
 * @version 1.0
 * @created 2023/9/21.
 */
@Data
public class MetaCollection {
    private Long collectionId;
    private String bizLine;
    private Integer group;
    private Meta meta;
    private String name;
    private Boolean enableMultiValue;
    private Boolean subMetaRequired;
    private Integer showType;
    private List<Long> subMetaList;
}
