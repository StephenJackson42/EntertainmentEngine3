package com.stephenjackson.entertainmentengine;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                launch();
            }
        };
        Handler h = new Handler();
        h.postDelayed(r, 3000);
    }

    private void launch(){
        Intent i = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(i);
        finish();
    }
}
