package com.gen.codegen;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeGenApplication {

    /**
     * 自定义配置
     * TODO 请按需修改
     */
    private static final String YOUR_BASE_NAME = "code_gen";
    private static final String[] YOUR_TABLE_NAMES = {"user"};
    private static final String YOUR_NAME = "code-gen";
    private static final String YOUR_MODULE_NAME = "codegen";
    private static final String YOUR_PROJECT_DIR = "/tmp/codegen";
    /**
     * 是否需要自动生成insert update detail delete接口
     * 默认不生成,需要生成的话,修改👇🏻参数
     */
    private static final boolean GENERATE_CURD = true;
    /**
     * 是否需要自动生返回ID的insert接口
     * 默认不生成,需要生成的话,修改👇🏻参数
     */
    private static final boolean GENERATE_INSERT_RETURN_ID = true;

    /**
     * 是否需要导入导出Excel模板接口
     * 默认不生成,需要生成的话,修改👇🏻参数
     */
    private static final boolean GENERATE_EXCEL_TEMPLATE = false;
    /**
     *  是否需要生成 Feign 模板接口
     * 默认不生成,需要生成的话,修改👇🏻参数
     */
    private static final boolean GENERATE_FEGIN_TEMPLATE = true;

    /**
     * DataSource config
     * tableNames : {多个表用逗号分隔}
     */
    private static final String DATE_SOURCE_URL =
            "jdbc:mysql://localhost:3306/" +
                    YOUR_BASE_NAME +
                    "?useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false&serverTimezone=Asia/Shanghai&useOldAliasMetadataBehavior=true&useSSL=false";
    private static final String DATA_SOURCE_USER_NAME = "root";
    private static final String DATA_SOURCE_PASS_WORD = "root";
    private static final String DATA_SOURCE_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String[] DATA_TABLE_NAMES = YOUR_TABLE_NAMES;

    /**
     * Project config
     */
    private static final String AUTHOR = YOUR_NAME;
    private static final String PROJECT_DIR = YOUR_PROJECT_DIR;
    private static final String PARENT = "com.gen";
    private static final String MODULE_NAME = YOUR_MODULE_NAME;


    /**
     * 下面是一些可选的配置
     */
    private static final String MAPPER_TEMPLATE_PATH = "/templates/mapper.xml.vm";
    private static final String CONTROLLER_TEMPLATE_PATH = "/templates/controller.java.vm";
    private static final String DTO_TEMPLATE_PATH = "/templates/dto.java.vm";
    private static final String EXCEL_DTO_TEMPLATE_PATH = "/templates/excelDto.java.vm";
    private static final String VO_TEMPLATE_PATH = "/templates/vo.java.vm";
    private static final String I_FEIGN_TEMPLATE_PATH = "/templates/Ifeign.java.vm";
    private static final String FEIGN_TEMPLATE_PATH = "/templates/feign.java.vm";
    private static final String DELETE_FLAG = "deleted";
    private static final String VERSION = "version";
    private static final DbType DB_TYPE = DbType.MYSQL;
    private static final ITypeConvert I_TYPE_CONVERT = new MySqlTypeConvert();
    /**
     * 表名生成策略
     */
    private static final NamingStrategy TABLE_STRATEGY = NamingStrategy.underline_to_camel;

    private static final String FRAME_DIR = PARENT.replace(".", "/");
    private static final String ROOT_DIR = PROJECT_DIR + "/src/main/";
    private static final String DTO_DIR = ROOT_DIR + "java/" + FRAME_DIR + "/" + MODULE_NAME + "/dto/";
    private static final String VO_DIR = ROOT_DIR + "java/" + FRAME_DIR + "/" + MODULE_NAME + "/vo/";
    private static final String EXCEL_DTO_DIR = ROOT_DIR + "java/" + FRAME_DIR + "/" + MODULE_NAME + "/excel/";
    private static final String CONTROLLER_DIR = ROOT_DIR + "java/" + FRAME_DIR + "/" + MODULE_NAME + "/controller/";
    private static final String FEIGN_DIR = ROOT_DIR + "java/" + FRAME_DIR + "/" + MODULE_NAME + "/feign/";
    private static final String FEIGN_IMPL_DIR = ROOT_DIR + "java/" + FRAME_DIR + "/" + MODULE_NAME + "/feign/impl/";
    private static final String MAPPER_DIR = ROOT_DIR + "resources/mapper/";

    private static final List<TableFill> tableFills = new ArrayList<>();

    /**
     *  需要填充的 字段
     */
    static {
        TableFill isDeleted = new TableFill("deleted", FieldFill.INSERT);
        TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
        TableFill createUserId = new TableFill("create_user_id", FieldFill.INSERT);
        TableFill updateTime = new TableFill("update_time", FieldFill.UPDATE);
        TableFill updateUserId = new TableFill("update_user_id", FieldFill.UPDATE);
        tableFills.add(isDeleted);
        tableFills.add(createTime);
        tableFills.add(createUserId);
        tableFills.add(updateTime);
        tableFills.add(updateUserId);
    }

    public static void main(String[] args) {

        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(ROOT_DIR + "java");
        gc.setFileOverride(true);
        // 不需要ActiveRecord特性的请改为false
        gc.setActiveRecord(true);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(true);
        gc.setAuthor(AUTHOR);
        //
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DB_TYPE);
        dsc.setTypeConvert(I_TYPE_CONVERT);
        dsc.setDriverName(DATA_SOURCE_DRIVER_NAME);
        dsc.setUsername(DATA_SOURCE_USER_NAME);
        dsc.setPassword(DATA_SOURCE_PASS_WORD);
        dsc.setUrl(DATE_SOURCE_URL);
        dsc.setTypeConvert(new MySqlTypeConvert() {
            @Override
            public IColumnType processTypeConvert(GlobalConfig config, String fieldType) {
                if (fieldType.toLowerCase().contains("bit")) {
                    return DbColumnType.BOOLEAN;
                }
                return super.processTypeConvert(config, fieldType);
            }
        });
        mpg.setDataSource(dsc);
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
//        strategy.setTablePrefix(Prefix);// 此处可以修改为您的表前缀
        // 表名生成策略
        strategy.setNaming(TABLE_STRATEGY);
        // 需要生成的表
        strategy.setInclude(DATA_TABLE_NAMES);
        strategy.setLogicDeleteFieldName(DELETE_FLAG);
        strategy.setVersionFieldName(VERSION);
        strategy.setRestControllerStyle(true);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTableFillList(tableFills);
        mpg.setStrategy(strategy);
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(PARENT);
        pc.setModuleName(MODULE_NAME);
        mpg.setPackageInfo(pc);
        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>(16);
                map.put("success", this.getConfig().getGlobalConfig().getAuthor() + "\t You are success");
                map.put("generateCurd", GENERATE_CURD);
                map.put("basePackage", PARENT);
                map.put("generateInsertReturnId", GENERATE_INSERT_RETURN_ID);
                map.put("generateExcelTemplate", GENERATE_EXCEL_TEMPLATE);
                this.setMap(map);
            }
        };
        // 自定义 xxList.jsp 生成
        List<FileOutConfig> focList = new ArrayList<>();

        // 调整 xml 生成目录演示
        focList.add(new FileOutConfig(MAPPER_TEMPLATE_PATH) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return MAPPER_DIR + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        focList.add(new FileOutConfig(CONTROLLER_TEMPLATE_PATH) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return CONTROLLER_DIR + tableInfo.getEntityName() + "Controller.java";
            }
        });
        focList.add(new FileOutConfig(DTO_TEMPLATE_PATH) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return DTO_DIR + tableInfo.getEntityName() + "DTO.java";
            }
        });
        focList.add(new FileOutConfig(VO_TEMPLATE_PATH) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return VO_DIR + tableInfo.getEntityName() + "VO.java";
            }
        });

        if (GENERATE_FEGIN_TEMPLATE){
            focList.add(new FileOutConfig(I_FEIGN_TEMPLATE_PATH) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return FEIGN_DIR + "I" + tableInfo.getEntityName() + "Feign.java";
                }
            });
            focList.add(new FileOutConfig(FEIGN_TEMPLATE_PATH) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return FEIGN_IMPL_DIR + tableInfo.getEntityName() + "Feign.java";
                }
            });
        }

        //excel
        if (GENERATE_EXCEL_TEMPLATE) {
            focList.add(new FileOutConfig(EXCEL_DTO_TEMPLATE_PATH) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return EXCEL_DTO_DIR + tableInfo.getEntityName() + "ExcelDTO.java";
                }
            });
        }
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        // 关闭默认 xml 生成，调整生成 至 根目录
        TemplateConfig tc = new TemplateConfig();
        tc.setXml(null);
        mpg.setTemplate(tc);
        // 执行生成
        mpg.execute();
        // 打印注入设置【可无】
        System.out.println(mpg.getCfg().getMap().get("success"));
    }

}
