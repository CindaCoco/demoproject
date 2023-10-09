package com.example.demo.util.mapstruct;

import com.alibaba.fastjson.JSON;
import com.example.demo.dao.entity.MetaEntity;
import com.example.demo.dao.entity.MetaGroupEntity;
import com.example.demo.dto.MetaDTO;
import com.example.demo.model.Meta;
import com.example.demo.model.MetaGroup;
import com.example.demo.model.MetaRule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.util.StringUtils;

/**
 * @author linyida
 * @version 1.0
 * @created 2023/9/21.
 */
@Mapper
public interface MetaTransfer {
    @Mapping(target = "id", ignore = true)
    MetaEntity toEntity(Meta meta);

    MetaGroupEntity toEntity(MetaGroup metaGroup);

    default String toRule(MetaRule metaRule){
        if(metaRule == null){
            return null;
        }
        return JSON.toJSONString(metaRule);
    }
    default MetaRule toRule(String rule){
        if(!StringUtils.hasLength(rule)){
            return null;
        }
        return JSON.parseObject(rule, MetaRule.class);
    }

    Meta toModel(MetaDTO metaDTO);

    Meta toModel(MetaEntity metaEntity);

    MetaDTO toDTO(Meta meta);

}
