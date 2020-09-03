package com.example.touchlistener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.MotionEventCompat;

import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {


    ImageView imageView = null;
    public ArrayList<Integer> image;

    GestureDetectorCompat detectorCompat;
    float x = 0;
    float y = 0;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        image = new ArrayList<>();
        image.add(R.drawable.download);

        image.add(R.drawable.download2);

        image.add(R.drawable.tom1);
        imageView = findViewById(R.id.image);

       imageView.setScaleType(ImageView.ScaleType.MATRIX);


        imageView.setOnTouchListener(new ImageMultiTouch(this));

    }



}
