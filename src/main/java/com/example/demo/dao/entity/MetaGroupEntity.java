package com.example.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author linyida
 * @version 1.0
 * @created 2023/9/21.
 */
@TableName("meta_group")
@Data
public class MetaGroupEntity {
    @TableId
    private Long id;
    private String bizLine;
    private Integer group;
    private Long metaId;
    private String alias;
}
