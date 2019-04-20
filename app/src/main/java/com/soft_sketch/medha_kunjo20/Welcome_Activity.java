package com.soft_sketch.medha_kunjo20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Welcome_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread td = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                goToNextActivity();
            }
        });
        td.start();

    }

    private void goToNextActivity() {
        Intent intent = new Intent(this,Catagory_Activity.class);
        startActivity(intent);
    }

}
