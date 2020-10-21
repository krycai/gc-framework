package com.allen.plus;

import com.allen.plus.config.MybatisGenPlus;

public class CodeGeneratorPlus {

    public static void main(String[] args) {
        //输出目录
        String projectPath = "E://mybatis//";

        //项目包名
        String packageName = "com.allen";

        //作者名字
        String author = "xuguocai";

        //生成表，如{"sys_user","sys_role"}
        String[] tableList = {"sys_role"};

        //去除表前缀：如sys_
        String tablePrefix = "";

        //是否生成form类和vo类
        boolean generateFormAndVo = false;

        MybatisGenPlus.generate(projectPath , packageName, author, tableList, tablePrefix ,generateFormAndVo);
    }


}