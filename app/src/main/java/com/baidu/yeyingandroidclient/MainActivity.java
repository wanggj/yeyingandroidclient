package com.baidu.yeyingandroidclient;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.baidu.yeying.kit.YeYingManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new StartYeyingListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class StartYeyingListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Log.i(MainActivity.class.getName(), "启动夜莺智能客服");
            startYeying();
        }

        private void startYeying() {
            // 获取夜莺SDK 实例
            YeYingManager yyManager = YeYingManager.sharedYYSDK();

            // 以系统标识方式初始化SDK
            yyManager.initWithToken("yeyinghelp", ".baidu.com");
            // 这是客服页面titleBar样式
            yyManager.configTitleBar(null, null, R.drawable.back);

            /*
            // 以系统标识方式初始化SDK，用户信息对接请用这个方式
            // 1.准备相关参数
            String userId = "20136320";
            String userName = "测试用户1";
            String timestamp = System.currentTimeMillis() + "";
            // secureKey: 通讯密钥
            String secureKey = "877a298be951426ba6e48f55c4edf5ce";
            // 2.拼接参数值，按参数名升序排列，这里借助TreeMap实现
            Map<String, String> treeMap = new TreeMap<String, String>();
            treeMap.put("userId", userId);
            treeMap.put("userName", userName);
            treeMap.put("timestamp", timestamp);
            treeMap.put("secureKey", secureKey);
            StringBuffer buffer = new StringBuffer();
            for (Map.Entry<String, String> entry : treeMap.entrySet()) {
                buffer.append(entry.getValue());
            }
            // 3.计算md5
            String signature = MD5Util.md5Hex(buffer.toString());
            // 4.拼接初始化参数，参数顺序随意
            String parameters = String.format("userId=%s&userName=%s&timestamp=%s&signature=%s", userId, userName, timestamp, signature);
            yyManager.initWithTokenAndParam("xxxx", ".baidu.com", parameters);
            */

            // 调起客服页面
            try {
                yyManager.show(MainActivity.this, "online");
            } catch (Exception e) {
                new AlertDialog.Builder(MainActivity.this).setTitle("调起夜莺客服页面失败").setMessage(e.getMessage()).show();
            }
        }
    }
}
