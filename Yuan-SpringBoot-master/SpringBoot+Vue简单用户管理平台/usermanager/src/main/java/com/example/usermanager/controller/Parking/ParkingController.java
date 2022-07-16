package com.example.usermanager.controller.Parking;

import com.example.usermanager.Dao.Parking.GetParkingcarListDao;
import com.example.usermanager.bean.ArryList;
import com.example.usermanager.bean.ParkingOrder;
import com.example.usermanager.bean.ParkingcarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.usermanager.controller.WXpay.WXPay.unifiedOrder;

/**
 * @author 黎荣右
 * @date 2022/6/1 12:04
 */
@RestController
@RequestMapping("/ParkingServlet")
public class ParkingController {

    @Autowired
    private JdbcTemplate jdbcTemplate;//使用注解自动创建对象

    @PostMapping("/parkingcarlist")
    @ResponseBody
    public List<ParkingcarList> getparkingcarlist(@RequestBody ParkingcarList parkingcarList) {
          GetParkingcarListDao.OrderCompletion(jdbcTemplate);
        return GetParkingcarListDao.getparkingcarlist(jdbcTemplate,parkingcarList.getParkingname() );
    }

    /**
     *微信小程序返回停车车位情况
     * @param parkingname
     * @return
     */
    @RequestMapping("/getparkinglot")
    @ResponseBody
    public ArryList getparkinglot(@RequestBody String parkingname) {
        GetParkingcarListDao.OrderCompletion(jdbcTemplate);
        return GetParkingcarListDao.getparkinglot(jdbcTemplate,parkingname);

    }

    /**
     * 微信小程序获取预约的车位
     * @param parkingcarList
     */
    @RequestMapping("/parking")
    @ResponseBody
    public boolean getparking(@RequestBody ParkingcarList parkingcarList) {
        GetParkingcarListDao.OrderCompletion(jdbcTemplate);
        String classes=parkingcarList.getClasses().replace("-","");
        parkingcarList.setClasses("chewei"+classes);
        if(GetParkingcarListDao.UpdateParkingLot(jdbcTemplate,parkingcarList)==1){
            return true;
        }else {
            return false;
        }
    }
    @RequestMapping("/parkingorder")
    @ResponseBody
    public boolean AddReservationOrder(@RequestBody ParkingOrder parkingOrder){
//        提交订单 调用微信付款
        if(parkingOrder.getParkingspace().equals("")||parkingOrder.getTime().equals("")||parkingOrder.getDuration().equals("")){
            return false;
        }else {
            // 生成二维码，完成支付
            unifiedOrder("1111111", parkingOrder.getParkingname()+parkingOrder.getParkingspace(), Integer.parseInt(parkingOrder.getTotalprice()));

            parkingOrder.setDuration(parkingOrder.getDuration()+"小时");
            if(GetParkingcarListDao.AddParkingOrder(jdbcTemplate,parkingOrder)==1){
                return true;
            }else {
                return false;
            }
        }
    }

    @RequestMapping("/getallparkingorder")
    public List<ParkingOrder> GetAllParkingOrder(){
        GetParkingcarListDao.OrderCompletion(jdbcTemplate);
        List<ParkingOrder> parkingOrderList=GetParkingcarListDao.GetAllParkingOrder(jdbcTemplate);
        return parkingOrderList;
    }

    @RequestMapping("/getallparkingorderbyopenid")
    @ResponseBody
    public List<ParkingOrder> GetAllParkingOrderByopenid(@RequestBody String openid){
        GetParkingcarListDao.OrderCompletion(jdbcTemplate);
        List<ParkingOrder> parkingOrderList=GetParkingcarListDao.GetAllParkingOrderByOpenis(jdbcTemplate,openid);
        return parkingOrderList;
    }
}
