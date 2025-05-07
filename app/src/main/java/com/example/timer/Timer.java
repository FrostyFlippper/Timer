package com.example.timer;

public class Timer {
    private int currentTime;
    private int maxTime;

    private boolean isFinished;

    public Timer(int maxTime){
        this.maxTime = maxTime;
        this.currentTime = maxTime;
        this.isFinished = false;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public int getMaxTime() {
        return maxTime;
    }

    //test
    public void decreaseTime(){
        if(currentTime == 0){
            System.out.println("Timer finished!");
            isFinished = true;
            return;
        }
        currentTime--;
    }

    public boolean isFinished(){
        return isFinished;
    }
}
