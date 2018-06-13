package com.gameengine;

import lombok.Data;

@Data
public class MapMemory {

    private Integer x;
    private Integer y;
    private Integer MapValue;
    private Integer GMapValue;
    private Integer HMapValue;
    private Integer FMapValue;
    private Boolean isPlayer = false;
    private Boolean isFlag = false;

}
