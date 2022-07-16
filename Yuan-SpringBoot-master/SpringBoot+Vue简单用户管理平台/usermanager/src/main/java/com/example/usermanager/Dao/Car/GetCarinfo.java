package com.example.usermanager.Dao.Car;

import com.example.usermanager.bean.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author 黎荣右
 * @date 2022/6/1 17:38
 */
public class GetCarinfo {
    /**
     * 车辆信息列表
     *
     * @param jdbcTemplate
     * @return 列表
     */
    public static List<User> getcarlist(JdbcTemplate jdbcTemplate) {
        //数据库操作
        String sql = "select * from user ";//SQL查询语句
        //查询
        List<User> carlists = jdbcTemplate.query(sql, (resultSet, i) -> {
            User carlist = new User(
                    resultSet.getString("username"), resultSet.getString("phone"),
                    resultSet.getString("carid"), resultSet.getString("carcolor"),
                    resultSet.getString("allowpassage")
            );
            return carlist;
        });
        return carlists;
    }


    /**
     * 获取指定条件的车辆信息
     * @param jdbcTemplate
     * @return 车辆信息列表
     */
    public static List<User> findcarlist(JdbcTemplate jdbcTemplate, User user) {
        //数据库操作
        String sql = "select * from user where username LIKE ? and  phone LIKE ? and carid LIKE ? and carcolor LIKE ? and allowpassage like ?;";//SQL查询语句
        String username, phone, carid, carcolor, allowpassage;

        if (user.getUsername() == "") {
            username = "%";
        } else {
            username = "%" + user.getUsername() + "%";
        }
        if (user.getPhone() == "") {
            phone = "%";
        } else {
            phone = "%" + user.getPhone() + "%";
        }
        if (user.getCarid() == "") {
            carid = "%";
        } else {
            carid = "%" + user.getCarid() + "%";
        }
        if (user.getCarcolor() == "") {
            carcolor = "%";
        } else {
            carcolor = "%" + user.getCarcolor() + "%";
        }
        if (user.getAllowpassage() == "") {
            allowpassage = "%";
        } else {
            allowpassage = user.getAllowpassage() ;
        }
        //查询
        List<User> UserList = jdbcTemplate.query(sql, (resultSet, i) -> {
            User carlist = new User(
                    resultSet.getString("username"), resultSet.getString("phone"),
                    resultSet.getString("carid"), resultSet.getString("carcolor"),
                    resultSet.getString("allowpassage")
            );
            return carlist;
        }, username, phone, carid, carcolor, allowpassage);

        return UserList;
    }

    /**
     * 获取指定车牌号的车辆信息
     * @param jdbcTemplate
     * @return 车辆信息列表
     */
    public static User findidcarlist(JdbcTemplate jdbcTemplate, String carid) {
        //数据库操作
        String sql = "select * from user where carid=?;";//SQL查询语句
        //查询
        User user = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),carid);//rowmapper帮我们封装，是个接口，返回不同数据类型，使用这个接口里面实现类完成数据封装装

        return user;
    }

    /**
     * 修改车辆是否允许进入权限
     * @param jdbcTemplate
     * @param carid
     * @return
     */
    public static boolean updateallowpassage(JdbcTemplate jdbcTemplate, String carid) {
        //获得指定车牌号的车辆信息
        User user=findidcarlist(jdbcTemplate,carid);

        //数据库操作
        String sql = "update user set allowpassage=? where carid=?;";

        String allowpassage;
        if (user.getAllowpassage() .equals("允许") ) {
            allowpassage = "不允许";
        } else {
            allowpassage = "允许";
        }
        Object[] args = {allowpassage, user.getCarid()};
        int update = jdbcTemplate.update(sql, args);

        if (update > 0) {
            return true;
        } else {
            return false;
        }
    }
}
