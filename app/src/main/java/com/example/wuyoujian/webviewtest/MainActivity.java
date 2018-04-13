package com.example.wuyoujian.webviewtest;

import android.graphics.Point;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.Random;
import java.util.StringTokenizer;

public class MainActivity extends AIBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = (WebView)findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.setWebContentsDebuggingEnabled(true);
        }

        webView.getSettings().setAppCacheEnabled(false);


        WebSettings ws = webView.getSettings();
        try {
            if (Build.VERSION.SDK_INT >= 16) {
                Class<?> clazz = webView.getSettings().getClass();
                Method method = clazz.getMethod(
                        "setAcceptThirdPartyCookies", boolean.class);
                if (method != null) {
                    method.invoke(webView.getSettings(), true);
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        ws.setSupportZoom(false);
        ws.setTextSize(WebSettings.TextSize.NORMAL);//
        ws.setAllowFileAccess(true);
        webView.setWebViewClient(new WebViewClient());

//        String token = "isLoginNoSms=1&loginToSuccessPage=1&mobile=" + "13577116615";String token = "isLoginNoSms=1&loginToSuccessPage=1&mobile=" + "13577116615";String token = "isLoginNoSms=1&loginToSuccessPage=1&mobile=" + "13577116615";
        String token = "isLoginNoSms=1&loginToSuccessPage=1&mobile=" + "15925100424";
        try {
            token = AESEncrypt.encrypt(token, "www.asiainfo.com");
            String token00 = URLEncoder.encode(token, "utf-8");

            Log.i("token00",token00);
        } catch (Exception e) {
        }



        webView.loadUrl("http://10.174.61.149/html/iframe.html");
        //http://www.wadecn.com/doc/Demo/test.html
        //http://10.174.61.149/html/demo.html
        //webView.loadUrl("http://www.wadecn.com/doc/Demo/test.html");

        String string =  orderIdString("18600746313","ABOSS072","10000");
        Log.d("string",string);

    }

    public static String randomString(int lenght) {
        String s = "1234567890abcdefghijknmlopqrstuvwxyzABCDEFGHIJKNMLOPQRSTUVWXYZ";
        char[] cs = s.toCharArray();
        int length = s.length();

        Random random = new Random();
        String zh_string = new String();
        for(int i = 0; i < lenght; i++) {
            int index = random.nextInt(length);
            zh_string += cs[index];
        }

        return zh_string;
    }

    // 8099(4)+手机号(11) + 工号(8) + "_"(1) +  4位随机码(4) + 金额(4) =  32位
    public static String orderIdString (String phonenumber, String acc,String money) {
        double moneyDouble = Double.parseDouble(money);
        double moneyInt = Math.floor(moneyDouble);
        int x = (int)(moneyInt);
        String moneyString = Integer.toString(x);
        if (moneyString.length() > 4) {
            moneyString = moneyString.substring(0,4);
        }

        String randomStr = randomString(4);
        String orderId = "8099" + phonenumber + acc + "_" + randomStr + moneyString;

        Log.i("pay orderId:",orderId);
        return orderId;
    }


    public static void main(String [] args) throws Exception {
        String token = "isLoginNoSms=1&loginToSuccessPage=1&mobile=" + "15925100424";
        try {
            token = AESEncrypt.encrypt(token, "www.asiainfo.com");
            String token00 = URLEncoder.encode(token, "utf-8");
            Log.i("token00",token00);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
