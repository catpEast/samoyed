package com.catpeast.generator;

import com.catpeast.commons.enums.DBType;
import com.catpeast.generator.dto.GenerateDto;
import com.catpeast.generator.strategy.DBStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author wyz
 * @Date 2024/6/21 10:20
 * @Description
 */
public class CodeGenerator {
    
    private static final Logger logger = LoggerFactory.getLogger(CodeGenerator.class);
    
    private DBStrategy dbStrategy;

    public CodeGenerator(DBStrategy dbStrategy) {
        this.dbStrategy = dbStrategy;
    }

    public void generate(GenerateDto generateDto) throws Exception {
        dbStrategy.generate(generateDto);
        logger.info("11111");
    }

    public static void main(String[] args) {
        // generate(DBType.MySQL);
    }
}
