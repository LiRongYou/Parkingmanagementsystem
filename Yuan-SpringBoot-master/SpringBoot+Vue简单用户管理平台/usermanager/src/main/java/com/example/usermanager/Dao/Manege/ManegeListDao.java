package com.example.usermanager.Dao.Manege;

import com.example.usermanager.bean.ManegeList;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author 黎荣右
 * @date 2022/5/22 21:54
 */
public class ManegeListDao {

    /**
     * 获取全部的管理员信息
     * @param jdbcTemplate
     * @return 管理员信息列表
     */
    public static List<ManegeList> getmanegelist(JdbcTemplate jdbcTemplate){
        //数据库操作
        String sql="select * from login";//SQL查询语句
        //查询
        List<ManegeList> ManegeList = jdbcTemplate.query(sql, (resultSet, i) -> {
            ManegeList manegeList = new ManegeList(resultSet.getInt(1),
                    resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getString(5),
                    resultSet.getString(6),resultSet.getString(7)
            );
            return manegeList;
        });

        return ManegeList;
    }

    /**
     * 获取当前页的管理员信息
     * @param jdbcTemplate
     * @return 管理员信息列表
     */
    public static List<ManegeList> getpagemanegelist(JdbcTemplate jdbcTemplate,int page){
        int start=page*9-9;
        int end=9;//每页7行
        //数据库操作
        //,limit有两个参数，第一个参数表示从第几行数据开始查，第二个参数表示查几条数据，“limit 3,2”表示从第四行数据开始，取两条数据。
        String sql="select * from login LIMIT ?,?;";//SQL查询语句
        //查询
        List<ManegeList> ManegeList = jdbcTemplate.query(sql, (resultSet, i) -> {
            ManegeList manegeList = new ManegeList(resultSet.getInt(1),
                    resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getString(5),
                    resultSet.getString(6),resultSet.getString(7)
            );
            return manegeList;
        },start,end);

        return ManegeList;
    }

    /**
     * 获取指定条件的管理员信息
     * @param jdbcTemplate
     * @return 管理员信息列表
     */
    public static List<ManegeList> findmanegelist(JdbcTemplate jdbcTemplate,ManegeList manegeList){
        //数据库操作
        String sql="select * from login where username LIKE ? and idnumber LIKE ? and phone LIKE ? and sex LIKE ? and create_time like ?;";//SQL查询语句
       String username,idnumber,phone,sex,createtime;

        if(manegeList.getUsername()==""){
            username="%";
        }else {
            username="%"+manegeList.getUsername()+"%";
        }
        if(manegeList.getIdnumber()==""){
            idnumber="%";
        }else {
            idnumber="%"+manegeList.getIdnumber()+"%";
        }
        if(manegeList.getPhone()==""){
            phone="%";
        }else {
            phone="%"+manegeList.getPhone()+"%";
        }
        if(manegeList.getSex()==""){
            sex="%";
        }else {
            sex="%"+manegeList.getSex()+"%";
        }
        if(manegeList.getCreatetime()==""){
            createtime="%";
         }else {
            createtime="%"+manegeList.getCreatetime()+"%";
        }
        //查询
        List<ManegeList> ManegeList = jdbcTemplate.query(sql, (resultSet, i) -> {
            ManegeList manegeList1 = new ManegeList(resultSet.getInt(1),
                    resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getString(5),
                    resultSet.getString(6),resultSet.getString(7)
            );
            return manegeList1;
        },username,idnumber,phone,sex,createtime);

        return ManegeList;
    }

    /**
     * 获取指定序号的管理员信息
     * @param jdbcTemplate
     * @return 管理员信息列表
     */
    public static ManegeList findidmanegelist(JdbcTemplate jdbcTemplate,int id){

        //数据库操作
        String sql="select * from login where id=?;";//SQL查询语句
        //查询
        ManegeList manegeList = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<ManegeList>(ManegeList.class),id);//rowmapper帮我们封装，是个接口，返回不同数据类型，使用这个接口里面实现类完成数据封装装

        return manegeList;
    }
}
