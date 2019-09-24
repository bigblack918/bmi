package com.example.leo.bmi;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.example.leo.bmi.DAO.BmiDao;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    private SQLiteDatabaseHelper helper;
    private EditText num_height;
    private EditText num_weight;
    private TextView show_result;
    private TextView show_suggest;
    private Button calButton;
    private Button clrButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews(){
        helper = new SQLiteDatabaseHelper(this);
        helper.getWritableDatabase();

        num_height = (EditText)findViewById(R.id.height);
        num_weight = (EditText)findViewById(R.id.weight);
        show_result = (TextView)findViewById(R.id.result);
        show_suggest = (TextView)findViewById(R.id.suggest);
        calButton = findViewById(R.id.button);
        calButton.setOnClickListener(this);
        clrButton = findViewById(R.id.clear);
        clrButton.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id)
        {
            case R.id.action_settings:
//                openOptionsDialog();
                Intent intent = new Intent();
                intent.setClass(this, TestActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openOptionsDialog()
    {
        new AlertDialog.Builder(this)
                .setTitle("關於Android BMI")
                .setMessage("Android BMI 計算")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                .show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                calBMI();
                insert();
                hideKeyboard();
                break;
            case R.id.clear:
                clearInput();
        }

    }

    //BMI計算
    private void calBMI(){
        DecimalFormat nf = new DecimalFormat("0.00");
        //身高
        double height = Double.parseDouble(num_height.getText().toString())/100;
        //體重
        double weight = Double.parseDouble(num_weight.getText().toString());
        //計算出BMI值
        double BMI = weight / (height*height);

        //結果
        show_result.setText(getText(R.string.bmi_result) + nf.format(BMI));

        //建議
        if(BMI > 25) //太重了
            show_suggest.setText(R.string.advice_heavy);
        else if(BMI < 20) //太輕了
            show_suggest.setText(R.string.advice_light);
        else //剛剛好
            show_suggest.setText(R.string.advice_average);

        showToast("計算完成");

    }

    //清除EditView, 並focus num_height
    private void clearInput(){
        num_height.setText("");
        num_weight.setText("");
        show_result.setText("");
        show_suggest.setText("");
        num_height.requestFocus();
        showToast("已清除");
    }

    //隱藏鍵盤
    private void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    //顯示Toast
    private void showToast(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void insert(){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("height", String.valueOf(num_height.getText()));
        cv.put("weight", String.valueOf(num_weight.getText()));
        db.insert(BmiDao.TABLE_NAME,null,cv);
    }
}
