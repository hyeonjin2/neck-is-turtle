package com.example.neck_is_turtle;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

public class VideoAdapter extends BaseAdapter implements TimePicker.OnTimeChangedListener {
    ArrayList<VideoData> videos = new ArrayList<VideoData>();
    Context context;

    ImageButton btn_alert,btn_like;
    TimePicker timePicker;
    Button day_mon,day_tue,day_wed,day_thu,day_fri,day_sat,day_sun,btn_set,btn_mclose;
    Button btn_see,btn_sclose;
    TextView mtv_video,mtv_creator;
    Dialog mDialog,sDialog;
    Calendar calendar;
    int shour,smin;

    @Override
    public int getCount() {
        return videos.size();
    }

    @Override
    public Object getItem(int position) {
        return videos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        context = parent.getContext();
        VideoData videoData = videos.get(position);

        mDialog = new Dialog(context);
        mDialog.setContentView(R.layout.dialog_alert);

        sDialog = new Dialog(context);
        sDialog.setContentView(R.layout.dialog_alert_set_completion);

        // 현재 화면에 보여지는 뷰만 만들도록 설정
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.box_video, parent, false);
        }
        // box_video 아이템들 가져오기
        ImageView thumbnail = convertView.findViewById(R.id.img_video);
        ImageView profile = convertView.findViewById(R.id.img_creator);
        TextView tv_video = convertView.findViewById(R.id.tv_video);
        TextView tv_creator = convertView.findViewById(R.id.tv_creator);

        // VideoData에 있는 정보 넣기
        tv_video.setText(videos.get(position).getVideoName());
        tv_creator.setText(videos.get(position).getCreatorName());
        thumbnail.setImageResource(R.drawable.video_example);
        profile.setImageResource(R.drawable.creator_example);

        btn_alert = convertView.findViewById(R.id.btn_alert);
        btn_like = convertView.findViewById(R.id.btn_like);

        // 대화상자 가져오기

        // 대화상자 아이템들 가져오기
        timePicker = mDialog.findViewById(R.id.timePicker);
        timePicker.setOnTimeChangedListener(this);

        day_mon = mDialog.findViewById(R.id.day_mon);
        day_tue = mDialog.findViewById(R.id.day_tue);
        day_wed = mDialog.findViewById(R.id.day_wed);
        day_thu = mDialog.findViewById(R.id.day_thu);
        day_fri = mDialog.findViewById(R.id.day_fri);
        day_sat = mDialog.findViewById(R.id.day_sat);
        day_sun = mDialog.findViewById(R.id.day_sun);

        mtv_video = mDialog.findViewById(R.id.tv_video);
        mtv_creator = mDialog.findViewById(R.id.tv_creator);

        btn_set = mDialog.findViewById(R.id.set);
        btn_mclose = mDialog.findViewById(R.id.btn_close);

        btn_see = sDialog.findViewById(R.id.see);
        btn_sclose = sDialog.findViewById(R.id.btn_close);

        // 대화상자 요일버튼 클릭리스너 지정
        day_mon.setOnClickListener(this.mOnClickListener);
        day_tue.setOnClickListener(this.mOnClickListener);
        day_wed.setOnClickListener(this.mOnClickListener);
        day_thu.setOnClickListener(this.mOnClickListener);
        day_fri.setOnClickListener(this.mOnClickListener);
        day_sat.setOnClickListener(this.mOnClickListener);
        day_sun.setOnClickListener(this.mOnClickListener);

        // 타임피커 초기값 현재시간으로 설정
        calendar = Calendar.getInstance();
        shour = calendar.get(calendar.HOUR_OF_DAY);
        smin = calendar.get(calendar.MINUTE);

        // 알람설정 버튼 눌렀을 때
        btn_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                mtv_video.setText(videos.get(position).getVideoName());
                mtv_creator.setText(videos.get(position).getCreatorName());
                mDialog.show();
            }
        });

        // 알람 대화상자 완료버튼 눌렀을 때
        btn_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 서버로 데이터 넘기기

                // 창 닫기

                // 완료상자 띄우기

                // 알람 이미지 selected로 바꾸기
            }
        });

        // 알람 대화상자 닫기버튼 눌렀을 때
        btn_mclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 창 닫기
                mDialog.cancel();
            }
        });

        // 좋아요 기능 구현
        btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_like.setSelected(!btn_like.isSelected());
            }
        });
        return convertView;
    }

    // 아이템 add함수
    public void addItem(VideoData videoData) {
        videos.add(videoData);
    }

    // 타임피커에서 설정된 시간 가져오기
    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        shour = hourOfDay;
        smin = minute;
    }
    // 대화상자 요일 버튼들 selected 설정
    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.day_mon:
                    day_mon.setSelected(!day_mon.isSelected());
                    break;
                case R.id.day_tue:
                    day_tue.setSelected(!day_tue.isSelected());
                    break;
                case R.id.day_wed:
                    day_wed.setSelected(!day_wed.isSelected());
                    break;
                case R.id.day_thu:
                    day_thu.setSelected(!day_thu.isSelected());
                    break;
                case R.id.day_fri:
                    day_fri.setSelected(!day_fri.isSelected());
                    break;
                case R.id.day_sat:
                    day_sat.setSelected(!day_sat.isSelected());
                    break;
                case R.id.day_sun:
                    day_sun.setSelected(!day_sun.isSelected());
                    break;
            }
        }
    };
}

