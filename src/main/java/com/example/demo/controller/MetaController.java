package com.example.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dto.MetaDTO;
import com.example.demo.dto.MetaGroupDTO;
import com.example.demo.dto.PageResultDTO;
import com.example.demo.model.Meta;
import com.example.demo.model.MetaGroup;
import com.example.demo.service.MetaService;
import com.example.demo.util.CommonUtils;
import com.example.demo.util.mapstruct.MetaTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linyida
 * @version 1.0
 * @created 2023/9/28.
 */
@RestController
@RequestMapping("/management")
public class MetaController {

    @Autowired
    MetaService metaService;
    @Autowired
    MetaTransfer metaTransfer;

    @PostMapping("/meta")
    public MetaDTO insert(@RequestBody MetaDTO metaDTO){
        Meta meta = metaService.createMeta(metaTransfer.toMeta(metaDTO));
        return metaTransfer.toDTO(meta);
    }

    @GetMapping("/meta/{metaId}")
    public MetaDTO query(@PathVariable Long metaId){
        Meta meta = metaService.queryMetaByMetaId(metaId);
        return metaTransfer.toDTO(meta);
    }

    @GetMapping("/meta/page/{pageNow}/{pageSize}")
    public PageResultDTO<MetaDTO> queryList(@PathVariable Integer pageNow, @PathVariable Integer pageSize){
        Page<Meta> metaPage = metaService.queryMetaList(pageNow, pageSize);
        PageResultDTO<MetaDTO> pageResultDTO = CommonUtils.forkPage(metaPage);
        pageResultDTO.setDataList(metaTransfer.toMetaDTOList(pageResultDTO.getDataList()));
        return pageResultDTO;
    }

    @GetMapping("metagroup/{groupName}/page/{pageNow}/{pageSize}")
    public PageResultDTO<MetaGroupDTO> queryList(@PathVariable String groupName, @PathVariable Integer pageNow, @PathVariable Integer pageSize){
        Page<MetaGroup> metaGroupPage = metaService.queryMetaGroupList(groupName, pageNow, pageSize);
        PageResultDTO<MetaGroupDTO> pageResultDTO = CommonUtils.forkPage(metaGroupPage);
        pageResultDTO.setDataList(metaTransfer.toMetaGroupDTOList(metaGroupPage.getRecords()));
        return pageResultDTO;
    }

    @PutMapping("/metagroup")
    public MetaGroupDTO updateMetaGroup(@RequestBody MetaGroupDTO metaGroupDTO){
        MetaGroup metaGroup = metaService.updateMetaGroup(metaTransfer.toMetaGroup(metaGroupDTO));
        return metaTransfer.toDTO(metaGroup);
    }

    @PostMapping("/metagroup")
    public MetaGroupDTO createMetaGroup(@RequestBody MetaGroupDTO metaGroupDTO){
        MetaGroup metaGroup = metaService.createMetaGroup(metaTransfer.toMetaGroup(metaGroupDTO));
        return metaTransfer.toDTO(metaGroup);
    }
}
