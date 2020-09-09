package com.example.time;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.example.time.databinding.ActivityMainBinding;
import com.example.time.databinding.MainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    private static String TAG = "YEAD_";
    ActivityMainBinding activityMainBinding;
    MainBinding mainBinding;
    TimeViewModel timeViewModel;
    FrameLayout frameLayout1,frameLayout2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        final View view = activityMainBinding.getRoot();
        timeViewModel = new ViewModelProvider(this).get(TimeViewModel.class);
        mainBinding = MainBinding.inflate(getLayoutInflater());
        final View view2 = mainBinding.getRoot();
        setContentView(view2);
        frameLayout1 = mainBinding.frame1;
        frameLayout2 = mainBinding.frame2;

        getSupportFragmentManager().beginTransaction().replace(R.id.frame1,new Fragment1()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame2,new fragment2()).commit();




      /*  timeViewModel.get_time().observe(this, new Observer<String>() {

            @Override
            public void onChanged(String s) {
                fetch_date(s);
             //   Log.d(TAG, "onChanged: " + s);

            }


        });*/
    /*    activityMainBinding.barisal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeViewModel.update_time("ASIA/DHAKA");
            }
        });
        activityMainBinding.Dhaka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeViewModel.update_time("EUROPE/LONDON");
            }
        });
        activityMainBinding.khulna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeViewModel.update_time("america/New_York");
            }
        });*/
    }

    void fetch_date(String s) {

        if(s==null)return;
        try {

            JSONObject jsonObject = new JSONObject(s);
            String unixTime = jsonObject.getString("datetime");
          //  Log.d(TAG, "fetch_date: "+unixTime);
            String date =unixTime.substring(0,10);
            String time = unixTime.substring(11,19);
            Log.d(TAG, "fetch_date: date "+date+" "+time);
            activityMainBinding.clockTime.setText(date+"\n"+time);


        } catch (JSONException e) {
            Log.d(TAG, "fetch_date: "+e.toString());
        }

    }

}