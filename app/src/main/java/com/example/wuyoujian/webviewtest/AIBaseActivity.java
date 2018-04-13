package com.example.wuyoujian.webviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by wuyoujian on 17/3/29.
 */

public abstract class AIBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();

        String string = getRunningActivityName();
        Log.d("ActivityName:%s",string);

    }

    private String getRunningActivityName() {
        String contextString = this.toString();
        return contextString.substring(0, contextString.indexOf("@"));
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    //用来控制应用前后台切换的逻辑
    private boolean isCurrentRunningForeground = true;
    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    public void onStop() {
        super.onStop();
    }
}
