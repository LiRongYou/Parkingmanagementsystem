package com.example.usermanager.bean;

/**
 * @author 黎荣右
 * @date 2022/6/1 11:01
 */
public class ParkingcarList {
    private String parkingname,classes,havacar,baiche1,baiche2,baiche3,hongche,huangche;

    public ParkingcarList(String parkingname,String classes, String havacar, String baiche1, String baiche2, String baiche3, String hongche, String huangche) {
        this.parkingname=parkingname;
        this.classes = classes;
        this.havacar = havacar;
        this.baiche1 = baiche1;
        this.baiche2 = baiche2;
        this.baiche3 = baiche3;
        this.hongche = hongche;
        this.huangche = huangche;
    }

    public String getParkingname() {
        return parkingname;
    }

    public void setParkingname(String parkingname) {
        this.parkingname = parkingname;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getHavacar() {
        return havacar;
    }

    public void setHavacar(String havacar) {
        this.havacar = havacar;
    }

    public String getBaiche1() {
        return baiche1;
    }

    public void setBaiche1(String baiche1) {
        this.baiche1 = baiche1;
    }

    public String getBaiche2() {
        return baiche2;
    }

    public void setBaiche2(String baiche2) {
        this.baiche2 = baiche2;
    }

    public String getBaiche3() {
        return baiche3;
    }

    public void setBaiche3(String baiche3) {
        this.baiche3 = baiche3;
    }

    public String getHongche() {
        return hongche;
    }

    public void setHongche(String hongche) {
        this.hongche = hongche;
    }

    public String getHuangche() {
        return huangche;
    }

    public void setHuangche(String huangche) {
        this.huangche = huangche;
    }

    @Override
    public String toString() {
        return "ParkingcarList{" +
                "parkingname='" + parkingname + '\'' +
                ", classes='" + classes + '\'' +
                ", havacar='" + havacar + '\'' +
                ", baiche1='" + baiche1 + '\'' +
                ", baiche2='" + baiche2 + '\'' +
                ", baiche3='" + baiche3 + '\'' +
                ", hongche='" + hongche + '\'' +
                ", huangche='" + huangche + '\'' +
                '}';
    }
}
