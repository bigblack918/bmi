package com.example.leo.bmi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setListensers();
    }

    private Button button_calc;
    private EditText num_height;
    private EditText num_weight;
    private TextView show_result;
    private TextView show_suggest;

    private void initViews(){
        button_calc = (Button)findViewById(R.id.button);
        num_height = (EditText)findViewById(R.id.height);
        num_weight = (EditText)findViewById(R.id.weight);
        show_result = (TextView)findViewById(R.id.result);
        show_suggest = (TextView)findViewById(R.id.suggest);
    }

    private void setListensers(){
        button_calc.setOnClickListener(calBmi);
    }

    private View.OnClickListener calBmi = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
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
        }
    };
}
