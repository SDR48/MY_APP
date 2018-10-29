package com.example.sdr.my_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        logolaunch logs = new logolaunch();
        logs.start();

    }
    private class logolaunch extends Thread{
        public void run(){
            try{
                sleep(1000);
            }catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            Intent i = new Intent(HomeActivity.this,MainActivity.class);
            startActivity(i);
            HomeActivity.this.finish();
        }
    }

}
