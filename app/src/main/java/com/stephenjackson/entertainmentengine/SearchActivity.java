package com.stephenjackson.entertainmentengine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
/**
 * Created by StephenJackson on 11/10/2016.
 */

public class SearchActivity extends AppCompatActivity {
    private EditText editText;
    public static String entry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.search);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                entry = editText.getText().toString();
                boolean handled =  false;
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    sendMessage();
                    handled = true;}
                return handled;
            }
        });
    }

    private void sendMessage() {
        Intent i = new Intent(getApplicationContext(), LoadingActivity.class);
        startActivity(i);
    }
}
