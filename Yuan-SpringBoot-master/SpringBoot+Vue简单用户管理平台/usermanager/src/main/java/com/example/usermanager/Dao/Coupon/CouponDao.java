package com.example.usermanager.Dao.Coupon;

import com.example.usermanager.bean.Coupon;
import com.example.usermanager.bean.UserCoupon;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author 黎荣右
 * @date 2022/7/10 20:34
 */
public class CouponDao {
    /**
     * 优惠券列表
     *
     * @param jdbcTemplate
     * @return 列表
     */
    public static List<Coupon> getCouponlist(JdbcTemplate jdbcTemplate) {
        //数据库操作
        String sql = "select * from coupon ";//SQL查询语句
        //查询
        List<Coupon> couponlists = jdbcTemplate.query(sql, (resultSet, i) -> {
            Coupon couponlist = new Coupon(
                    resultSet.getString(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getString(4),
                    resultSet.getString(5), resultSet.getString(6),
                    resultSet.getString(7), resultSet.getString(8)
            );
            return couponlist;
        });
        return couponlists;
    }

    /**
     * 发布新的优惠券
     *
     * @param jdbcTemplate
     * @param coupon
     * @return
     */
    public static boolean publiccoupon(JdbcTemplate jdbcTemplate, Coupon coupon) {
        String sql = "insert into coupon values(?,?,?,?,?,?,?,?)";
        Object[] args = {coupon.getId(), coupon.getTag(), coupon.getDiscount(), coupon.getMin(), coupon.getCoupon_name(), coupon.getCoupon_desc(), coupon.getDays(), coupon.getReceive()};
        int update = jdbcTemplate.update(sql, args);
        if (update == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 存入用户领取的优惠卷
     *
     * @param jdbcTemplate
     * @param userCoupon
     * @return
     */
    public static boolean insertusercoupon(JdbcTemplate jdbcTemplate, UserCoupon userCoupon) {
        String sql = "insert into usercoupon values(?,?,?,?,?,?,?,?,?)";
        int flag = 0;
        for (Coupon c : userCoupon.getCouponList()) {
            Object[] args = {userCoupon.getOpenid(), c.getId(), c.getTag(), c.getDiscount(), c.getMin(),
                    c.getCoupon_name(), c.getCoupon_desc(), c.getDays(), c.getReceive()};
            int update = jdbcTemplate.update(sql, args);
            flag = flag + update;
        }
        if (flag == userCoupon.getCouponList().size()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取用户已领取的优惠券
     *
     * @param jdbcTemplate
     * @param openid
     * @return
     */
    public static List<Coupon> getusercouponlist(JdbcTemplate jdbcTemplate, String openid) {
        //数据库操作
        String sql = "select * from usercoupon where openid=?";//SQL查询语句
        //查询
        List<Coupon> couponlists = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Coupon.class), openid);
        return couponlists;
    }

    /**
     * 删除使用过的优惠券
     *
     * @param jdbcTemplate
     * @param userCoupon
     * @return
     */
    public static boolean usecoupon(JdbcTemplate jdbcTemplate, UserCoupon userCoupon) {
        String sql = "delete from usercoupon where openid=? and id = ?";
        List<Coupon> coupons = userCoupon.getCouponList();
        int update = 0;
        for (Coupon c : coupons) {
            Object[] args = {userCoupon.getOpenid(), c.getId()};
            update = jdbcTemplate.update(sql, args);
        }
        if (update == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 下架优惠券
     * @param jdbcTemplate
     * @param id
     * @return
     */
    public static boolean DeleteCouponById(JdbcTemplate jdbcTemplate, String id) {
        String sql = "delete from coupon where id = ?";
        Object[] args = {id};
        int update = jdbcTemplate.update(sql, args);
        if (update == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 修改优惠券
     * @param jdbcTemplate
     * @param coupon
     * @return
     */
    public static boolean UpdateCouponById(JdbcTemplate jdbcTemplate, Coupon coupon) {
        String sql = "update coupon set tag=?,discount=?,min=?,coupon_name=?,coupon_desc=?,days=?,receive=? where id=?";
        Object[] args = {coupon.getTag(),coupon.getDiscount(),coupon.getMin(),coupon.getCoupon_name(),coupon.getCoupon_desc(),coupon.getDays(),coupon.getReceive(),coupon.getId()};
        int update = jdbcTemplate.update(sql,args);
        if (update == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据id获取相应的优惠券
     * @param jdbcTemplate
     * @param id
     * @return
     */
    public static Coupon GetCouponById(JdbcTemplate jdbcTemplate, String id) {
        String sql = "select * from coupon where id=?";
        Coupon coupon = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<Coupon>(Coupon.class),id);//rowmapper帮我们封装，是个接口，返回不同数据类型，使用这个接口里面实现类完成数据封装装
        return coupon;
    }
}
