package com.example.demo.controller;

import com.example.demo.dto.MetaDTO;
import com.example.demo.dto.MetaGroupDTO;
import com.example.demo.dto.PageResultDTO;
import com.example.demo.service.MetaService;
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

    @PostMapping("/meta")
    public MetaDTO insert(@RequestBody MetaDTO metaDTO){
        return metaService.insertMeta(metaDTO);
    }

    @GetMapping("/meta/page/{pageNow}/{pageSize}")
    public PageResultDTO<MetaDTO> queryList(@PathVariable Integer pageNow, @PathVariable Integer pageSize){
        return metaService.queryList(pageNow, pageSize);
    }

    @GetMapping("metagroup/{groupName}/page/{pageNow}/{pageSize}")
    public PageResultDTO<MetaGroupDTO> queryList(@PathVariable String groupName, @PathVariable Integer pageNow, @PathVariable Integer pageSize){
        return null;
    }

    @PutMapping("/metagroup")
    public MetaGroupDTO updateMetaGroup(@RequestBody MetaGroupDTO metaGroupDTO){
        return null;
    }

    @PostMapping("/metagroup")
    public MetaGroupDTO addMetaGroup(@RequestBody MetaGroupDTO metaGroupDTO){
        return null;
    }
}
