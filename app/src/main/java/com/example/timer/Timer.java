package com.example.timer;

public class Timer {
    private int currentTime;
    private int maxTime;
    private TimerState timerState;

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

    public TimerState getTimerState(){
        return timerState;
    }

    //test
    public void decreaseTime(){
        if(currentTime == 0){
            System.out.println("Timer finished!");
            if(timerState == TimerState.FINISHED){
                return;
            }
        }

        if(timerState == TimerState.RUNNING){
            currentTime--;
        }
    }

    public void setTimerState(TimerState timerState){
        this.timerState = timerState;
    }

    public boolean isFinished(){
        return timerState == TimerState.FINISHED;
    }
}
