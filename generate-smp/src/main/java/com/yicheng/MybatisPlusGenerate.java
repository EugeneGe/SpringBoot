package com.yicheng;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.util.StringUtils;

/**
 * MybatisPlus代码生成器
 *
 * @author EugeneGe
 * @date 2021-05-25 17:34
 */
public class MybatisPlusGenerate {
    /**
     * 代码生成器的配置常量
     */
    private static final String OUTPUT_DIR = "D://Code//Test//PlusCode";
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    //-----------------常用变更----------------------
    private static final String DATABASE_NAME = "data_test";
    private static final String TABLE_NAME = "xxl_job_user";
    private static final Boolean SWAGGER_2 = true;
    //表名，多个英文逗号分割 和 tableName二选一配置
    private static final String SCANNER_TABLE_NAME = "";
    //-----------------数据库配置----------------------
    private static final String DATA_URL = "jdbc:mysql://localhost:3306/" + DATABASE_NAME + "?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private static final String DATA_NAME = "root";
    private static final String DATA_PWD = "123456";
    //-----------------常用变更结束----------------------
    private static final String PARENT_PACKAGE = "com.gly";
    private static final String MODULE_NAME = "rest";
    private static final String MAPPER_NAME = "mapper";
    private static final String SERVICE_NAME = "service";
    private static final String IMPL_NAME = "service.impl";
    private static final String POJO_NAME = "entity";
    private static final String CONTROLLER_NAME = "controller";
    private static final String AUTHOR = "EugeneGe";

    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        // =============================全局配置===============================
        mpg.setGlobalConfig(getGlobalConfig());
        // ================================数据源配置============================
        mpg.setDataSource(getDataSourceConfig());
        //===============================模板引擎配置===============================
        mpg.setTemplateEngine(getFreemarkerTemplateEngine());
        // ===============================包名配置：父包.模块.controller===============================
        mpg.setPackageInfo(getPackageConfig());
        //================   自定义配置    ===========================
        mpg.setCfg(getInjectionConfig());
        // =====================================策略配置==================================
        mpg.setStrategy(getStrategyConfig());
        // 执行生成
        mpg.execute();
    }

    /**
     * 全局配置
     */
    public static GlobalConfig getGlobalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();

        globalConfig.setSwagger2(SWAGGER_2);//  生成swagger2
        globalConfig.setFileOverride(true).setOutputDir(OUTPUT_DIR);// 覆盖输出到xxx目录
        globalConfig.setOpen(false);// 文件夹是否自动打开

        /*--------------------  mapper 配置  start-----------------------------*/
        // 主键生成策略 生成BaseResultMap
        globalConfig.setIdType(IdType.AUTO);
        // 指定作者
        globalConfig.setAuthor(AUTHOR);
        globalConfig.setActiveRecord(true);//是否支持AR模式,继承Model类，开启ActiveRecord模式
        /*--------------------  mapper 配置  end -----------------------------*/

        /*--------------------  mapper 配置  start-----------------------------*/
        // 设置Controller、Service、ServiceImpl、Dao、Mapper文件名称，%s是依据表名转换来的
        globalConfig.setControllerName("%sController").setServiceName("I%sService").setServiceImplName("%sServiceImpl").setMapperName("%sMapper").setXmlName("%sMapper");
        //xml生成 BaseResultMap
        globalConfig.setBaseResultMap(true);//  生成基本ResultMap
        globalConfig.setBaseColumnList(true);// 生成公共的、由所有列名组成的Sql片段
        /*--------------------  mapper 配置  end -----------------------------*/

        return globalConfig;
    }

    /**
     * 数据源配置
     *
     * @return
     */
    public static DataSourceConfig getDataSourceConfig() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        // 用户名、密码、驱动、url
        dataSourceConfig.setUsername(DATA_NAME).setPassword(DATA_PWD)
                .setDbType(DbType.MYSQL).setDriverName(DRIVER_NAME)
                .setUrl(DATA_URL);
        //自动转换字段类型
        dataSourceConfig.setTypeConvert(new ITypeConvert() {
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                String t = fieldType.toLowerCase();
                if (t.contains("datetime") || t.contains("date") || t.contains("timestamp")) {
                    return DbColumnType.STRING;
                }
                //其它字段采用默认转换（非mysql数据库可以使用其它默认的数据库转换器）
                return new MySqlTypeConvert().processTypeConvert(globalConfig, fieldType);
            }
        });
        return dataSourceConfig;
    }

    /**
     * 模板引擎配置
     *
     * @return
     */
    public static FreemarkerTemplateEngine getFreemarkerTemplateEngine() {
        FreemarkerTemplateEngine fte = new FreemarkerTemplateEngine();
        return fte;
    }

    /**
     * 包配置
     */
    public static PackageConfig getPackageConfig() {
        PackageConfig packageConfig = new PackageConfig();
        // 父包名 模块名
        packageConfig.setParent(PARENT_PACKAGE).setModuleName(MODULE_NAME);
        // 分层包名
        packageConfig.setController(CONTROLLER_NAME).setService(SERVICE_NAME).setServiceImpl(IMPL_NAME).setEntity(POJO_NAME).setMapper(MAPPER_NAME);

        return packageConfig;
    }

    /**
     * 自定义配置
     */
    public static InjectionConfig getInjectionConfig() {
        return new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
    }

    /**
     * 策略配置
     *
     * @return
     */
    public static StrategyConfig getStrategyConfig() {
        StrategyConfig strategyConfig = new StrategyConfig();
        // 命名策略：数据库表字段映射到实体的命名策略,默认为no_change，即原样输出，underline_to_camel为下划线转驼峰命名
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.entityTableFieldAnnotationEnable(true);

//        strategyConfig.setSuperEntityClass("com.smartever.common.core.web.domain.BaseEntity");

        //是否给实体类使用lombok
//        strategyConfig.setEntityLombokModel(true);
//        strategyConfig.setEntityBuilderModel(true);
        // controller生成@RestCcontroller 是否直接返回json
        strategyConfig.setRestControllerStyle(true);
//        strategyConfig.setControllerMappingHyphenStyle(false);
        //表前缀
//        strategyConfig.setTablePrefix("my_","you_");
        // 需要排除生成的表
        if (!StringUtils.isEmpty(SCANNER_TABLE_NAME)) {
            strategyConfig.setExclude(SCANNER_TABLE_NAME);
        }
        //TODO 需要生成的表,Include与Exclude只能二选一
        if (!StringUtils.isEmpty(TABLE_NAME)) {
            strategyConfig.setInclude(TABLE_NAME);
        }
        return strategyConfig;
    }
}
