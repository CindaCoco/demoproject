package com.example.demo.service;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dao.entity.MetaEntity;
import com.example.demo.dao.mapper.MetaMapper;
import com.example.demo.dto.MetaDTO;
import com.example.demo.dto.PageResultDTO;
import com.example.demo.model.Meta;
import com.example.demo.util.AssertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author linyida
 * @version 1.0
 * @created 2023/9/21.
 */
@Service
public class MetaService {

    @Autowired
    MetaMapper metaMapper;
    public MetaDTO insertMeta(MetaDTO metaDTO){
        Meta meta = Meta.build(metaDTO);
        // 校验唯一性
        List<Meta> metas = queryMetaList();
        if (!meta.isUnique(metas)) {
            throw new RuntimeException("插入的元数据定义重复，请确认填写无误后插入");
        }
        MetaEntity entity = meta.toEntity();
        metaMapper.insert(entity);
        Meta newMeta = Meta.build(entity);
        return newMeta.toDTO();
    }

    public List<Meta> queryMetaList(){
        List<MetaEntity> metaEntities = metaMapper.selectList(Wrappers.query());
        if(CollectionUtils.isEmpty(metaEntities)){
            return Collections.emptyList();
        }
        return metaEntities.stream().map(Meta::build).collect(Collectors.toList());
    }

    public PageResultDTO<MetaDTO> queryList(Integer pageNow, Integer pageSize) {
        AssertUtils.notNull(pageNow, "当前页不能为空");
        AssertUtils.notNull(pageSize, "当前页大小不能为空");
        Page<MetaEntity> metaEntityPage = metaMapper.selectPage(new Page<>(pageNow, pageSize), null);
        PageResultDTO<MetaDTO> result = new PageResultDTO<>();
        result.setTotalCount(metaEntityPage.getTotal());
        if(CollectionUtils.isEmpty(metaEntityPage.getRecords())){
            result.setDataList(Collections.emptyList());
        }else{
            result.setDataList(metaEntityPage.getRecords().stream().map(Meta::build).map(Meta::toDTO).collect(Collectors.toList()));
        }
        return result;
    }
}
