package com.example.usermanager.controller.WXpay;


import com.example.usermanager.util.MD5Util;
import com.example.usermanager.wxpaysdk.WXPayUtil;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author 黎荣右
 * 获取微信服务器返回的结果
 */
public class getPayResult {

//    向微信端发送查询订单请求,并获取成功是否的代码
    public static Map<String, String> getPayResultCode(String orderNo) {
        Map<String, String> resultSet=null;
        try {
            MyConfig myConfig=new MyConfig();
            com.example.usermanager.wxpaysdk.WXPay wxPay=new com.example.usermanager.wxpaysdk.WXPay(myConfig);
            SortedMap<String, String> params = new TreeMap<String, String>();
            //公众账号ID
            params.put("appid", myConfig.getAppID());
            //商户号
            params.put("mch_id", myConfig.getMchID());
            //随机字符串，不能大于32位
            params.put("nonce_str", WXPayUtil.generateNonceStr());
            //支付单号 二选一
            //params.put("transaction_id", transaction_id);
            //商户订单号  二选一
            params.put("out_trade_no", orderNo);
            //生成签名
            String sign = MD5Util.createSignMD5(params,myConfig.getKey());

            params.put("sign", sign);

            //生成xml结构的数据，用于统一下单请求的xml请求数据
//            String requestXML = WXPayUtil.mapToXml(params);

            resultSet=wxPay.orderQuery(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
