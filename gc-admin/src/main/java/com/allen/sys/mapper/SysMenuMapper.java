package com.allen.sys.mapper;

import com.allen.sys.model.po.SysMenu;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SysMenuMapper extends Mapper<SysMenu> {
    /**
     * 根据用户id判断用户是否超级管理员
     *
     * @param userId the user id
     * @return the int
     */
    int checkUserIsSysAdmin(@Param("userId") Integer userId);

    /**
     * 菜单权限过滤查询(管理员与非管理员)
     * @param map
     * @return
     */
    List<SysMenu> findListByParam(@Param("param") Map<String,Object> map);

    /**
     * 根据用户查询菜单
     *
     * @param userId the user id
     * @return the list
     */
    List<SysMenu> findListByUserId(Integer userId);

}