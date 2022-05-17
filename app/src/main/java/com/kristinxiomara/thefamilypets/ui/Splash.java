package com.kristinxiomara.thefamilypets.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.kristinxiomara.thefamilypets.R;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    TimerTask tarea1 = new TimerTask() {
        @Override
        public void run() {
            Intent intent = new Intent(Splash.this, Menu.class);
            startActivity(intent);
            finish();

            Timer timer = new Timer();
            timer.schedule(tarea1, 400);

        }
    };

}