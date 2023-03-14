package com.example.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private int seconds;
    private boolean runnning;
    private boolean wasRunnning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null)
        {
            savedInstanceState.getInt("seconds");
            savedInstanceState.getBoolean("runnning");
            savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }
    public void onStart(View view)
    {
        runnning=true;
    }
    public void onStop(View view)
    {
        runnning=false;
    }
    public void onReset(View view)
    {
        runnning=false;
        seconds=0;
    }
    public void onPause(View view)
    {
        super.onPause();
        wasRunnning=runnning;
        runnning=false;
    }
    public void onResume(View view)
    {
        super.onResume();
       if(wasRunnning)
       {
            runnning=true;
       }
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("seconds",seconds);
        outState.putBoolean("running",runnning);
        outState.putBoolean("wasRunning",wasRunnning);

    }

    private void runTimer() {
        TextView timeView=findViewById(R.id.textView);
        Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours=seconds/3600;
                int minutes=(seconds%3600)/60;
                int secs=seconds%60;

                String time=String.format(Locale.getDefault(),
                        "%d:%02d:%02d",hours,minutes,secs);
                timeView.setText(time);
                if(runnning)
                    seconds++;
                handler.postDelayed(this,1000);

            }
        });
    }
}