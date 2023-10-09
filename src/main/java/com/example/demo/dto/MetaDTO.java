package com.example.demo.dto;

import com.example.demo.model.MetaRule;
import lombok.Data;

/**
 * @author linyida
 * @version 1.0
 * @created 2023/9/21.
 */
@Data
public class MetaDTO {
    private Long metaId;
    private String bizLine;
    private Integer fillType;
    private MetaRule rule;
    private String name;
}
