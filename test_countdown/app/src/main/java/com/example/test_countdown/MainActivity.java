package com.example.test_countdown;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button, button2;
    TimerTask timerTask;
    Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimerTask();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimerTask();
            }
        });

    }
    @Override
    protected void onDestroy()
    {
        timer.cancel();
        super.onDestroy();
    }

    public void startTimerTask(){
        stopTimerTask();

        timerTask = new TimerTask()
        {
            int count = 60;

            @Override
            public void run()
            {
                count--;
                textView.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(count + "초");
                    }
                });
            }
        };
        timer.schedule(timerTask,0 ,1000);
    }

    public void stopTimerTask(){
        if(timerTask != null)
        {
            //textView.setText("60초");
            timerTask.cancel();
            timerTask = null;
        }
    }
}
