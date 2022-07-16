package com.example.usermanager.Dao.Login;


import com.example.usermanager.bean.Logininfo;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author 黎荣右
 * @date 2022/5/21 11:57
 */

public class LogininfoDao {
    /**
     * 获取登录者历史记录
     * @param jdbcTemplate
     * @return
     */
    public static List<Logininfo> getlogininfo(JdbcTemplate jdbcTemplate,String username){
        String sql = "select 日期,用户,登录地址 from logininfo where 用户=? order by 序号 desc limit 5";
        List<Logininfo> logininfoList = jdbcTemplate.query(sql, (resultSet, i) -> {
            String date = resultSet.getString("日期");
            String name = resultSet.getString("用户");
            String address = resultSet.getString("登录地址");
            Logininfo logininfo = new Logininfo(date,name, address);
            return logininfo;
        },username);


        return logininfoList;
    }
    }
