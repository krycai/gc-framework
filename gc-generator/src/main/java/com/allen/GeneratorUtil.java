package com.allen;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xuguocai 2020/6/5 9:53
 */
public class GeneratorUtil {

    public static void main(String[] args) throws Exception {
        //MBG 执行过程中的警告信息
        List<String> warnings = new ArrayList<>();
        //当生成的代码重复时，覆盖原代码
        boolean overwrite = true;
        //读取我们的 MBG 配置文件
//        如果 name 以“/”开头，代表绝对路径，从项目根目录开始查找
//        如果 name 不以"/"开头，代表相对路径，以当前class文件所在的目录开始查找
        InputStream is = GeneratorUtil.class.getResourceAsStream("/generator/generatorConfig.xml");
//        File file = new File("E:\\IdeaWorkSpace\\microwave-service\\gc-generator\\src\\main\\resources\\generator\\generatorConfig.xml");
//        InputStream is = new FileInputStream(file);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(is);
        is.close();

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        //创建 MBG
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        //执行生成代码
        myBatisGenerator.generate(null);
        //输出警告信息
        for (String warning : warnings) {
            System.err.println(warning);
        }
        System.out.println("-----success-----");
    }

}
