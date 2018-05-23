package com.example.wuyoujian.webviewtest;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.Random;
import java.util.StringTokenizer;

public class MainActivity extends AIBaseActivity {

    WebView mWebView;
    ImageView mImageView;

    private CountDownTimer timer = new CountDownTimer(4000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            //mWebView.loadUrl("http://10.173.148.198:8080/ngboss/");

            mWebView.loadUrl("http://doc.wadecn.com/dmp/login.html");
        }

        @Override
        public void onFinish() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    mImageView.setVisibility(View.GONE);
                    mWebView.setVisibility(View.VISIBLE);
                    //startAlphaAnimationJavaCode();
                }
            });
        }
    };


    private void startAlphaAnimationJavaCode() {
//        //渐变动画    从显示（1.0）到隐藏（0.0）
//        AlphaAnimation alphaAnim = new AlphaAnimation(1.0f, 0.0f);
//        //执行三秒
//        alphaAnim.setDuration(1000);
//        alphaAnim.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//        mImageView.startAnimation(alphaAnim);

        AlphaAnimation alphaAnim1 = new AlphaAnimation(0.0f, 1.0f);
        //执行三秒
        alphaAnim1.setDuration(1000);
        alphaAnim1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //mImageView.setVisibility(View.GONE);
               // mWebView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mWebView.startAnimation(alphaAnim1);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = (WebView)findViewById(R.id.web_view);
        mWebView = webView;

//        TypedValue typedValue = new TypedValue();
//        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
//        final  int color = typedValue.data;
        //webView.setBackgroundColor(color);
        webView.setBackgroundColor(Color.parseColor("#00282333"));
        webView.setBackground(getDrawable(R.color.colorPrimary));
        webView.getBackground().setAlpha(0);

        webView.getSettings().setDefaultTextEncodingName("utf-8") ;
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(webView.getSettings().LOAD_NO_CACHE);
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


//        String token = "isLoginNoSms=1&loginToSuccessPage=1&mobile=" + "13577116615";String token = "isLoginNoSms=1&loginToSuccessPage=1&mobile=" + "13577116615";String token = "isLoginNoSms=1&loginToSuccessPage=1&mobile=" + "13577116615";
        String token = "isLoginNoSms=1&loginToSuccessPage=1&mobile=" + "15925100424";
        try {
            token = AESEncrypt.encrypt(token, "www.asiainfo.com");
            String token00 = URLEncoder.encode(token, "utf-8");

            Log.i("token00",token00);
        } catch (Exception e) {
        }

        //webView.loadDataWithBaseURL(null, "加载中...", "text/html", "utf-8",null);
        //webView.loadUrl("http://plan.wadecn.com/#/");
        mImageView = (ImageView)findViewById(R.id.iv_splash);
        mWebView.loadDataWithBaseURL(null, "加载中...", "text/html", "utf-8",null);
        //webView.setVisibility(View.INVISIBLE);
        timer.start();
        //mWebView.loadUrl("file:///android_asset/welcome.html");


        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //view.setVisibility(View.VISIBLE);
            }
        });



        //webView.loadUrl("http://www.baidu.com");
        //webView.loadUrl("http://www.jd.com");
        //webView.setVisibility(View.VISIBLE);
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
