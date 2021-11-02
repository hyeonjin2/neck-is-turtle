package com.example.neck_is_turtle;

public class Alerts {
    static final String Am = "오전";
    static final String Pm = "오후";

    int hour, minute;
    boolean[] days = new boolean[7];
    String ampm;

    Alerts(int num1, int num2, boolean[] arr){
        this.hour = num1;
        this.minute = num2;
        this.days = arr;

        if(num1 >= 12)
            this.ampm = Pm;
        else
            this.ampm = Am;
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
