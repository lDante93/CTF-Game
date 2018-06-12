package com.gameengine;

public class MapMemory {

    private Integer MapValue;
    private Integer GMapValue;
    private Integer HMapValue;
    private Integer FMapValue;

    public Integer getMapValue() {
        return MapValue;
    }

    public void setMapValue(Integer mapValue) {
        MapValue = mapValue;
    }

    public Integer getGMapValue() {
        return GMapValue;
    }

    public void setGMapValue(Integer GMapValue) {
        this.GMapValue = GMapValue;
    }

    public Integer getHMapValue() {
        return HMapValue;
    }

    public void setHMapValue(Integer HMapValue) {
        this.HMapValue = HMapValue;
    }

    public Integer getFMapValue() {
        return FMapValue;
    }

    public void setFMapValue(Integer FMapValue) {
        this.FMapValue = FMapValue;
    }

    @Override
    public String toString() {
        return "MapMemory{" +
                "MapValue=" + MapValue +
                ", GMapValue=" + GMapValue +
                ", HMapValue=" + HMapValue +
                ", FMapValue=" + FMapValue +
                '}';
    }
}
