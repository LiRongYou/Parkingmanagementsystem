package com.example.usermanager.controller.ScanningCode;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.example.usermanager.controller.WXpay.WXPay.scanCodeToPay;

/**
 * @author 黎荣右
 * @date 2022/7/1 11:11
 */
@Controller
@RequestMapping("/ScanningCodeService")
public class ScanningCodeController {
    //获取扫码得到的信息
    @RequestMapping("/ScanningCode")
    @ResponseBody
    public void test(@RequestBody String ScanningCode) {
        try {
            boolean flag = scanCodeToPay(ScanningCode, "111111", "扫码支付测试", 1);
           if (flag == true) {
                System.out.println("扫码支付成功!");
            }else {
                System.out.println("扫码支付失败!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("微信扫一扫的结果" + ScanningCode);
    }

    //获取openid
    @GetMapping("/getopenid")
    @ResponseBody//需要加上@ResponseBody注解，否则GET请求获取不到返回体
    public String getUserInfo(@RequestParam(name = "code") String code) throws Exception {
        System.out.println("code" + code);
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        url += "?appid=wxeadec906a75a4c92";//自己的appid
        url += "&secret=8a1a432dff84df6eea7eee2a7c75ba7a";//自己的appSecret
        url += "&js_code=" + code;
        url += "&grant_type=authorization_code";
        url += "&connect_redirect=1";
        String res = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);    //GET方式
        CloseableHttpResponse response = null;
        // 配置信息
        RequestConfig requestConfig = RequestConfig.custom()          // 设置连接超时时间(单位毫秒)
                .setConnectTimeout(5000)                    // 设置请求超时时间(单位毫秒)
                .setConnectionRequestTimeout(5000)             // socket读写超时时间(单位毫秒)
                .setSocketTimeout(5000)                    // 设置是否允许重定向(默认为true)
                .setRedirectsEnabled(false).build();           // 将上面的配置信息 运用到这个Get请求里
        httpget.setConfig(requestConfig);                         // 由客户端执行(发送)Get请求
        response = httpClient.execute(httpget);                   // 从响应模型中获取响应实体
        HttpEntity responseEntity = response.getEntity();
        System.out.println("响应状态为:" + response.getStatusLine());
        if (responseEntity != null) {
            res = EntityUtils.toString(responseEntity);
            System.out.println("响应内容长度为:" + responseEntity.getContentLength());
            System.out.println("响应内容为:" + res);
        }
        // 释放资源
        if (httpClient != null) {
            httpClient.close();
        }
        if (response != null) {
            response.close();
        }
        JSONObject jo = JSON.parseObject(res);
        String openid = jo.getString("openid");
        System.out.println("openid: " + openid);
        return openid;
    }

    public static void main(String[] args) {
        //nonceStr随机字符串
//        String nonce_str = WXPayUtil.generateNonceStr();
//        System.out.println(nonce_str);

        //扫码支付测试
//        try {
//          boolean flag = scanCodeToPay("130051112021467093", "111111", "扫码支付测试", 1);
//            if (flag == true) {
//                System.out.println("扫码支付成功!");
//            }else {
//                System.out.println("扫码支付失败!");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }

}


