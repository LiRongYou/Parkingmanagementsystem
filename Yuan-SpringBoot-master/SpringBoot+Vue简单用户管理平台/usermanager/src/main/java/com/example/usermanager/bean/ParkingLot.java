package com.example.usermanager.bean;

/**
 * @author 黎荣右
 * @date 2022/6/30 18:57
 */
public class ParkingLot {
    private String index,select;

    public ParkingLot() {
    }

    public ParkingLot(String index, String select) {
        this.index = index;
        this.select = select;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    @Override
    public String toString() {
        return "ParkingLot{" +
                "index='" + index + '\'' +
                ", select='" + select + '\'' +
                '}';
    }
}
