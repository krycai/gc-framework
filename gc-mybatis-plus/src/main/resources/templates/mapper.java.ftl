package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};
<#if cfg.createSelectVo>
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import ${cfg.voPackage}.${entity}Vo;
import java.util.List;
import java.util.Map;
</#if>
 /**
  *  ${table.comment!} Mapper接口
  * @author ${author}  ${.now?string("yyyy/mm/dd hh:mm")}
  */
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {
    <#if cfg.createSelectVo>

    /**
    * 查询列表(按分页)
    * @param page
    * @param sqlMap
    * @return
    */
    Page<${entity}Vo> selectVo(Page<${entity}Vo> page, @Param("sqlMap") Map<String, Object> sqlMap);

    /**
    * 查询vo列表(不分页)
    * @param sqlMap
    * @return
    */
    List<${entity}Vo> selectVo(@Param("sqlMap") Map<String, Object> sqlMap);

    </#if>
}
</#if>
