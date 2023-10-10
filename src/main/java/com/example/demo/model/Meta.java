package com.example.demo.model;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * @author linyida
 * @version 1.0
 * @created 2023/9/21.
 */
@Data
public class Meta {
    private Long metaId;
    private String bizLine;
    private Integer fillType;
    private MetaRule rule;
    private String name;

    public boolean isUnique(List<Meta> metaList){
        if(CollectionUtils.isEmpty(metaList)){
            return true;
        }
        for (Meta meta : metaList) {
            if(Objects.equals(meta.getMetaId(), metaId) || Objects.equals(meta.getName(), name)){
                return false;
            }
        }
        return true;
    }
}
