package com.example.usermanager.controller.Car;

import com.example.usermanager.Dao.Car.GetCarinfo;
import com.example.usermanager.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 黎荣右
 * @date 2022/6/1 17:48
 */
@RestController
@RequestMapping("/CarServlet")
public class CarController {
    @Autowired
    private JdbcTemplate jdbcTemplate;//使用注解自动创建对象

    @PostMapping("/carlist")
    @ResponseBody
    public List<User> getparkingcarlist() {
        return GetCarinfo.getcarlist(jdbcTemplate);
    }

    @GetMapping("{carid}")
    public boolean updateallowpassage(@PathVariable(value = "carid") String carid) {
        return GetCarinfo.updateallowpassage(jdbcTemplate,carid);
    }

    @PostMapping("/findparkingcarlist")
    @ResponseBody
    public List<User> findparkingcarlist(@RequestBody User user) {
        return GetCarinfo.findcarlist(jdbcTemplate, user);
    }
}
