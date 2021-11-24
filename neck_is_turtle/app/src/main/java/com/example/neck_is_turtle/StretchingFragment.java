package com.example.neck_is_turtle;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class StretchingFragment extends Fragment implements View.OnClickListener{

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    ArrayList<VideoData> videos = new ArrayList<VideoData>();
    private ListView listView;

    Button btn1,btn2,btn3,btn4;

    public StretchingFragment() {
        // Required empty public constructor
    }

    public static StretchingFragment newInstance(String param1, String param2) {
        StretchingFragment fragment = new StretchingFragment();
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
        View view = inflater.inflate(R.layout.fragment_stretching, container, false);

        // 서버에서 비디오 정보 가져오기// 리스트뷰에 넣기
        listView = view.findViewById(R.id.listview_video1);
        // adapter 가져오기
        VideoAdapter adapter = new VideoAdapter();

        // item정보 리스트뷰에 올리기
        adapter.addItem(new VideoData(1,0,0,"Video Name1","Creator Name1",1));
        adapter.addItem(new VideoData(2,0,0,"Video Name2","Creator Name2",2));
        adapter.addItem(new VideoData(3,0,0,"Video Name3","Creator Name3",3));
        adapter.addItem(new VideoData(4,0,0,"Video Name4","Creator Name4",4));
        listView.setAdapter(adapter);

        btn1 = view.findViewById(R.id.btn_1);
        btn2 = view.findViewById(R.id.btn_2);
        btn3 = view.findViewById(R.id.btn_3);
        btn4 = view.findViewById(R.id.btn_4);

        // onClick 사용설정
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

        return view;
    }

    // 카테고리 버튼들 selected 설정
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_1:
                btn1.setSelected(!btn1.isSelected());
                break;
            case R.id.btn_2:
                btn2.setSelected(!btn2.isSelected());
                break;
            case R.id.btn_3:
                btn3.setSelected(!btn3.isSelected());
                break;
            case R.id.btn_4:
                btn4.setSelected(!btn4.isSelected());
                break;
        }
    }
}