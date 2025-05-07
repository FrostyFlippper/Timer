package com.example.timer;

import android.widget.TextView;

import java.util.ArrayList;

public class TimerThread extends Thread{
    public final ArrayList<Timer> timerList = new ArrayList<>();
    TextView textView;

    public TimerThread(TextView textView){
        this.textView = textView;
    }

    public void addToList(Timer timer){
        timerList.add(timer);
    }

    @Override
    public void run(){
        while(true){
            for(Timer timer : timerList){
                if(!timer.isFinished()){
                    timer.decreaseTime();
                    System.out.println(timer.getCurrentTime());
                    textView.setText(String.valueOf(timer.getCurrentTime()));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
