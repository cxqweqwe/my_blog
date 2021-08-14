package com.fang.backgroundapi;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

/**
 * 代码生成器类
 * @author Bernie_xin
 * @create 2020/10/1 21:33
 **/
public class FangCode {

    public static void main(String[] args) {
        //新建一个代码生成器类
        AutoGenerator autoGenerator = new AutoGenerator();
        //配置策略

        //1、全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");//获取当前系统的目录
        System.out.println("看看这里是不是我要的========================================"+projectPath);
        globalConfig.setOutputDir(projectPath + "/background-api/src/main/java");//设置代码生成的输出目录
        globalConfig.setAuthor("Bernie_fang");//设置作者名字
        globalConfig.setOpen(false);//设置不要打开文件夹管理器
        globalConfig.setFileOverride(false);//是否覆盖原来的代码
        globalConfig.setServiceName("%sService");//设置去掉Server的I前缀
        globalConfig.setIdType(IdType.ASSIGN_UUID);//设置id生成的类型
        globalConfig.setDateType(DateType.ONLY_DATE);//设置date的类型，只有日期
        globalConfig.setSwagger2(false);//设置配置swagger2
        autoGenerator.setGlobalConfig(globalConfig);//交给自动生成器

        //2、设置数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        // TODO ：数据库链接
        dataSourceConfig.setUrl("jdbc:mysql://120.79.64.26:3306/forum_blog?useSSl=false&useUnicode=true&characterEncoding=utf-8");
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setUsername("forum_blog");
        dataSourceConfig.setPassword("123456");
        dataSourceConfig.setDbType(DbType.MYSQL);
        autoGenerator.setDataSource(dataSourceConfig);//交给自动生成器


        //3、包的配置
        PackageConfig packageConfig = new PackageConfig();
        // packageConfig.setModuleName("blog");//设置包的名字
        packageConfig.setParent("com.fang.backgroundapi"); //设置包的路径
        packageConfig.setEntity("pojo.DO");//设置实体类包名
        packageConfig.setMapper("mapper");//设置mapper包名
        packageConfig.setService("service");//设置server包名
        packageConfig.setController("controller");//设置controller包名
        autoGenerator.setPackageInfo(packageConfig);

        //4、策略配置
        StrategyConfig strategy = new StrategyConfig();
        // TODO :表名字
        strategy.setInclude("article_comment","blog_info","favorites_bar","label_class","label_record",
                "likes_record","port_comment","post_info","report_info","sys_authority","sys_users",
                "sys_users_authority","");//映射表名字，可以多个
        strategy.setNaming(NamingStrategy.underline_to_camel);//支持下划线转驼峰命名
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//列的名字 也支持下划线转驼峰命名
        // strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);//使用lombok开启注解
        strategy.setRestControllerStyle(true);
        strategy.setLogicDeleteFieldName("deleted");//设置逻辑删除字段

        //5、自动填充策略
        TableFill create = new TableFill("created_time", FieldFill.INSERT);//设置create字段自动填充
        TableFill modified = new TableFill("modified_time", FieldFill.INSERT_UPDATE);//设置modified字段自动填充
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(create);
        tableFills.add(modified);
        strategy.setTableFillList(tableFills);//自动填充

        // 乐观锁
        // strategy.setVersionFieldName("version");
        // strategy.setRestControllerStyle(true);//设置驼峰命名格式
        // strategy.setControllerMappingHyphenStyle(true);//使用下划线访问请求，http://localhast:8080/hello_id_2格式


        autoGenerator.setStrategy(strategy);

        autoGenerator.execute();//执行
    }


}
