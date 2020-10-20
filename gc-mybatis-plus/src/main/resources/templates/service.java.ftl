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
 * <br>
 * <b>功能：</b>${table.comment!} 服务类<br>
 * <b>作者：</b>${author}<br>
 * <b>日期：</b>${.now?string("yyyy")}<br>
 * <b>版权所有：<b>广州弘度信息科技有限公司 版权所有(C) ${.now?string("yyyy")}<br>
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
