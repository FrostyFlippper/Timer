package com.example.timer;

import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity{

    Timer timer = new Timer(10);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.timerTextView);
        Button resumeButton = findViewById(R.id.resumeButton);
        Button pauseButton = findViewById(R.id.pauseButton);

        resumeButton.setOnClickListener(new resumeButtonListener());
        pauseButton.setOnClickListener(new pauseButtonListener());

        View rootView = findViewById(android.R.id.content);
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                rootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                // Start the timer thread after the layout is fully loaded
                TimerThread timerThread = new TimerThread(textView);
                timerThread.addToList(timer);
                timerThread.start();
            }
        });
    }

    class resumeButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            
        }
    }

    class pauseButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {

        }
    }
}