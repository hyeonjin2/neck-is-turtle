package com.example.neck_is_turtle;

import androidx.annotation.ContentView;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class activity_alert extends AppCompatActivity {

    ImageButton btn_back;
    TextView tv_hour,tv_minute,tv_ampm,
            tv_mon,tv_tue,tv_wed,tv_thu,tv_fri,tv_sat,tv_sun;
    TextView[] tv_days;
    int hour, minute;
    boolean[] selected_days = new boolean[7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        btn_back = findViewById(R.id.btn_back);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lay_alerts);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(0, 0);
            }
        });

        // 알람이 존재하는지 확인 후 layout 넣기
        /*
        *
        * 서버와 연동해서 확인하는 코드 작성
        *
        * */
        // 레이아웃 인플레이터 객체
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // 메인에 새로 생성한 레이아웃 추가
        layoutInflater.inflate(R.layout.alert_box, linearLayout, true);

        // alert_box에 있는 item 가져오기
        tv_hour = linearLayout.findViewById(R.id.tv_hour);
        tv_minute = linearLayout.findViewById(R.id.tv_minute);
        tv_ampm = linearLayout.findViewById(R.id.tv_ampm);

        tv_mon = linearLayout.findViewById(R.id.tv_mon);
        tv_tue = linearLayout.findViewById(R.id.tv_tue);
        tv_wed = linearLayout.findViewById(R.id.tv_wed);
        tv_thu = linearLayout.findViewById(R.id.tv_thu);
        tv_fri = linearLayout.findViewById(R.id.tv_fri);
        tv_sat = linearLayout.findViewById(R.id.tv_sat);
        tv_sun = linearLayout.findViewById(R.id.tv_sun);

        tv_days = new TextView[]{tv_mon, tv_tue, tv_wed, tv_thu, tv_fri, tv_sat, tv_sun};

        // 임의로 알람 설정하기
        Alerts a1 = new Alerts(13, 45, null);
        selected_days = new boolean[]{false, false, true, true, true, false, false};

        // 알람내용 item에 적용하기
        tv_hour.setText(String.valueOf(a1.getHour()));
        tv_minute.setText(String.valueOf(a1.getMinute()));
        tv_ampm.setText(a1.getAmpm());
        //selected_days = a1.getDays();

        for (int i = 0; i < 7; i++) {
            tv_days[i].setSelected(selected_days[i]);
        }
    }
}