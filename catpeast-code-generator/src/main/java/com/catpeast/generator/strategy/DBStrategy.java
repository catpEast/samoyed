package com.catpeast.generator.strategy;

import com.catpeast.generator.dto.GenerateDto;

/**
 * @Author wyz
 * @Date 2024/6/21 10:36
 * @Description
 */
public interface DBStrategy {
    
    void generate(GenerateDto generateDto) throws Exception;
}
