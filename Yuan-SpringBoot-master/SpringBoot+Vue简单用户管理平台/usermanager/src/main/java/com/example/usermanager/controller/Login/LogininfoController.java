package com.example.usermanager.controller.Login;

import com.example.usermanager.Dao.Login.LoginDao;
import com.example.usermanager.Dao.Login.LogininfoDao;
import com.example.usermanager.bean.Logininfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 黎荣右
 * @date 2022/5/21 12:03
 */
@RestController
@RequestMapping("/LogininfoServlet")
public class LogininfoController {
    @Autowired
    private JdbcTemplate jdbcTemplate;//使用注解自动创建对象

    @PostMapping("/logininfolist")
    @ResponseBody
    public List<Logininfo> getlogininfo(){
        return LogininfoDao.getlogininfo(jdbcTemplate, LoginDao.loginusername);
    }
}
