package com.example.turtle_neck;

import static com.example.turtle_neck.R.drawable.rect_part1;
import static com.example.turtle_neck.R.drawable.rect_part2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class activity_stretching extends AppCompatActivity {

    ImageButton btn_home;
    Button btn_exe,btn1,btn2,btn3,btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stretching);

        btn_home = findViewById(R.id.btn_home);
        btn_exe = findViewById(R.id.btn_exe);

        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);

        // 하단 홈버튼 눌렀을 때
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_home = new Intent(activity_stretching.this,MainActivity.class);
                startActivity(intent_home);
                overridePendingTransition(0,0);
                //overridePendingTransition(R.anim.in_left,R.anim.out_right);
            }
        });
        // 상단 운동 버튼 눌렀을 때
        btn_exe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_str = new Intent(activity_stretching.this,activity_exercise.class);
                startActivity(intent_str);
                overridePendingTransition(0,0);
            }
        });
    }
    // 버튼 색 바꾸기
    public void setButton(Button button, boolean click){

        if(click) { // 클릭 된 버튼 바꾸기
            button.setTextColor(Color.WHITE);
            button.setBackground(ContextCompat.getDrawable(this, rect_part2));
        }
        else { // 클릭 안된 버튼들 바꾸기
            button.setTextColor(Color.BLACK);
            button.setBackground(ContextCompat.getDrawable(this, rect_part1));
        }
    }

    public void mOnClick(View v){
        switch (v.getId()){
            case R.id.btn_1:
                setButton(btn1,true);
                setButton(btn2,false);
                setButton(btn3,false);
                setButton(btn4,false);
                break;
            case R.id.btn_2:
                setButton(btn1,false);
                setButton(btn2,true);
                setButton(btn3,false);
                setButton(btn4,false);
                break;
            case R.id.btn_3:
                setButton(btn1,false);
                setButton(btn2,false);
                setButton(btn3,true);
                setButton(btn4,false);
                break;
            case R.id.btn_4:
                setButton(btn1,false);
                setButton(btn2,false);
                setButton(btn3,false);
                setButton(btn4,true);
                break;
        }
    }
}