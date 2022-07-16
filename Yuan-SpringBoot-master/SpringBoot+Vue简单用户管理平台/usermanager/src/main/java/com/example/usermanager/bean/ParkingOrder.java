package com.example.usermanager.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 黎荣右
 * @date 2022/6/30 21:13
 */
//订单
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingOrder {
    private String openid,parkingname,price,parkingspace,time,duration,totalprice,complete;
}
