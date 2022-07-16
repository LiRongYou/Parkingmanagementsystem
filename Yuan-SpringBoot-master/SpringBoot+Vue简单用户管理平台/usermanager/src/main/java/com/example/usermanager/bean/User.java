package com.example.usermanager.bean;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * FileName:    User
 * Author:      小袁
 * Date:        2022/5/6 13:23
 * Description: 用户表
 */
@Data
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    private String nickname;

    private String password;

    private String phone;

    private String sex;

    @TableField(value = "`describe`")
    private String describe;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    private String carid,carcolor,allowpassage;

    public User() {
    }

    public User(Integer id, String username, String nickname, String password, String phone, String sex, String describe, Date createTime, String carid, String carcolor, String allowpassage) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.phone = phone;
        this.sex = sex;
        this.describe = describe;
        this.createTime = createTime;
        this.carid = carid;
        this.carcolor = carcolor;
        this.allowpassage = allowpassage;
    }

    public User(String username, String phone, String carid, String carcolor, String allowpassage) {
        this.username = username;
        this.phone = phone;
        this.carid = carid;
        this.carcolor = carcolor;
        this.allowpassage = allowpassage;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCarid() {
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    public String getCarcolor() {
        return carcolor;
    }

    public void setCarcolor(String carcolor) {
        this.carcolor = carcolor;
    }

    public String getAllowpassage() {
        return allowpassage;
    }

    public void setAllowpassage(String allowpassage) {
        this.allowpassage = allowpassage;
    }
}
