package com.example.demo.util.mapstruct;

import com.alibaba.fastjson.JSON;
import com.example.demo.dao.entity.MetaEntity;
import com.example.demo.dao.entity.MetaGroupEntity;
import com.example.demo.dto.MetaDTO;
import com.example.demo.dto.MetaGroupDTO;
import com.example.demo.model.Meta;
import com.example.demo.model.MetaGroup;
import com.example.demo.model.MetaRule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author linyida
 * @version 1.0
 * @created 2023/9/21.
 */
@Mapper(componentModel = "spring")
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

    Meta toMeta(MetaDTO metaDTO);

    Meta toMeta(MetaEntity metaEntity);

    MetaDTO toDTO(Meta meta);


    MetaGroupDTO toDTO(MetaGroup metaGroup);
    MetaGroup toMetaGroup(MetaGroupEntity entity);

    MetaGroup toMetaGroup(MetaGroupDTO metaGroupDTO);

    List<MetaGroup> toMetaGroupList(List<MetaGroupEntity> entityList);

    List<MetaDTO> toMetaDTOList(List<MetaDTO> dataList);

    List<MetaGroupDTO> toMetaGroupDTOList(List<MetaGroup> dataList);

    List<Meta> toMetaList(List<MetaEntity> metaEntities);
}
