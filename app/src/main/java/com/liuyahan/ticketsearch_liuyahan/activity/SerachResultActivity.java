package com.liuyahan.ticketsearch_liuyahan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.liuyahan.ticketsearch_liuyahan.R;
import com.liuyahan.ticketsearch_liuyahan.adapter.SearchResultAdapter;
import com.liuyahan.ticketsearch_liuyahan.entity.SearchEntity;

import java.util.List;

public class SerachResultActivity extends AppCompatActivity {
    private TextView city_to_city_tv;
    private TextView time_tv;
    private ListView listView;
    private List<SearchEntity.dataEntity.trainListEntity> trainList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serach_result);
        //初始化
        init();
    }

    private void init() {
        city_to_city_tv = (TextView) findViewById(R.id.city_to_city_tv);
        time_tv = (TextView) findViewById(R.id.time_tv);
        listView = (ListView) findViewById(R.id.info_list_view);
        Intent intent = getIntent();
        String startCity = intent.getStringExtra("startcity");
        String finalCity = intent.getStringExtra("finalcity");
        String times = intent.getStringExtra("times");
        String json = intent.getStringExtra("json");
        Gson gson = new Gson();
        SearchEntity searchEntity = gson.fromJson(json,SearchEntity.class);
        boolean ret = searchEntity.isRet();
        if (ret && searchEntity.getData().getTrainList()!=null){
            SearchEntity.dataEntity data = searchEntity.getData();
            trainList = data.getTrainList();
            city_to_city_tv.setText(startCity + "-" + finalCity + "(共" + trainList.size() + "趟列车)");
            time_tv.setText(times);
            SearchResultAdapter adapter = new SearchResultAdapter(this,trainList);
            String trainType = trainList.get(0).getTrainType();
            listView.setAdapter(adapter);
        } else {
            city_to_city_tv.setText(startCity + "-" + finalCity );
            time_tv.setText(times);
            Toast.makeText(this,"没有相关的列车信息",Toast.LENGTH_LONG).show();
        }

    }

    public void returnTop(View view){
        listView.smoothScrollToPosition(0);
    }

    public void  closeActivity(View view){
        finish();
    }
}
