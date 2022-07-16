package com.example.usermanager.Dao.Parking;

import com.example.usermanager.bean.ArryList;
import com.example.usermanager.bean.ParkingLot;
import com.example.usermanager.bean.ParkingOrder;
import com.example.usermanager.bean.ParkingcarList;
import com.example.usermanager.service.impl.TimeComparisonImpl;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 黎荣右
 * @date 2022/6/1 11:07
 */
public class GetParkingcarListDao {
    /**
     * 按停车场名字获取停车场车辆停放列表
     *
     * @param jdbcTemplate
     * @return 列表
     */
    public static List<ParkingcarList> getparkingcarlist(JdbcTemplate jdbcTemplate, String parkingname) {
        //数据库操作
        String sql = "select * from parkingcar where parkingname=?";//SQL查询语句
        //查询
        List<ParkingcarList> parkingcarLists = jdbcTemplate.query(sql, (resultSet, i) -> {
            ParkingcarList parkingcarList = new ParkingcarList(resultSet.getString(1),
                    resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getString(5),
                    resultSet.getString(6), resultSet.getString(7), resultSet.getString(8)
            );
            return parkingcarList;
        }, parkingname);

        return parkingcarLists;
    }

    /**
     * 返回微信小程序需要的停车列表
     *
     * @param jdbcTemplate
     * @param parkingname
     * @return
     */
    public static ArryList getparkinglot(JdbcTemplate jdbcTemplate, String parkingname) {
        List<ParkingcarList> parkingcarLists = getparkingcarlist(jdbcTemplate, parkingname);
        List<ParkingLot> arry1 = new ArrayList<>();
        List<ParkingLot> arry2 = new ArrayList<>();

        for (int i = 0; i < parkingcarLists.size(); i++) {
            ParkingcarList parkingcarList = parkingcarLists.get(i);
            ParkingLot parkingLot = new ParkingLot();

            parkingLot.setIndex(parkingcarList.getClasses().substring(6, 7) + "-" + parkingcarList.getClasses().substring(7));
            if (parkingcarList.getHavacar().equals("true")) {
                parkingLot.setSelect("1");
            } else {
                parkingLot.setSelect("0");
            }

            if (parkingcarList.getClasses().substring(6, 7).equals("1")) {
                arry1.add(parkingLot);
            } else {
                arry2.add(parkingLot);
            }

        }
        ArryList arryList = new ArryList(arry1, arry2);
        return arryList;
    }

    /**
     * 根据预约修改停车情况
     *
     * @param jdbcTemplate
     * @param parkingcarList
     * @return
     */
    public static int UpdateParkingLot(JdbcTemplate jdbcTemplate, ParkingcarList parkingcarList) {
        String sql = "update parkingcar set havacar=?,hongche=? where parkingname=? and classes=?";
        Object[] args = {"true", "true", parkingcarList.getParkingname(), parkingcarList.getClasses()};
        int update = jdbcTemplate.update(sql, args);
        return update;
    }

    /**
     * 添加预约订单
     *
     * @param jdbcTemplate
     * @param parkingOrder
     * @return
     */
    public static int AddParkingOrder(JdbcTemplate jdbcTemplate, ParkingOrder parkingOrder) {
        String sql = "insert into parkingorder values(?,?,?,?,?,?,?,?)";
        Object[] args = {parkingOrder.getOpenid(),parkingOrder.getParkingname(), parkingOrder.getPrice(), parkingOrder.getParkingspace(), parkingOrder.getTime(), parkingOrder.getDuration(), parkingOrder.getTotalprice(), "false"};
        int add = jdbcTemplate.update(sql, args);
        return add;
    }

    /**
     * 返回全部订单
     *
     * @param jdbcTemplate
     * @return
     */
    public static List<ParkingOrder> GetAllParkingOrder(JdbcTemplate jdbcTemplate) {
        String sql = "select * from parkingorder ORDER BY time desc ";
        List<ParkingOrder> parkingOrderList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ParkingOrder>(ParkingOrder.class));
        return parkingOrderList;
    }

    /**
     * 返回指定用户的订单
     *
     * @param jdbcTemplate
     * @return
     */
    public static List<ParkingOrder> GetAllParkingOrderByOpenis(JdbcTemplate jdbcTemplate,String openid) {
        String sql = "select * from parkingorder where openid=? ORDER BY time desc ";
        List<ParkingOrder> parkingOrderList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ParkingOrder>(ParkingOrder.class),openid);
        return parkingOrderList;
    }

    /**
     * 检查订单是否完成，完成的需要将车位改为空闲状态并且订单为完成状态
     *
     * @param jdbcTemplate
     */
    public static void OrderCompletion(JdbcTemplate jdbcTemplate) {
        String sql = "select * from parkingorder where complete='false'";
        List<ParkingOrder> parkingOrderList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ParkingOrder>(ParkingOrder.class));
        for (ParkingOrder p : parkingOrderList) {
            boolean result = TimeComparisonImpl.TimeComparison(p.getTime(), p.getDuration());
            if (result) {
                boolean Updataorder=Updataorder(jdbcTemplate,p);
            }
        }

    }

    /**
     * 根据订单将某车位恢复空闲状态
     *
     * @param jdbcTemplate
     * @return
     */
    public static boolean EmptyParkingSpace(JdbcTemplate jdbcTemplate, ParkingOrder parkingOrder) {
        String sql = "update parkingcar set havacar='false',baiche1='false',baiche2='false',baiche3='false',hongche='false' ,huangche='false' where parkingname=? and classes=?";
        String classes = "chewei" + parkingOrder.getParkingspace().replace("-", "");
        Object[] args = {parkingOrder.getParkingname(), classes};
        int update = jdbcTemplate.update(sql, args);
        if (update == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 修改订单为完成状态
     *
     * @param jdbcTemplate
     * @return
     */
    public static boolean Updataorder(JdbcTemplate jdbcTemplate, ParkingOrder parkingOrder) {
        String sql = "update parkingorder set complete='true' where parkingname=? and parkingspace=?";
        Object[] args = {parkingOrder.getParkingname(), parkingOrder.getParkingspace()};
        int update = jdbcTemplate.update(sql, args);
        if (update == 1) {
            //订单完成才可以将车位恢复空闲状态
            boolean EmptyParkingSpace=EmptyParkingSpace(jdbcTemplate, parkingOrder);
            return EmptyParkingSpace;
        } else {
            return false;
        }
    }
}
