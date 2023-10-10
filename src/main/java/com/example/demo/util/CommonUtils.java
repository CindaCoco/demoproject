package com.example.demo.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dto.PageResultDTO;

/**
 * @author linyida
 * @version 1.0
 * @created 2023/10/10.
 */
public class CommonUtils {

    public static PageResultDTO forkPage(Page page){
        PageResultDTO pageResultDTO = new PageResultDTO<>();
        pageResultDTO.setTotalCount(page == null ? 0 : page.getTotal());
        return pageResultDTO;
    }
}
