package com.example.neck_is_turtle;

public class AlertData {
    static final String Am = "오전";
    static final String Pm = "오후";

    int videoId;
    int hour, minute;
    boolean[] days = new boolean[7];
    String ampm;

    AlertData(){}

    AlertData(int num, int num1, int num2, boolean[] arr){
        this.videoId = num;
        this.hour = num1;
        this.minute = num2;
        this.days = arr;

        if(num1 >= 12)
            this.ampm = Pm;
        else
            this.ampm = Am;
    }

    AlertData(int num1, int num2, boolean[] arr){
        this.hour = num1;
        this.minute = num2;
        this.days = arr;

        if(num1 >= 12)
            this.ampm = Pm;
        else
            this.ampm = Am;
    }
    int getVideoId(){
        return this.videoId;
    }

    int getHour(){
        int result;
        if(this.ampm.equals(Pm)){
            if(this.hour!=12)
                result = this.hour - 12;
            else
                result = this.hour;
        }
        else
            result = this.hour;
        return result;
    }

    int getMinute(){ return this.minute; }

    boolean[] getDays(){ return this.days; }

    String getAmpm(){ return this.ampm; }
}
