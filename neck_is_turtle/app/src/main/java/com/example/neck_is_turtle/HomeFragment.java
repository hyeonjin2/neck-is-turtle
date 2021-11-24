package com.example.neck_is_turtle;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.Calendar;

public class HomeFragment extends Fragment {

    private TextView tv_today;
    private Calendar calendar;
    private Button btn_add,btn_show;
    private LinearLayout lay_empty,lay_schedule;
    private ListView listView_alerts,listView_missions;

    String[] days = new String[]{"일요일","월요일","화요일","수요일","목요일","금요일","토요일"};

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        tv_today = view.findViewById(R.id.tv_today);
        btn_add = view.findViewById(R.id.btn_add);
        lay_empty = view.findViewById(R.id.layout_empty);

        // calendar에서 가져온 int값 요일로 바꿔 설정하기 -> 1:일요일 ~ 7:토요일
        calendar = Calendar.getInstance();
        tv_today.setText(days[calendar.get(calendar.DAY_OF_WEEK)-1]);

        // 사용자가 설정한 알람이 있는지 확인
        /*
         * 서버에서 사용자에 대해 알람설정 정보 가져와 확인하기
         * */
        // 설정한 알람이 없다면 추가하기 버튼 기능 구현
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("clicked",true);
                Intent intent = new Intent(getActivity(),MainActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        // 설정한 알람이 있다면 추가하기 박스 invisible설정 및 알람
        lay_empty.setVisibility(View.GONE);
        // 알람리스트뷰에 알람 올리기
        listView_alerts = view.findViewById(R.id.listview_schedule);
        listView_alerts.setVisibility(View.VISIBLE);
        btn_show = view.findViewById(R.id.btn_show);

        AlertAdapter adapter = new AlertAdapter();

        // 알람정보 알람리스트뷰에 올리기
        boolean[] list_days = new boolean[]{true,false,false,false,true,false,false};
        adapter.addItem(new AlertData(21,30,list_days));
        adapter.addItem(new AlertData(23,45,list_days));
        adapter.addItem(new AlertData(15,20,list_days));
        adapter.addItem(new AlertData(17,10,list_days));
        listView_alerts.setAdapter(adapter);

        btn_show.setVisibility(View.VISIBLE);
        // 알람리스트뷰 크기 설정
        final int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 177, view.getResources().getDisplayMetrics());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(MATCH_PARENT,height);
        listView_alerts.setLayoutParams(params);

        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_show.setSelected(!btn_show.isSelected());
                if(!btn_show.isSelected()) {
                    adapter.setShowCount(adapter.getSize());
                    int numSize = 80*adapter.getSize() + 15*(adapter.getSize() - 1)+2;
                    final int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, numSize, view.getResources().getDisplayMetrics());
                    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(MATCH_PARENT, height);
                    listView_alerts.setLayoutParams(params);
                    btn_show.setText("알람 접기");
                }
                else{
                    adapter.setShowCount(2);
                    final int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 177, view.getResources().getDisplayMetrics());
                    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(MATCH_PARENT,height);
                    listView_alerts.setLayoutParams(params);
                    btn_show.setText("알람 더보기");
                }
            }
        });
        // 미션리스트뷰
        listView_missions = view.findViewById(R.id.listview_mission);
        MissionAdapter adapter1 = new MissionAdapter();

        adapter1.addItem(new MissionData("스트레칭 하기","목 스트레칭을 1분간 함께 해봐요!"));
        adapter1.addItem(new MissionData("측면사진 기록 남기기","아직 측면사진 기록을 남기지 않으셨네요!"));
        adapter1.addItem(new MissionData("바른 자세 챌린지","추후 구현할 예정입니다."));
        listView_missions.setAdapter(adapter1);

        int numSize1 = 80*adapter1.getCount() + 15*(adapter1.getCount() - 1)+2;
        final int height1 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, numSize1, view.getResources().getDisplayMetrics());
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(MATCH_PARENT,height1);
        listView_missions.setLayoutParams(params1);

        return view;
    }
}