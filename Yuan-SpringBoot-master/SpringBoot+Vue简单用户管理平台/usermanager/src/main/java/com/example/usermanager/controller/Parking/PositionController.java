package com.example.usermanager.controller.Parking;

import com.example.usermanager.Dao.Parking.GetParkingcarListDao;
import com.example.usermanager.Dao.Parking.GetPositionDao;
import com.example.usermanager.bean.ParkingPosition;
import com.example.usermanager.bean.ParkingcarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;


/**
 * @author 黎荣右
 * @date 2022/6/2 12:47
 */
@RestController
@RequestMapping("/PositionServlet")
public class PositionController {
    @Autowired
    private JdbcTemplate jdbcTemplate;//使用注解自动创建对象

    @GetMapping("{parkingname}")
    @ResponseBody
    public ParkingPosition getparkingcarlist(@PathVariable(value = "parkingname") String parkingname) {
        return GetPositionDao.getparkingposition(jdbcTemplate,parkingname );
    }
}
