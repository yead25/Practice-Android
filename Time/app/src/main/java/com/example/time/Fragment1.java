package com.example.time;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.time.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

public class Fragment1 extends Fragment {


    public static String TAG ="YEAD";
    ActivityMainBinding activityMainBinding;
    TimeViewModel timeViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        activityMainBinding = ActivityMainBinding.inflate(inflater,container,false);

        View layout = activityMainBinding.getRoot();
        timeViewModel = new ViewModelProvider(requireActivity()).get(TimeViewModel.class);
        timeViewModel.get_time().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                fetch_date(s);
            }
        });




       return layout;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

    }

    void fetch_date(String s) {

        if(s==null)
            return ;

        try {
            JSONObject jsonObject = new JSONObject(s);
            String unixTime = jsonObject.getString("datetime");
            String timeZone = jsonObject.getString("timezone");
            //  Log.d(TAG, "fetch_date: "+unixTime);
            String date =unixTime.substring(0,10);
            String time = unixTime.substring(11,19);
            Log.d(TAG, "fetch_date: date "+date+" "+time);
            activityMainBinding.clockTime.setText(timeZone+"\n"+"Date: "+date+"\n"+"Time: "+time);


        } catch (JSONException e) {
            Log.d(TAG, "fetch_date: "+e.toString());
        }

    }
}
