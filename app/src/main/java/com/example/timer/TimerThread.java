package com.example.timer;

import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TimerThread extends Thread{
    public final ArrayList<Timer> timerList = new ArrayList<>();
    TextView textView;
    // This lock is used to synchronize access to the timerList
    private final Object lock = new Object();
    // This handler is used to post updates to the UI thread
    private final Handler mainHandler = new Handler(Looper.getMainLooper());


    public TimerThread(TextView textView){
        this.textView = textView;
    }

    public void addToList(Timer timer) {
        // Synchronize access to the timerList
        synchronized (lock) {
            timerList.add(timer);
            lock.notify(); // Wake up the thread if it's waiting
        }
    }

    @Override
    public void run(){
        while(true){
            // Synchronize access to the timerList
            synchronized (lock) {
                while (timerList.isEmpty()) {
                    try {
                        lock.wait(); // Wait until a timer is added
                    } catch (InterruptedException e) {
                        return; // Exit the thread if interrupted
                    }
                }
            }

            // Create a copy of the timerList to avoid concurrent modification issues
            List<Timer> timersCopy;
            synchronized (lock) {
                timersCopy = new ArrayList<>(timerList); // Create a copy to avoid modification issues
            }

            // Iterate over the timers and update their state
            for (Timer timer : timersCopy) {
                if (timer.getTimerState() == TimerState.FINISHED) {
                    synchronized (lock) {
                        timerList.remove(timer);
                    }
                } else {
                    // Update the UI with the current time
                    mainHandler.post(() -> textView.setText(String.valueOf(timer.getCurrentTime()))); // Update UI
                    timer.decreaseTime();
                    try {
                        Thread.sleep(1000); // Sleep for 1 second
                    } catch (InterruptedException e) {
                        return; // Exit the thread if interrupted
                    }
                }
            }
        }
    }
}
