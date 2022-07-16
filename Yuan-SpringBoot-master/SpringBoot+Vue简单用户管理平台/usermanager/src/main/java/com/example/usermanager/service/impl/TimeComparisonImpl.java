package com.example.usermanager.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 黎荣右
 * @date 2022/7/15 17:20
 */
public class TimeComparisonImpl {
    public static boolean TimeComparison(String ordertime, String duration){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //获取当前时间
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currenttime=sdf.format(System.currentTimeMillis());

        try {
            Date d1 = df.parse(ordertime);
            Date d2 = df.parse(currenttime);
            long diff = d2.getTime() - d1.getTime();
            long durationstamp= Long.parseLong(duration.replace("小时",""))*1000*(60*60);
            if(diff>durationstamp){
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
