package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView textView;
    Button button;
    long maxTime;
    String secS;
    String minS;
    int sec;
    int min;
    boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar= findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView2);
        button = findViewById(R.id.button);

        seekBar.setMax(600000);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b){
                    maxTime = i/1000*1000;
                    sec = (i/1000)%60;
                    min = (i/1000)/60;
                    minS = ""+min;
                    secS = ""+sec;

                    if (minS.length()==1)
                        minS="0"+minS;
                    if (secS.length()==1)
                        secS="0"+secS;

                    textView.setText(minS + " : "+ secS);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    CountDownTimer countDownTimer = new CountDownTimer(600001, 1000) {
        @Override
        public void onTick(long l) {

            if (sec == 0 && min == 0) {
                countDownTimer.cancel();
                button.setText("Start");
                seekBar.setProgress(0);
                seekBar.setEnabled(true);
                flag = true;
            }

            if (sec != 0){
                sec--;
            } else {
                if (min != 0){
                    sec = 59;
                    min--;
                }
            }
            secS = ""+sec;
            minS = ""+min;
            if (minS.length()==1)
                minS="0"+minS;
            if (secS.length()==1)
                secS="0"+secS;

            textView.setText(minS + " : "+ secS);
        }

        @Override
        public void onFinish() {

        }
    };

    public void start(View view) {
        if (flag == true){
            countDownTimer.start();
            button.setText("Stop");
            seekBar.setEnabled(false);
            flag = false;
        }
        else {
            countDownTimer.cancel();
            button.setText("Start");
            seekBar.setEnabled(true);
            flag = true;
        }
    }
}