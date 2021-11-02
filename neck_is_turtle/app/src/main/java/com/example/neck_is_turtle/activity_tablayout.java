package com.example.neck_is_turtle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class activity_tablayout extends FragmentActivity {

    TabLayout tabs;

    fragment_stretching stretching;
    fragment_exercise exercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);

        stretching = new fragment_stretching();
        exercise = new fragment_exercise();

        getSupportFragmentManager().beginTransaction().add(R.id.container, stretching).commit();

        tabs = findViewById(R.id.tabs);

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment selected = null;
                if(position == 0)
                    selected = stretching;
                else if(position == 1)
                    selected = exercise;
                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}