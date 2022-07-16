package com.example.usermanager.controller.Manage;

import com.example.usermanager.Dao.Login.LoginDao;
import com.example.usermanager.Dao.Login.LogininfoDao;
import com.example.usermanager.Dao.Manege.AddManage;
import com.example.usermanager.Dao.Manege.DeleteManage;
import com.example.usermanager.Dao.Manege.ManegeListDao;
import com.example.usermanager.Dao.Manege.UpdateManage;
import com.example.usermanager.bean.Logininfo;
import com.example.usermanager.bean.ManegeList;
import com.example.usermanager.bean.Test;
import com.example.usermanager.bean.User;
import com.example.usermanager.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 黎荣右
 * @date 2022/5/22 22:10
 */
@RestController
@RequestMapping("/ManageServlet")
public class ManageListController {
    @Autowired
    private JdbcTemplate jdbcTemplate;//使用注解自动创建对象

    @PostMapping("/namagelist")
    @ResponseBody
    public List<ManegeList> getnamagelist() {
        return ManegeListDao.getmanegelist(jdbcTemplate);
    }

    @PostMapping("/pagenamagelist")
    @ResponseBody
    public List<ManegeList> getpagenamagelist(@RequestBody String id) {
        int i = id.length();
        int page = Integer.parseInt(id.substring(0, i - 1));
        return ManegeListDao.getpagemanegelist(jdbcTemplate, page);
    }

    @PostMapping("/deletenamage")
    @ResponseBody
    public boolean deletenamage(@RequestBody String id) {
        int i = id.length();
        int xuhao = Integer.parseInt(id.substring(0, i - 1));
        return DeleteManage.deletemanege(jdbcTemplate, xuhao);
    }

    @PostMapping("/deletemorenamage")
    @ResponseBody
    public boolean deletemorenamage(@RequestBody List<String> id) {
        int i = 0;
        for (String xuhao : id) {
            if (DeleteManage.deletemanege(jdbcTemplate, Integer.parseInt(xuhao))) {
                i++;
            }
        }
        if (i == id.size()) {
            return true;
        } else {
            return false;
        }
    }

    @PostMapping("/findnamage")
    @ResponseBody
    public List<ManegeList> findnamage(@RequestBody ManegeList manegeList) {
        System.out.println(manegeList.toString());
        return ManegeListDao.findmanegelist(jdbcTemplate, manegeList);
    }

    @GetMapping("{id}")
    public ManegeList findidnamage(@PathVariable(value = "id") String id) {
        return ManegeListDao.findidmanegelist(jdbcTemplate, Integer.parseInt(id));
    }

    @PostMapping("/addnamage")
    @ResponseBody
    public boolean addnamage(@RequestBody ManegeList manegeList) {
        return AddManage.addemanege(jdbcTemplate, manegeList);
    }

    @PutMapping("")
    public boolean updatenamage(@RequestBody ManegeList manegeList) {
        return UpdateManage.updatemanege(jdbcTemplate,manegeList);
    }

}
