package com.example.timer;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.button);

        textView.setOnClickListener(new listener());


        TimerThread timerThread = new TimerThread(textView);
        timerThread.addToList(timer);
        timerThread.start();
    }

    class listener implements View.OnClickListener{
        @Override
        public void onClick(View v) {

        }
    }
}