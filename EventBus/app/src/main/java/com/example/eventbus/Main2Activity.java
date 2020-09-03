package com.example.eventbus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
     final EditText textView= findViewById(R.id.text2);
        Button button = findViewById(R.id.send);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String get = textView.getText().toString();
                EventBus.getDefault().postSticky(new MessageEvent(get));
               finish();

            }
        });
    }


}
