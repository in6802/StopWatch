package com.example.edu.stopwatch;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView textViewTime;
    Button buttonStart, buttonPause, buttonReset, buttonSave;
    Handler handler = new Handler();
    private long millsecondTime;
    private long startTime;
    private long timeBuf;
    private long updateTime;
    private long seconds;
    private long minutes;
    private long milliseconds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTime = findViewById(R.id.textViewTime);
        buttonStart = findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(this);
        buttonPause = findViewById(R.id.buttonPause);
        buttonPause.setOnClickListener(this);
        buttonReset = findViewById(R.id.buttonReset);
        buttonReset.setOnClickListener(this);
        buttonSave = findViewById(R.id.buttonSaveLap);
        buttonSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.buttonStart:
                onClickStart();
                break;
            case R.id.buttonPause:
                onClickPause();
                break;
            case R.id.buttonReset:
                onClickReset();
                break;
            case R.id.buttonSaveLap:
                onClickSave();
                break;
        }
    }

    public void onClickStart(){
        startTime = SystemClock.uptimeMillis();
        handler.postDelayed(runnable, 0);
    }

    public void onClickPause(){
        timeBuf += millsecondTime;
        handler.removeCallbacks(runnable);
    }

    public void onClickReset(){
        startTime = SystemClock.uptimeMillis();
        timeBuf = 0;
        handler.postDelayed(runnable, 0);
    }

    public void onClickSave(){
        //textViewTime.setText();
        //handler.postDelayed(this,0);

    }

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            millsecondTime = SystemClock.uptimeMillis() - startTime;
            updateTime = timeBuf + millsecondTime;
            seconds = (int)(updateTime / 1000);
            textViewTime.setText(String.format(minutes + ":" + String.format("%02d", seconds) + ":"
                    + String.format("%03d", milliseconds)));
            handler.postDelayed(this,0);
        }
    };
}
