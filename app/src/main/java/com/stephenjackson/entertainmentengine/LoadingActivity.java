package com.stephenjackson.entertainmentengine;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import static com.stephenjackson.entertainmentengine.SearchActivity.entry;

public class LoadingActivity extends AppCompatActivity {

    private TextView tv;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        tv = (TextView)findViewById(R.id.textView);
        tv.setText("Searching");
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
        Intent i = new Intent(getApplicationContext(), ResultsActivity.class);
        startActivity(i);
        finish();
    }
}
