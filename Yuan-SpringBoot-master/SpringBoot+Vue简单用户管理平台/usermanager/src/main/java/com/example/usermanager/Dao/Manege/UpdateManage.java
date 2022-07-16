package com.example.usermanager.Dao.Manege;

import com.example.usermanager.bean.ManegeList;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author 黎荣右
 * @date 2022/5/23 17:06
 */
public class UpdateManage {
    /**
     * 编辑管理员
     * @param jdbcTemplate
     * @param manegeList
     * @return
     */
    public static boolean updatemanege(JdbcTemplate jdbcTemplate, ManegeList manegeList) {
        //数据库操作
        String sql = "update login set username=?,idnumber=? ,password=? ,phone=?,sex=? where id=?";
        Object[] args = {manegeList.getUsername(),manegeList.getIdnumber(),manegeList.getPassword(),manegeList.getPhone(),manegeList.getSex(),manegeList.getId()};
        int update = jdbcTemplate.update(sql,args);

        if (update > 0) {
            return true;
        } else {
            return false;
        }
    }
}
