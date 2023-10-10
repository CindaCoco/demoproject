package com.example.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author linyida
 * @version 1.0
 * @created 2023/9/20.
 */
@TableName("meta")
@Data
public class MetaEntity {
    public static final String META_ID = "meta_id";

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long metaId;
    private String bizLine;
    private Integer fillType;
    private String rule;
    private String name;
}
