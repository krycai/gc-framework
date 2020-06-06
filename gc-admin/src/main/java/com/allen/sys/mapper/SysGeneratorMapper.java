package com.allen.sys.mapper;

import com.allen.sys.model.po.SysTable;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author: allen小哥 2020-06-05 22:23
 **/
public interface SysGeneratorMapper extends Mapper<SysTable> {

    List<SysTable> findTablePage(@Param("tableName") String tableName);

    Map<String, String> queryTable(String tableName);

    List<Map<String, String>> queryColumns(String tableName);
}
