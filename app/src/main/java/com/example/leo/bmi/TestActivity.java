package com.example.leo.bmi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    Intent intent;
    private Button serviceButton;
    private Button broadcastButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        serviceButton = findViewById(R.id.serviceBtn);
        serviceButton.setOnClickListener(this);
        broadcastButton = findViewById(R.id.broadcastBtn);
        broadcastButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.serviceBtn:
                intent = new Intent(this, ServiceActivity.class);
                startActivity(intent);
                break;
            case R.id.broadcastBtn:
                intent = new Intent(this, BroadcastActivity.class);
                startActivity(intent);
                break;
        }
    }
}
