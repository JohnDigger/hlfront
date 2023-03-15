package com.hlfront.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.IOException;

/**
 * @author 贾佳
 * @date 2023/3/7 16:27
 */
public class SendSMS {
//    https://user.ihuyi.com/new/sms/buy/buy
    private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
    public static int sendCode(String phoneNum){
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(Url);

        client.getParams().setContentCharset("GBK");
        method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=GBK");
        int mobile_code = (int)((Math.random()*9+1)*1000);
        String content = new String("您的验证码是："+mobile_code+"。请不要把验证码泄露给其他人。");
        NameValuePair[] data = {
                new NameValuePair("account","C93466470"),
                new NameValuePair("password","6620ab59b969524081bbe61b5d40c08e"),
                new NameValuePair("mobile", phoneNum),
                new NameValuePair("content", content),
        };
        method.setRequestBody(data);
        try {
            client.executeMethod(method);
            String SubmitResult =method.getResponseBodyAsString();
            Document doc = DocumentHelper.parseText(SubmitResult);
            Element root = doc.getRootElement();

            String code = root.elementText("code");
            String msg = root.elementText("msg");
            String smsid = root.elementText("smsid");
//            System.out.println(code);
//            System.out.println(msg);
//            System.out.println(smsid);
            if("2".equals(code)){
                System.out.println("短信提交成功");
                return mobile_code;
            }
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
