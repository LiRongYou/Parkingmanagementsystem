package com.example.usermanager.bean;

import java.util.List;

/**
 * @author 黎荣右
 * @date 2022/6/30 19:21
 */

/**
 * 微信小程序预约车位的实体类
 */
public class ArryList {
    private List<ParkingLot> arry1,arry2;

    public ArryList() {
    }

    public ArryList(List<ParkingLot> arry1, List<ParkingLot> arry2) {
        this.arry1 = arry1;
        this.arry2 = arry2;
    }

    public List<ParkingLot> getArry1() {
        return arry1;
    }

    public void setArry1(List<ParkingLot> arry1) {
        this.arry1 = arry1;
    }

    public List<ParkingLot> getArry2() {
        return arry2;
    }

    public void setArry2(List<ParkingLot> arry2) {
        this.arry2 = arry2;
    }

    @Override
    public String toString() {
        return "ArryList{" +
                "arry1=" + arry1 +
                ", arry2=" + arry2 +
                '}';
    }
}
