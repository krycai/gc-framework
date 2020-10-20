//package com.allen.plugin;
//
//import org.mybatis.generator.api.GeneratedXmlFile;
//import org.mybatis.generator.api.IntrospectedTable;
//import org.mybatis.generator.api.PluginAdapter;
//
//import java.util.List;
//
///**
// * @author xuguocai 2020/6/5 10:00
// */
//public class OverwriteXmlPlugin extends PluginAdapter {
//    @Override
//    public boolean validate(List<String> warnings) {
//        return true;
//    }
//
//    @Override
//    public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
//        sqlMap.setMergeable(false);
//        return super.sqlMapGenerated(sqlMap, introspectedTable);
//    }
//
//}
