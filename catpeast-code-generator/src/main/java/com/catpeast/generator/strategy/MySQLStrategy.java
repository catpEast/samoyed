package com.catpeast.generator.strategy;

import com.catpeast.generator.dto.GenerateDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author wyz
 * @Date 2024/6/21 10:39
 * @Description
 */
public class MySQLStrategy implements DBStrategy {
    
    @Override
    public void generate(GenerateDto generateDto) throws ClassNotFoundException, SQLException {
        if (StringUtils.isNotEmpty(generateDto.getDriver())) {
            Class.forName(generateDto.getDriver());
        } else {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        
        Connection connection = DriverManager.getConnection(generateDto.getUrl(), generateDto.getUsername(), generateDto.getPassword());
        
    }
}
