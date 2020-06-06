package com.allen.sys.service;

import com.allen.sys.model.dto.FormCodeDto;
import com.allen.sys.model.dto.GeneratorParam;
import com.allen.sys.model.po.SysTable;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author xuguocai 2020/6/1 10:43
 */
public interface SysGeneratorService {

    Map<String, String> queryTable(String tableName);

    List<Map<String, String>> queryColumns(String tableName);

    PageInfo<SysTable> findTablePage(GeneratorParam param);

    /**
     * 生成代码
     */
    byte[] generatorCode(FormCodeDto formCodeDto);

}
