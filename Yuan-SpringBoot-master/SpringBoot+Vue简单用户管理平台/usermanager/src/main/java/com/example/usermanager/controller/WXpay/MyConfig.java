package com.example.usermanager.controller.WXpay;


import com.example.usermanager.wxpaysdk.IWXPayDomain;
import com.example.usermanager.wxpaysdk.WXPayConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MyConfig extends WXPayConfig {

    private byte[] certData;

    public MyConfig() throws Exception {
//        String certPath = "/Users/liwei/IdeaProjects/wxpaydemo/src/main/resources/cert/apiclient_cert.p12";
//        File file = new File(certPath);
//        InputStream certStream = new FileInputStream(file);
//        this.certData = new byte[(int) file.length()];
//        certStream.read(this.certData);
//        certStream.close();
    }

    // 微信公众号的APPID
    @Override
    public String getAppID() {
        return "wxd9a46e74fc279fcc";
    }

    // 商户ID
    @Override
    public String getMchID() {
        return "1623889015";
    }

    // 密钥
    @Override
    public String getKey() {
        return "v2LZk95bXz6VPjQq9yOPg3wPCD3BKqcM";
    }

    @Override
    public InputStream getCertStream() {
//        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        InputStream certBis= null;
        try {
            certBis = new FileInputStream(new File("src/main/resources/cert/apiclient_cert.p12"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return certBis;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }

    @Override
    public IWXPayDomain getWXPayDomain() {
        return new IWXPayDomain() {
            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {
            }

            @Override
            public DomainInfo getDomain(WXPayConfig config) {
                return new DomainInfo("api.mch.weixin.qq.com", false);
            }
        };
    }

}