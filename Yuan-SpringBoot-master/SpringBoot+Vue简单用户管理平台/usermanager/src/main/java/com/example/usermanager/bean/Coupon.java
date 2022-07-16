package com.example.usermanager.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 黎荣右
 * @date 2022/7/10 20:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {
    private String id,tag,discount,min,coupon_name,coupon_desc,days,receive;
}
