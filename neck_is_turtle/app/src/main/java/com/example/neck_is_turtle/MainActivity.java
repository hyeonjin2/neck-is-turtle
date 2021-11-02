package com.example.neck_is_turtle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    ImageButton btn_stretching,btn_alert,btn_like;
    TextView tv_today;
    Calendar calendar;

    String[] days = new String[]{"일요일","월요일","화요일","수요일","목요일","금요일","토요일"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_stretching=findViewById(R.id.btn_stretching);
        btn_alert = findViewById(R.id.btn_alert);
        btn_like = findViewById(R.id.btn_like);
        tv_today = findViewById(R.id.tv_today);

        // calendar에서 가져온 int값 요일로 바꿔 설정하기 -> 1:일요일 ~ 7:토요일
        calendar = Calendar.getInstance();
        tv_today.setText(days[calendar.get(calendar.DAY_OF_WEEK)-1]);

        // 하단 메뉴바 스트레칭 클릭 시
        btn_stretching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,activity_tablayout.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                //overridePendingTransition(R.anim.in_right,R.anim.out_left);
            }
        });

        // 상단 알람 버튼 클릭 시
        btn_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_alert = new Intent(MainActivity.this,activity_alert.class);
                startActivity(intent_alert);
                overridePendingTransition(0,0);
            }
        });

        // 상단 하트 버튼 클릭 시
        btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_like = new Intent(MainActivity.this,activity_like.class);
                startActivity(intent_like);
                overridePendingTransition(0,0);
            }
        });
    }
}