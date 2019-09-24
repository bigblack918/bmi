package com.example.leo.bmi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
/*
* 靜態註冊
* 1.需要再manifests中註冊receiver
* 2.透過action進行filter
*
* */
public class BroadcastActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    Button sendBroadcastBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        sendBroadcastBtn = findViewById(R.id.sendBraodcastBtn);
        sendBroadcastBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sendBraodcastBtn:
                Intent intent = new Intent();
                intent.setAction("MyReceiver");
                intent.putExtra("HELLO", "How are you.");
                sendBroadcast(intent);
                Log.e(TAG, "Broadcast sended!");
                break;
        }
    }

}
