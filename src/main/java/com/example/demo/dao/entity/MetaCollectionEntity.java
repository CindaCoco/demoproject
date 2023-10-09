package com.example.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author linyida
 * @version 1.0
 * @created 2023/9/21.
 */
@TableName("meta_collection")
@Data
public class MetaCollectionEntity {
    @TableId
    private Long collectionId;
    private String bizLine;
    private Integer group;
    private String name;
    private Boolean enableMultiValue;
    private Boolean subMetaRequired;
    private Integer showType;
    private String subMetaList;
}
