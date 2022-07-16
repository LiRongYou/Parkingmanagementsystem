package com.example.usermanager.Dao.Login;

import com.example.usermanager.bean.Login;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 黎荣右
 * @date 2022/5/20 10:55
 */
public class LoginDao {

    public static String loginusername;
    /**
     * 检查账号密码是否正确
     * @param jdbcTemplate
     * @param username
     * @param password
     * @return 是否正确
     */
    public static boolean chencklogininfo(JdbcTemplate jdbcTemplate, String username, String password){
        loginusername=username;
        //数据库操作
        String sql="select * from login where username=?and password=?";//SQL查询语句
        //查询
        List<Login> Login = jdbcTemplate.query(sql, (resultSet, i) -> {
            String name = resultSet.getString("username");
            String psw = resultSet.getString("password");
            com.example.usermanager.bean.Login user = new com.example.usermanager.bean.Login(name, psw);
            return user;
        },username,password);

        //记录登录记录
        //数据库操作
        String sql1="insert into logininfo(日期,用户,登录地址) values(?,?,?)";//SQL查询语句
        //插入
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String date=df.format(new Date());
        String address ="广西桂林";
        Object[] args = {date,username,address};
        jdbcTemplate.update(sql1,args);

        if(Login.size()>0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 获取登录者信息
     * @return
     */
    public static String getloginusername(){
        return loginusername;
    }

    /**
     * 获取全部登录者信息
     * @param jdbcTemplate
     * @return 登录者信息列表
     */
    public static List<Login> getuserlist(JdbcTemplate jdbcTemplate){
        //数据库操作
        String sql="select * from login";//SQL查询语句
        //查询
        List<Login> registerlist = jdbcTemplate.query(sql, (resultSet, i) -> {
            String name = resultSet.getString("username");
            String psw = resultSet.getString("password");
            com.example.usermanager.bean.Login user = new com.example.usermanager.bean.Login(name, psw);
            return user;
        });

        return registerlist;
    }

    /**
     * 注册账号
     * @param jdbcTemplate
     * @param login
     * @return 是否成功
     */
    public static boolean register(JdbcTemplate jdbcTemplate,Login login){
        //数据库操作
        String sql="insert into login(username,password) values(?,?)";//SQL查询语句
        //插入
        Object[] args = {login.getUsername(),login.getPassword()};
        int update =  jdbcTemplate.update(sql,args);

        if(update>0){
            return true;
        }else {
            return false;
        }
    }
}
