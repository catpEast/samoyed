package com.catpeast.generator.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @Author wyz
 * @Date 2024/6/21 11:07
 * @Description
 */
@Data
@Builder
public class GenerateDto {
    
    private String url;
    
    private String username;
    
    private String password;
    
    private String driver;
    
    private String packagePath;
    
}
