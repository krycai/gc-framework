package com.allen.plus.config;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisGenPlus {

    public static void generate( String projectPath , String packageName , String author , String[] tableList , String tablePrefix , boolean generateFormAndVo){
        boolean deleteRoot  = true;
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //String projectPath = System.getProperty("user.dir");

        // 需要重新生成 Entity 时修改路径，不要覆盖原来代码
        gc.setFileOverride(true);
        gc.setOutputDir(projectPath);
        gc.setAuthor(author);
        gc.setOpen(true);
        gc.setSwagger2(true);
        gc.setIdType(IdType.ID_WORKER_STR);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        mpg.setGlobalConfig(gc);

        //清空原输出目录
        if(deleteRoot){
            File file = new File(projectPath);
            if(file.exists()){
                FileSystemUtils.deleteRecursively(file);
            }
        }

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://118.31.8.21:3306/microwaveDB?useUnicode=true&characterEncoding=utf8&autoReconnect=true");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("rootroot");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("");
        pc.setParent(packageName);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                //生成vo类
                map.put("voPackage", pc.getParent()+".vo");
                //生成form类
                map.put("formPackage", pc.getParent()+".form");
                //是否自动生成selectVo语句
                map.put("createSelectVo",generateFormAndVo);
                this.setMap(map);
            }
        };


        // 切换为 freemarker 模板引擎
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 增加自定义vo类
        if(generateFormAndVo){
            focList.add(new FileOutConfig("/templates/vo.java.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                    return projectPath + "\\"+  pc.getParent().replaceAll("\\.","/")
                            + "\\vo\\" + tableInfo.getEntityName() + "Vo" + StringPool.DOT_JAVA;
                }
            });
            // 增加自定义vo类
            focList.add(new FileOutConfig("/templates/form.java.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                    return projectPath + "\\"+  pc.getParent().replaceAll("\\.","/")
                            + "\\form\\" + tableInfo.getEntityName() + "Form" + StringPool.DOT_JAVA;
                }
            });
        }

        // 增加自定义xml
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "\\resource\\mapper\\" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);


        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setRestControllerStyle(true);
        strategy.setEntityLombokModel(true);
        strategy.setInclude(tableList);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(tablePrefix);
        //设置tableFill
        List<TableFill> fillList = new ArrayList<>();
        fillList.add(new TableFill("del_flag", FieldFill.INSERT));
        fillList.add(new TableFill("create_user", FieldFill.INSERT));
        fillList.add(new TableFill("create_time", FieldFill.INSERT));
        fillList.add(new TableFill("update_user", FieldFill.UPDATE));
        fillList.add(new TableFill("update_time", FieldFill.INSERT_UPDATE));
        strategy.setTableFillList(fillList);
        //设置逻辑删除键
        strategy.setLogicDeleteFieldName("del_flag");
        mpg.setStrategy(strategy);
        mpg.execute();
    }
}
