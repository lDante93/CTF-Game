package com.gameengine;

import lombok.Data;

@Data
public class MapMemory {

    private Integer x;
    private Integer y;
    private Integer MapValue;
    private Double GMapValue;
    private Integer HMapValue;
    private Double FMapValue;
    private Boolean isPlayer = false;
    private Boolean isFlag = false;
    private MapMemory parentNode;

    @Override
    public String toString() {
        if(parentNode==null) {
            return "MapMemory{" +
                    "x=" + x +
                    ", y=" + y +
                    ", MapValue=" + MapValue +
                    ", GMapValue=" + GMapValue +
                    ", HMapValue=" + HMapValue +
                    ", FMapValue=" + FMapValue +
                    ", isPlayer=" + isPlayer +
                    ", isFlag=" + isFlag +
                    ", parentNode=" + parentNode +
                    '}';
        }else{
            return "MapMemory{" +
                    "x=" + x +
                    ", y=" + y +
                    ", MapValue=" + MapValue +
                    ", GMapValue=" + GMapValue +
                    ", HMapValue=" + HMapValue +
                    ", FMapValue=" + FMapValue +
                    ", isPlayer=" + isPlayer +
                    ", isFlag=" + isFlag +
                    ", parentNode=(x:" + parentNode.getX() +", y:"+parentNode.getY()+"}"+
                    '}';
        }
    }

}
