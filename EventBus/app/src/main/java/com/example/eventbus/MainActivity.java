package com.example.eventbus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.active);
        textView = findViewById(R.id.text1);
        EventBus.getDefault().postSticky(new MessageEvent("paisi"));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
    }
    private void activit()
    {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe( sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event) {
        Toast.makeText(this, "Hey, my message" + event.getMessage(), Toast.LENGTH_SHORT).show();
        textView.setText(event.getMessage());

    }


}
