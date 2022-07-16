package com.example.usermanager.Dao.Manege;

import org.springframework.jdbc.core.JdbcTemplate;


/**
 * @author 黎荣右
 * @date 2022/5/22 22:41
 */
public class DeleteManage {

    /**
     * 按照id删除管理员
     * @param jdbcTemplate
     * @param id
     * @return
     */
    public static boolean deletemanege(JdbcTemplate jdbcTemplate, int id) {
        //数据库操作
        String sql = "delete from login where id = ?";//SQL删除语句
        //删除
        Object[] args = {id};
        int update = jdbcTemplate.update(sql, args);
        if (update > 0) {
            return true;
        } else {
            return false;
        }
    }
}
