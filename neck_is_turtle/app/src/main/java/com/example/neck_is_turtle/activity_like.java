package com.example.neck_is_turtle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class activity_like extends AppCompatActivity {

    ImageButton btn_back;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like);

        btn_back = findViewById(R.id.btn_back);
        linearLayout = findViewById(R.id.lay_like);

        // 뒤로가기 버튼 클릭했을 때
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(0, 0);
            }
        });

        // 레이아웃 인플레이터 객체
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // 메인에 새로 생성한 레이아웃 추가
        layoutInflater.inflate(R.layout.video_box, linearLayout, true);
    }
}