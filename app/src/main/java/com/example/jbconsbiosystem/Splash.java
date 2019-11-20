package com.example.jbconsbiosystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.jbconsbiosystem.Shared.SharedPreferenceEdit;

public class Splash extends AppCompatActivity {


    private final int SPLASH_DISPLAY_LENGTH = 1000;
    private SharedPreferenceEdit sharedPreferenceEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedPreferenceEdit=new SharedPreferenceEdit(Splash.this);


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */

                String login=sharedPreferenceEdit.GetUserLogin();
                if (login.equals("login")){
                    Intent mainIntent = new Intent(Splash.this,Dashboard.class);
                    Splash.this.startActivity(mainIntent);
                    Splash.this.finish();

                }else{
                    Intent mainIntent = new Intent(Splash.this,Login.class);
                    Splash.this.startActivity(mainIntent);
                    Splash.this.finish();
                }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
