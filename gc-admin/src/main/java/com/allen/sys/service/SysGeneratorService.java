package com.allen.sys.service;


import cn.com.bluemoon.qy.pojo.dto.GeneratorDto;
import com.allen.sys.model.dto.FormCodeDto;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author xuguocai 2020/6/1 10:43
 */
public interface SysGeneratorService {

    Map<String, String> queryTable(String tableName);

    List<Map<String, String>> queryColumns(String tableName);

    PageInfo<SysTable> findTablePage(GeneratorDto generatorDto);

    /**
     * 生成代码
     */
    byte[] generatorCode(FormCodeDto formCodeDto);

}
