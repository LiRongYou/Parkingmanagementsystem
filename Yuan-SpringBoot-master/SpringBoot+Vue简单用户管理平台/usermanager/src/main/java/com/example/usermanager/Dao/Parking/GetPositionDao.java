package com.example.usermanager.Dao.Parking;

import com.example.usermanager.bean.ParkingPosition;
import com.example.usermanager.bean.ParkingcarList;
import com.example.usermanager.bean.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author 黎荣右
 * @date 2022/6/2 12:49
 */
public class GetPositionDao {
    /**
     * 按停车场名字获取停车场位置
     * @param jdbcTemplate
     * @return
     */
    public static ParkingPosition getparkingposition(JdbcTemplate jdbcTemplate, String parkingname){
        //数据库操作
        String sql="select * from parkingposition where parkingname=?";//SQL查询语句
        //查询
        ParkingPosition parkingPosition = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<ParkingPosition>(ParkingPosition.class),parkingname);//rowmapper帮我们封装，是个接口，返回不同数据类型，使用这个接口里面实现类完成数据封装装

        return parkingPosition;
    }
}
