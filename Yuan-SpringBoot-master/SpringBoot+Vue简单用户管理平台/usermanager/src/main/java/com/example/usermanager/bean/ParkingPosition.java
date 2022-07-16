package com.example.usermanager.bean;

/**
 * @author 黎荣右
 * @date 2022/6/2 12:46
 */
public class ParkingPosition {
    private String parkingname,lng,lat;

    public ParkingPosition() {
    }

    public ParkingPosition(String parkingname, String lng, String lat) {
        this.parkingname = parkingname;
        this.lng = lng;
        this.lat = lat;
    }

    public String getParkingname() {
        return parkingname;
    }

    public void setParkingname(String parkingname) {
        this.parkingname = parkingname;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
