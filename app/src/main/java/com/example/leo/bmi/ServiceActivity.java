package com.example.leo.bmi;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/*
 * StartService
 * 1.startService生命週期：onCreate->onStartCommand->onDestroy
 * 2.onBind不會被呼叫
 * 3.無論執行多少次startService()，只要執行一次stopService()就會停止
 *
 * Bind
 * 1.Sercice.class需建立Binder class
 * 2.Activity.class需建立connection
 * 3.Bind生命週期：onCreate->onBind->unBind->onDestroy
 *
 * 交叉執行
 * 1.startService->Bind->只可用unBind才可關閉
 * 2.Bind->startService->只可用unBind才可關閉
 * */
public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    Intent intent;
    Button startServiceBtn;
    Button stopServiceBtn;
    Button bindServiceBtn;
    Button unbindServiceBtn;
    Button getServiceStatusBtn;

    //建立activity和service連線機制
    MyService.MyBinder binder;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(TAG,"------Service Connected-------");
            binder = (MyService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG,"------Service DisConnected-------");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        startServiceBtn = findViewById(R.id.startServiceBtn);
        startServiceBtn.setOnClickListener(this);
        stopServiceBtn = findViewById(R.id.stopServiceBtn);
        stopServiceBtn.setOnClickListener(this);
        bindServiceBtn = findViewById(R.id.bindServiceBtn);
        bindServiceBtn.setOnClickListener(this);
        unbindServiceBtn = findViewById(R.id.unbindServiceBtn);
        unbindServiceBtn.setOnClickListener(this);
        getServiceStatusBtn = findViewById(R.id.getServiceStatusBtn);
        getServiceStatusBtn.setOnClickListener(this);
        intent = new Intent(this, MyService.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startServiceBtn:
                startService(intent);
                break;
            case R.id.stopServiceBtn:
                stopService(intent);
                break;
            case R.id.bindServiceBtn:
                bindService(intent, conn, BIND_AUTO_CREATE);
                break;
            case R.id.unbindServiceBtn:
                unbindService(conn);
                break;
            case R.id.getServiceStatusBtn:
                Log.e(TAG,"Service count = "+binder.getCount());
                Toast.makeText(this,"Service count = "+binder.getCount(),Toast.LENGTH_SHORT).show();
                break;


        }
    }
}
