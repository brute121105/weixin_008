package hyj.weixin_008;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.Map;

import hyj.weixin_008.flowWindow.MyWindowManager;
import hyj.weixin_008.util.FileUtil;
import hyj.weixin_008.util.GetPermissionUtil;
import hyj.weixin_008.util.LogUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyWindowManager.createSmallWindow(getApplicationContext());
        GetPermissionUtil.getReadAndWriteContactPermision(this,MainActivity.this);
        setContentView(R.layout.activity_main);
        //AutoUtil.wakeAndUnlock();

        Toast.makeText(MainActivity.this, "开启权限11", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        startActivity(intent);
        //AutoUtil.startAppByPackName("com.soft.apk008v","com.soft.apk008.LoadActivity");
        Toast.makeText(MainActivity.this, "开启权限", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
