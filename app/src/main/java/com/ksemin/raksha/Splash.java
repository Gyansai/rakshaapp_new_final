package com.ksemin.raksha;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    public  int time = 3500;
    public static boolean isfirstrun;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

         isfirstrun = getSharedPreferences("preferences",MODE_PRIVATE).getBoolean("isfirstrun",true);
         if (isfirstrun){
             time=3500;
             getSharedPreferences("preferences",MODE_PRIVATE).edit().putBoolean("isfirstrun",false).commit();
             final Handler handler = new Handler();
             handler.postDelayed(new Runnable() {
                 @Override
                 public void run() {
                     // call the introactivity after 3.5s = 3500ms.
                     Intent intent = new Intent(Splash.this, IntroActivity.class);
                     intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                     startActivity(intent);
                 }
             }, time);
         }
         else{

             time = 1500;
             final Handler handler = new Handler();
             handler.postDelayed(new Runnable() {
                 @Override
                 public void run() {
                     // call the introactivity after 3.5s = 3500ms.
                     Intent intent = new Intent(Splash.this, TypeActivity.class);
                     intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                     startActivity(intent);
                 }
             }, time);
         }



    }
}
