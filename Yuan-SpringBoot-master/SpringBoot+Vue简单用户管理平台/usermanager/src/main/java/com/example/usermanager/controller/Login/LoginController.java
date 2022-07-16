package com.example.usermanager.controller.Login;


import com.example.usermanager.Dao.Login.LoginDao;
import com.example.usermanager.bean.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author 黎荣右
 * @date 2022/5/19 21:46
 */
@RestController
@RequestMapping("/LoginServlet")
public class LoginController {
    @Autowired
    private JdbcTemplate jdbcTemplate;//使用注解自动创建对象

    @PostMapping("/login")
    @ResponseBody
    public boolean login(@RequestBody Login login){
        String username=login.getUsername();
        String password=login.getPassword();
        return LoginDao.chencklogininfo(jdbcTemplate, username, password);
    }

    @PostMapping("/loginsuername")
    public String loginusername(){
        return LoginDao.getloginusername();
    }

    @PostMapping("/getuserlist")
    @ResponseBody
    public List<Login> getuserlist(){
        return LoginDao.getuserlist(jdbcTemplate);
    }

    @PostMapping("/register")
    @ResponseBody
    public boolean register(@RequestBody Login login){
        return LoginDao.register(jdbcTemplate,login);
    }
}
