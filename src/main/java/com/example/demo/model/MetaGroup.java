package com.example.demo.model;

import com.example.demo.dao.entity.MetaEntity;
import com.example.demo.dao.entity.MetaGroupEntity;
import com.example.demo.util.mapstruct.MetaTransfer;
import lombok.Data;
import org.mapstruct.factory.Mappers;

/**
 * @author linyida
 * @version 1.0
 * @created 2023/10/9.
 */
@Data
public class MetaGroup {


    private Long id;
    private String bizLine;
    private Integer group;
    private Long metaId;
    private String alias;


    private static final MetaTransfer metaTransfer = Mappers.getMapper(MetaTransfer.class);

    public MetaGroupEntity toEntity(){
        return metaTransfer.toEntity(this);
    }
}
