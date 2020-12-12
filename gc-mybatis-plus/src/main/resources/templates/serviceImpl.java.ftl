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
  *  ${table.comment!} Mapper接口
  * @author ${author}  ${.now?string("yyyy/mm/dd hh:mm")}
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
