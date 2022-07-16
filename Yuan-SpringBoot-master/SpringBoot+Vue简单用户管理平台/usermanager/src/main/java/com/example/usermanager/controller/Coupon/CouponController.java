package com.example.usermanager.controller.Coupon;

import com.example.usermanager.Dao.Coupon.CouponDao;
import com.example.usermanager.bean.Coupon;
import com.example.usermanager.bean.UserCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 黎荣右
 * @date 2022/7/10 20:43
 */
@RestController
@RequestMapping("/CouponServlet")
public class CouponController {
    @Autowired
    private JdbcTemplate jdbcTemplate;//使用注解自动创建对象

    @PostMapping("/getcouponlist")
    @ResponseBody
    public List<Coupon> getcouponlist() {
        return CouponDao.getCouponlist(jdbcTemplate);
    }

    @PostMapping("/publiccoupon")
    @ResponseBody
    public boolean publiccoupon(@RequestBody Coupon coupon) {
        return CouponDao.publiccoupon(jdbcTemplate,coupon);
    }

    @PostMapping("/setusercouponlist")
    @ResponseBody
    public void setusercouponlist(@RequestBody UserCoupon userCoupon) {
        CouponDao.insertusercoupon(jdbcTemplate, userCoupon);
    }
    @PostMapping("/getusercouponlist")
    @ResponseBody
    public List<Coupon> getusercouponlist(@RequestBody String openid) {
        return CouponDao.getusercouponlist(jdbcTemplate,openid);
    }
    @PostMapping("/usecoupon")
    @ResponseBody
    public boolean usecoupon(@RequestBody UserCoupon userCoupon) {
        return CouponDao.usecoupon(jdbcTemplate,userCoupon);
    }

    @PostMapping("/DeleteCouponById")
    @ResponseBody
    public boolean DeleteCouponById(@RequestBody String id) {
        return CouponDao.DeleteCouponById(jdbcTemplate,id.substring(0,id.length()-1));
    }

    @PostMapping("/GetCouponById")
    @ResponseBody
    public Coupon GetCouponById(@RequestBody String id) {
        return CouponDao.GetCouponById(jdbcTemplate,id.substring(0,id.length()-1));
    }

    @RequestMapping("/updateCoupon")
    @ResponseBody
    public Boolean UpdateCoupon(@RequestBody Coupon coupon) {
        return CouponDao.UpdateCouponById(jdbcTemplate, coupon);
    }
 }
