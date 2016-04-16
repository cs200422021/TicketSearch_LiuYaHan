package com.liuyahan.ticketsearch_liuyahan.activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.liuyahan.ticketsearch_liuyahan.R;
import com.liuyahan.ticketsearch_liuyahan.utils.HttpTool;

import java.net.URLEncoder;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private EditText startEditText;
    private EditText finalEditText;
    private EditText timeEditText;
    private String startCity;
    private String finalCity;
    private String times;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;
    private Long exitTime = 0l;
    private ProgressDialog progressDialog;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String json = (String) msg.obj;
            progressDialog.dismiss();
            Intent intent = new Intent(MainActivity.this,SerachResultActivity.class);
            intent.putExtra("startcity",startCity);
            intent.putExtra("finalcity",finalCity);
            intent.putExtra("times", times);
            intent.putExtra("json",json);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化
        init();
    }

    private void init() {
        startEditText = (EditText) findViewById(R.id.start_edit_text);
        finalEditText = (EditText) findViewById(R.id.final_edit_text);
        timeEditText = (EditText) findViewById(R.id.time_edit_view);
        calendar = Calendar.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("查询中,请稍后...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);




    }

    public void turnCity(View view) {
        //转换始发站与终点站
        startCity = startEditText.getText().toString();
        finalCity = finalEditText.getText().toString();
        startEditText.setText(finalCity);
        finalEditText.setText(startCity);
    }

    public void openTimePicker(View view) {
        //打开timepicker并赋值给edittext
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                if (monthOfYear<9){
                    times = year + "-0" + (monthOfYear + 1) + "-" + dayOfMonth;
                }else {
                    times = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                }
                timeEditText.setText(times);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void showProgressDialog(View view) {
        //弹出进度条对话框,并查询下载数据,跳转结果页
        //获取点击查询按钮时各个edittext中的数据
        startCity = startEditText.getText().toString();
        finalCity = finalEditText.getText().toString();
        times = timeEditText.getText().toString();
        //根据各个数据长度,来判断是否输入为空,如果不为空则执行进度条显示和启动新线程下载
        if (startCity.length()>0){
            if (finalCity.length()>0){
                if (times.length()>0){
                    //进度条显示和启动新线程下载
                    progressDialog.show();
                    new DownJson().start();
                }else{
                    Toast.makeText(this,"请选择出发时间",Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this,"请输入终点站",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this,"请输入始发站",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        exit();
    }

    private void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, "再点一次退出", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    class DownJson extends Thread{

        @Override
        public void run() {
            super.run();

            String result = HttpTool.request("http://apis.baidu.com/qunar/qunar_train_service/s2ssearch", "version=1.0&from="+ URLEncoder.encode(startCity)+"&to="+URLEncoder.encode(finalCity)+"&date="+URLEncoder.encode(times));
            Message message = new Message();
            message.obj = result;
            handler.sendMessage(message);


        }


    }
}
