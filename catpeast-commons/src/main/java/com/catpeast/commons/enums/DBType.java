package com.catpeast.commons.enums;

/**
 * @Author wyz
 * @Date 2024/6/21 10:22
 * @Description
 */
public enum DBType {

    MySQL(0),
    
    Oracle(1),

    SQLServer(2),

    Postgresql(3),

    TiDB(4),
    
    ;

    private final int type;
    
    DBType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
