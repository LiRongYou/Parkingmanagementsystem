package com.example.usermanager.Dao.Manege;

import com.example.usermanager.bean.ManegeList;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 黎荣右
 * @date 2022/5/23 13:02
 */
public class AddManage {

    /**
     * 添加管理员
     * @param jdbcTemplate
     * @param manegeList
     * @return
     */
    public static boolean addemanege(JdbcTemplate jdbcTemplate, ManegeList manegeList) {
        String sql =  "insert into login(username,idnumber,password,phone,sex,create_time) values(?,?,?,?,?,?)";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String cteatetime=df.format(new Date());
        Object[] args = {manegeList.getUsername(),manegeList.getIdnumber(),manegeList.getPassword(),manegeList.getPhone(),manegeList.getSex(),cteatetime};
        int update =  jdbcTemplate.update(sql,args);

        if (update > 0) {
            return true;
        } else {
            return false;
        }
    }
}
