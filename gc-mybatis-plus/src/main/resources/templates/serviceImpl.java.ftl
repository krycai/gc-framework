package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
<#if cfg.createSelectVo>
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${cfg.voPackage}.${entity}Vo;
import java.util.List;
import java.util.Map;
</#if>
/**
 * <br>
 * <b>功能：</b>${table.comment!} 服务实现类<br>
 * <b>作者：</b>${author}<br>
 * <b>日期：</b>${.now?string("yyyy")}<br>
 * <b>版权所有：<b>广州弘度信息科技有限公司 版权所有(C) ${.now?string("yyyy")}<br>
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {
  <#if cfg.createSelectVo>

    @Override
    public IPage<${entity}Vo> selectVoByPage(Page<${entity}Vo> page, Map<String, Object> param) {
        return this.baseMapper.selectVo(page, param);
    }

    @Override
    public List<${entity}Vo> selectVo(Map<String, Object> param) {
        return this.baseMapper.selectVo(param);
    }

  </#if>
}
</#if>
