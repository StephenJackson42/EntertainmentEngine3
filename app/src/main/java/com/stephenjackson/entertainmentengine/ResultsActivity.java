package com.stephenjackson.entertainmentengine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.TextureView;
import android.widget.TextView;
import static com.stephenjackson.entertainmentengine.SearchActivity.entry;

public class ResultsActivity extends AppCompatActivity {
    private TextView tView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        tView = (TextView) findViewById(R.id.textView);
        tView.setText(entry);
    }
}
