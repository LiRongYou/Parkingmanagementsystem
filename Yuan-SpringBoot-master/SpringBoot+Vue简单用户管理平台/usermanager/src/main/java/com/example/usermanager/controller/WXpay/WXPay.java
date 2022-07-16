package com.example.usermanager.controller.WXpay;


import com.example.usermanager.util.DateUtil;
import com.example.usermanager.wxpaysdk.WXPayUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

import static com.example.usermanager.controller.WXpay.getPayResult.getPayResultCode;


/**
 * @author 黎荣右
 */
public class WXPay {

    private static Log log = LogFactory.getLog(WXPay.class);

    private static final String PAY_SUCCESS = "SUCCESS";
    private static final String PAY_USERPAYING = "USERPAYING";


    /**
     * 扫码支付
     *
     * @throws Exception
     */
    public static boolean scanCodeToPay(String auth_code,String attach,String body,int totalPrice) throws Exception {
        InetAddress addr = null;
        boolean returnFlag=false;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String spbill_create_ip = addr.getHostAddress();

        MyConfig config = new MyConfig();
        com.example.usermanager.wxpaysdk.WXPay wxpay = new com.example.usermanager.wxpaysdk.WXPay(config);
        String out_trade_no = DateUtil.getCurrentTime();//获取当前时间戳 用作订单号
        Map<String, String> map = new HashMap<>(16);
        map.put("attach", attach);
        map.put("auth_code", auth_code);
        map.put("body", body);//商品描述
        map.put("device_info", "Taran美妆");//描述
        map.put("nonce_str", WXPayUtil.generateNonceStr());//随机字符串
//        map.put("out_trade_no", attach.split(";")[0].split(",")[1]);//商户订单号
        map.put("out_trade_no", out_trade_no);//商户订单号
        map.put("spbill_create_ip", spbill_create_ip);//IP
        map.put("total_fee", String.valueOf(totalPrice));//总金额（分）
        //生成签名
        String sign = WXPayUtil.generateSignature(map, config.getKey());
        map.put("sign", sign);
        String mapToXml = null;
        try {
            //调用微信的扫码支付接口
            Map<String, String> resp = wxpay.microPay(map);
            System.out.println("扫码支付结果：" + resp);
            mapToXml = WXPayUtil.mapToXml(resp);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("微信支付失败" + e);
        }
        boolean payflag=false;
        while(payflag==false){
            Map<String, String> resultSet=getPayResultCode(out_trade_no);//检查订单号是否支付成功
            String resultCode=resultSet.get("trade_state");
            System.out.println(resultCode);
            if(resultCode.equals(PAY_SUCCESS)){
                System.out.println("支付成功");
                payflag=true;
                returnFlag=true;
            }else if(resultCode.equals(PAY_USERPAYING)){
                System.out.println("用户正在支付,请稍后----------------------------------");
            } else{
                System.out.println("用户已取消支付------------------------------------");
                payflag=true;
            }
        }
        return returnFlag;
    }

    /*
    下单：生成二维码
     */
    public static void unifiedOrder(String attach,String body,int total_fee) {
        Map<String, String> resultMap = new HashMap();
//        String openid = "ouR0E1oP5UGTEBce8jZ_sChfH26g";
        MyConfig config = null;
        com.example.usermanager.wxpaysdk.WXPay wxpay = null;
        try {
            config = new MyConfig();
            wxpay = new com.example.usermanager.wxpaysdk.WXPay(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //生成的随机字符串
        String nonce_str = WXPayUtil.generateNonceStr();
        //获取客户端的ip地址
        //获取本机的ip地址
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String spbill_create_ip = addr.getHostAddress();
        //支付金额，需要转成字符串类型，否则后面的签名会失败
        //统一下单接口参数
        SortedMap<String, String> data = new TreeMap<String, String>();
        data.put("appid", "wxd9a46e74fc279fcc");//小程序appid
        data.put("attach",attach);
//        可填写商品名称
        data.put("body", body);
        data.put("mch_id", "1623889015");//商户号
        // 回调接口，必须是一个域名，不能使用IP
        // 腾讯会自动调用你（程序自己提供的接口）的接口，给你发送支付结果的数据，数据格式：xml格式
        data.put("notify_url", "http://1459-221-7-210-196.ngrok.io/result");
//        商家订单号和相关商品信息在attach中传输

//        String order[] = attach.split(";")[0].split(",");
//        String order_No = order[1];
        String order_No = DateUtil.getCurrentTime();//获取当前时间戳 用作订单号

        data.put("out_trade_no", order_No);//交易号
        data.put("spbill_create_ip", spbill_create_ip);//下单的电脑IP地址
        data.put("trade_type", "NATIVE");//支付类型
//        商品金额
        data.put("total_fee", String.valueOf(total_fee));
//        data.put("openid", openid);

        try {
            Map<String, String> rMap = wxpay.unifiedOrder(data);
            System.out.println("统一下单接口返回: " + rMap);
            createQRCode(rMap);//生成二维码
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void createQRCode(Map<String, String> map) throws Exception {
        File outputFile = new File("C:\\Users\\ASUS\\IdeaProjects\\停车场管理系统\\Yuan-SpringBoot-master\\SpringBoot+Vue简单用户管理平台\\usermanager\\src\\main\\java\\com\\example\\usermanager\\PayImages\\" + File.separator + "pay.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
        String url = map.get("code_url");
        System.out.println("生成二维码的url：" + url);
        try {
            Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            hints.put(EncodeHintType.MARGIN, 0);
            BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, 300, 300, hints);

            MatrixToImageWriter.writeToStream(bitMatrix, "jpg", fileOutputStream);
        } catch (Exception e) {
            throw new Exception("生成二维码失败！");
        } finally {
            fileOutputStream.close();
        }
    }
}