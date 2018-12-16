package com.example.edu.stopwatch;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView textViewTime, textViewSave;
    Button buttonStart, buttonPause, buttonReset, buttonSave;
    Handler handler = new Handler();
    private long millsecondTime;
    private long startTime;
    private long timeBuf;
    private long updateTime;
    private long seconds;
    private long minutes;
    private long milliseconds;
    private String saveTime ="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTime = findViewById(R.id.textViewTime);
        textViewSave = findViewById(R.id.textViewSave);
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
        buttonPause.setEnabled(true);
    }

    public void onClickPause(){
        timeBuf += millsecondTime;
        handler.removeCallbacks(runnable);
        buttonPause.setEnabled(false);
    }

    public void onClickReset(){
        startTime = SystemClock.uptimeMillis();
        timeBuf = 0;
        textViewSave.setText("");
        saveTime = "";
        handler.postDelayed(runnable, 0);
    }

    public void onClickSave(){
        saveTime += String.format(minutes + ":" + String.format("%02d", seconds) + ":"
                + String.format("%03d", milliseconds)) + "\n";
        textViewSave.setText(saveTime);
        //handler.postDelayed(runnable,0);
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


    /*기본 기능에 충실한 스탑워치입니다.
        save lap을 클릭하면 중간 시간을 체크할 수 있습니다.*/

