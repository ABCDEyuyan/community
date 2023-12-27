package com.example;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Collections;

/**
 * @Author : ZL
 */
public class MybtaisPlusCodeAutoGeneration {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/clearly_understand?characterEncoding=UTF-8&useUnicode=true&useSSL=false", "root", "123456")
                // 全局配置
                .globalConfig(builder -> {
                    builder.author("ZL") // 设置作者
                            .outputDir("D:\\community\\community-generator\\src\\main\\java") // 指定输出目录
                            .disableOpenDir() //禁止打开输出目录，默认打开
                            .enableSwagger()
                    ;
                })
                // 包配置
                .packageConfig(builder -> {
                    builder.parent("com.example") // 设置父包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D:\\community\\community-generator\\src\\main\\resources\\mapper\\")); // 设置mapperXml生成路径
                })
                // 策略配置
                .strategyConfig(builder -> {
                    builder.addInclude("post") // 设置需要生成的表名
//                            .addTablePrefix("sys_") // 设置过滤表前缀
                            // Entity 策略配置
                            .entityBuilder()
                            .enableFileOverride() // 覆盖已生成文件
                            .enableLombok() //开启 Lombok
                            .naming(NamingStrategy.underline_to_camel)  //数据库表映射到实体的命名策略：下划线转驼峰命
                            .columnNaming(NamingStrategy.underline_to_camel)    //数据库表字段映射到实体的命名策略：下划线转驼峰命
                            // Mapper 策略配置
                            .mapperBuilder()
                            .enableFileOverride() // 覆盖已生成文件
                            // Service 策略配置
                            .serviceBuilder()
                            .enableFileOverride() // 覆盖已生成文件
                            .formatServiceFileName("%sService") //格式化 service 接口文件名称，%s进行匹配表名，如 UserService
                            .formatServiceImplFileName("%sServiceImpl") //格式化 service 实现类文件名称，%s进行匹配表名，如 UserServiceImpl
                            // Controller 策略配置
                            .controllerBuilder()
                            .enableRestStyle()
                            .enableFileOverride() // 覆盖已生成文件
                    ;
                })
                .execute();

//        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
//                // 全局配置
//                .globalConfig((scanner, builder) -> builder.author(scanner.apply("请输入作者名称？")).fileOverride())
//                // 包配置
//                .packageConfig((scanner, builder) -> builder.parent(scanner.apply("请输入包名？")))
//                // 策略配置
//                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
//                        .controllerBuilder().enableRestStyle().enableHyphenStyle()
//                        .entityBuilder().enableLombok().addTableFills(
//                                new Column("create_time", FieldFill.INSERT)
//                        ).build())
//                /*
//                    模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
//                   .templateEngine(new BeetlTemplateEngine())
//                   .templateEngine(new FreemarkerTemplateEngine())
//                 */
//                .execute();
//
//
//// 处理 all 情况
//        protected static List<String> getTables(String tables) {
//            return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));

    }
}
