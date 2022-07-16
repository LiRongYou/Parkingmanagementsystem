package com.example.usermanager.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 黎荣右
 * @date 2022/7/10 22:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCoupon {
    private String openid;
    private List<Coupon> couponList;
}
