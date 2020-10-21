package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
<#if cfg.createSelectVo>
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${cfg.voPackage}.${entity}Vo;
import java.util.List;
import java.util.Map;
</#if>
 /**
  *  ${table.comment!} Mapper接口
  * @author ${author}  ${.now?string("yyyy/mm/dd hh:mm")}
  */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {
<#if cfg.createSelectVo>

    /**
    * 查询VO列表(分页)
    * @param page
    * @param param
    * @return
    */
    IPage<${entity}Vo> selectVoByPage(Page<${entity}Vo> page, Map<String, Object> param);

    /**
    * 查询VO列表
    * @param param
    * @return
    */
    List<${entity}Vo> selectVo(Map<String, Object> param);

</#if>
}
</#if>
