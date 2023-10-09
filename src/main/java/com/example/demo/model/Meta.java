package com.example.demo.model;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.example.demo.dao.entity.MetaEntity;
import com.example.demo.dto.MetaDTO;
import com.example.demo.util.mapstruct.MetaTransfer;
import lombok.Data;
import org.mapstruct.factory.Mappers;

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

    private static final MetaTransfer metaTransfer = Mappers.getMapper(MetaTransfer.class);
    public MetaEntity toEntity(){
        return metaTransfer.toEntity(this);
    }

    public MetaDTO toDTO(){
        return metaTransfer.toDTO(this);
    }
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

    public static Meta build(MetaDTO metaDTO){
        return metaTransfer.toModel(metaDTO);
    }

    public static Meta build(MetaEntity metaEntity){
        return metaTransfer.toModel(metaEntity);
    }
}
