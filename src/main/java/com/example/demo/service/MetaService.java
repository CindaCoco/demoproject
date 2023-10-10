package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dao.entity.MetaEntity;
import com.example.demo.dao.entity.MetaGroupEntity;
import com.example.demo.dao.mapper.MetaGroupMapper;
import com.example.demo.dao.mapper.MetaMapper;
import com.example.demo.model.Meta;
import com.example.demo.model.MetaGroup;
import com.example.demo.util.AssertUtils;
import com.example.demo.util.mapstruct.MetaTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linyida
 * @version 1.0
 * @created 2023/9/21.
 */
@Service
public class MetaService {

    @Autowired
    MetaMapper metaMapper;
    @Autowired
    MetaGroupMapper metaGroupMapper;
    @Autowired
    MetaTransfer metaTransfer;

    public Meta createMeta(Meta meta) {
        // 校验唯一性
        List<Meta> metas = queryMetaList();
        if (!meta.isUnique(metas)) {
            throw new RuntimeException("插入的元数据定义重复，请确认填写无误后插入");
        }
        MetaEntity entity = metaTransfer.toEntity(meta);
        metaMapper.insert(entity);
        return metaTransfer.toMeta(entity);
    }

    public List<Meta> queryMetaList() {
        List<MetaEntity> metaEntities = metaMapper.selectList(null);
        return metaTransfer.toMetaList(metaEntities);
    }

    public Page<Meta> queryMetaList(Integer pageNow, Integer pageSize) {
        AssertUtils.notNull(pageNow, "当前页不能为空");
        AssertUtils.notNull(pageSize, "当前页大小不能为空");
        Page result = metaMapper.selectPage(new Page<>(pageNow, pageSize), null);
        List<MetaEntity> records = result.getRecords();
        if (CollectionUtils.isNotEmpty(records)) {
            result.setRecords(metaTransfer.toMetaList(records));
        }
        return result;
    }

    public Meta queryMetaByMetaId(Long metaId) {
        QueryWrapper<MetaEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MetaEntity.META_ID, metaId);
        MetaEntity meta = metaMapper.selectOne(queryWrapper);
        return metaTransfer.toMeta(meta);
    }

    public Page<MetaGroup> queryMetaGroupList(String groupName, Integer pageNow, Integer pageSize) {
        AssertUtils.notNull(groupName, "分组不能为空");
        AssertUtils.notNull(pageNow, "当前页不能为空");
        AssertUtils.notNull(pageSize, "页面大小不能为空");
        Page result = metaGroupMapper.selectPage(new Page<>(pageNow, pageSize),
                Wrappers.lambdaQuery(MetaGroupEntity.class).eq(MetaGroupEntity::getGroup, groupName));
        List<MetaGroupEntity> records = result.getRecords();
        if (CollectionUtils.isNotEmpty(records)) {
            result.setRecords(metaTransfer.toMetaGroupList(records));
        }
        return result;
    }

    public void checkMetaUniqueInGroup(String bizLine, Integer group, Long metaId) {
        MetaGroupEntity metaGroup = metaGroupMapper.selectOne(
                Wrappers.lambdaQuery(MetaGroupEntity.class).eq(MetaGroupEntity::getBizLine, bizLine)
                        .eq(MetaGroupEntity::getGroup, group).eq(MetaGroupEntity::getMetaId, metaId));
        AssertUtils.isNull(metaGroup, "单个分组内的metaId不能重复");
    }

    public MetaGroup queryMetaGroup(String bizLine, Integer group, Long metaId) {
        MetaGroupEntity metaGroup = metaGroupMapper.selectOne(
                Wrappers.lambdaQuery(MetaGroupEntity.class).eq(MetaGroupEntity::getBizLine, bizLine)
                        .eq(MetaGroupEntity::getGroup, group).eq(MetaGroupEntity::getMetaId, metaId));
        return metaTransfer.toMetaGroup(metaGroup);
    }

    public MetaGroup updateMetaGroup(MetaGroup metaGroup) {
        AssertUtils.notNull(metaGroup.getBizLine(), "业务线不能为空");
        AssertUtils.notNull(metaGroup.getGroup(), "分组不能为空");
        AssertUtils.notNull(metaGroup.getMetaId(), "metaId不能为空");
        LambdaUpdateWrapper<MetaGroupEntity> wrapper = Wrappers.lambdaUpdate(MetaGroupEntity.class);
        wrapper.eq(MetaGroupEntity::getBizLine, metaGroup.getBizLine())
                .eq(MetaGroupEntity::getGroup, metaGroup.getGroup())
                .eq(MetaGroupEntity::getMetaId, metaGroup.getMetaId())
                .set(MetaGroupEntity::getAlias, metaGroup.getAlias());
        metaGroupMapper.update(null, wrapper);
        return queryMetaGroup(metaGroup.getBizLine(), metaGroup.getGroup(), metaGroup.getMetaId());
    }

    public MetaGroup createMetaGroup(MetaGroup metaGroup) {
        AssertUtils.notNull(metaGroup.getBizLine(), "业务线不能为空");
        AssertUtils.notNull(metaGroup.getGroup(), "分组不能为空");
        AssertUtils.notNull(metaGroup.getMetaId(), "metaId不能为空");
        checkMetaUniqueInGroup(metaGroup.getBizLine(), metaGroup.getGroup(), metaGroup.getMetaId());
        MetaGroupEntity entity = metaTransfer.toEntity(metaGroup);
        metaGroupMapper.insert(entity);
        return metaTransfer.toMetaGroup(entity);
    }
}
