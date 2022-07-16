package com.example.usermanager.bean;

/**
 * @author 黎荣右
 * @date 2022/5/22 21:55
 */
public class ManegeList {
    private int id;
    private String username,idnumber,password,phone,sex,createtime;

    public ManegeList() {
    }

    public ManegeList(int id, String username, String idnumber, String password, String phone,String sex,  String createtime) {
        this.id = id;
        this.sex = sex;
        this.username = username;
        this.idnumber = idnumber;
        this.password = password;
        this.phone = phone;
        this.createtime = createtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "ManegeList{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", idnumber='" + idnumber + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", sex='" + sex + '\'' +
                ", createtime='" + createtime + '\'' +
                '}';
    }
}
