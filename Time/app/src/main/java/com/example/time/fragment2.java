package com.example.time;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.time.databinding.Frame2Binding;

public class fragment2 extends Fragment {



    Frame2Binding frame2Binding;
    TimeViewModel timeViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        frame2Binding  = Frame2Binding.inflate(inflater,container,false);

        View layout = frame2Binding.getRoot();
        timeViewModel = new ViewModelProvider(requireActivity()).get(TimeViewModel.class);
        frame2Binding.barisal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeViewModel.update_time("ASIA/DHAKA");
            }
        });
        frame2Binding.Dhaka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeViewModel.update_time("EUROPE/LONDON");
            }
        });
        frame2Binding.khulna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeViewModel.update_time("america/New_York");
            }
        });

        return layout;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }
}
