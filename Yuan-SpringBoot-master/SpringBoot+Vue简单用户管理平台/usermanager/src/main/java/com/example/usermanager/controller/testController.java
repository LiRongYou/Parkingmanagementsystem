package com.example.usermanager.controller;

import com.example.usermanager.bean.Test;
import org.springframework.web.bind.annotation.*;

/**
 * @author 黎荣右
 * @date 2022/5/18 16:01
 */
@RestController
@RequestMapping("/TestApiServlet")
public class testController {

    @PostMapping("/list")
    @ResponseBody
    public Test getlist(@RequestBody Test test){
        System.out.println("访问了");
        System.out.println(test.getName()+test.getAge());
        Test test1=new Test("黎荣右","20");

        return test1;
    }



}
