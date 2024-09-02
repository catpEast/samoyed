package generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import java.nio.file.Paths;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author wyz
 * @Date 2024/8/29 14:37
 * @Description
 */
public class CodeGenerator {
    
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/test01?characterEncoding=UTF-8&useUnicode=true&useSSL=false&tinyInt1isBit=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";
    private static final String PACKAGE = "com.samoyed.datapermission";
    
    public static void main(String[] args) {
        codeGenerate();
    }
    
    public static void codeGenerate() {
        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
            .globalConfig(builder -> builder
                .author("wyz")
                .outputDir(Paths.get(System.getProperty("user.dir")) + "/src/main/java")
                .commentDate("yyyy-MM-dd")
            )
            .packageConfig(builder -> builder
                .parent(PACKAGE)
                .entity("entity")
                .mapper("mapper")
                .service("service")
                .serviceImpl("service.impl")
                .xml("mapper.xml")
            )
            .strategyConfig(builder -> builder
                // entity
                .entityBuilder()
                .disableSerialVersionUID()
                .enableChainModel()
                .logicDeleteColumnName("del_flag")
                .addSuperEntityColumns("created_by", "created_time", "updated_by", "updated_time")
                .idType(IdType.ASSIGN_ID)
                .addTableFills(
                    new Column("create_time", FieldFill.INSERT),
                    new Column("update_time", FieldFill.INSERT_UPDATE)
                )
                .enableLombok()
                .enableTableFieldAnnotation()
                .formatFileName("%sEntity")
                .enableFileOverride()
                // controller
                .controllerBuilder()
                .formatFileName("%sController")
                .enableRestStyle()
                .enableFileOverride()
                // service
                .serviceBuilder()
                .formatServiceFileName("%sService")
                .formatServiceImplFileName("%sServiceImpl")
                .enableFileOverride()
                // mapper
                .mapperBuilder()
                .superClass(BaseMapper.class)
                .formatMapperFileName("%sMapper")
                .formatXmlFileName("%sXml")
                .mapperAnnotation(Mapper.class)
                .enableBaseResultMap()
                .enableBaseResultMap()
                .enableBaseColumnList()
                .enableFileOverride()
            )
            .templateEngine(new FreemarkerTemplateEngine())
            .execute();
    }
}
