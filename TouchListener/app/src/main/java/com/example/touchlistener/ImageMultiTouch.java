package com.example.touchlistener;

import android.graphics.Matrix;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageMultiTouch implements View.OnTouchListener {
    final static float MIN_DIST = 200;
    static float eventDistance = 0;
    static float centerX = 0, centerY = 0;
    int now = 0;
    private float d = 0;

    float last_dist;

    public static final String DEBUG_TAG = "touch";
    public static final String TAG = "touch";

    public static final int NONE = 0;
    public static final int DRAG = 1;
    public static final int ZOOM = 2;
    int touchState = NONE;

    MainActivity mainActivity;

    ImageMultiTouch(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }



    Matrix matrix = new Matrix();
    Matrix eventMat = new Matrix();



    @Override
    public boolean onTouch(View v, MotionEvent event) {

        ImageView imageView = (ImageView) v;
        imageView.setScaleType(ImageView.ScaleType.MATRIX);
        // Log.d(TAG, "evenMat: " + eventMat.toString());
        // Log.d(TAG, "Mat: " + matrix.toString());
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                touchState = DRAG;
                Log.d(TAG, "onTouch: ");
                centerX = event.getX(0);
                centerY = event.getY(0);
                eventMat.set(matrix);
                // Log.d(TAG, "evenMat: " + eventMat.toString());
                //   Log.d(TAG, "Mat: " + matrix.toString());
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                Log.d(TAG, "onTouch: 2");
                eventDistance = calcDistance(event);
                calcMidpoint(event);
                if (eventDistance > MIN_DIST) {
                    eventMat.set(matrix);
                    touchState = ZOOM;
                    last_dist = 0;
                }

                d = rotation(event);
                //   Log.d(TAG, "evenMat: " + eventMat.toString());
                //   Log.d(TAG, "Mat: " + matrix.toString());
                break;

            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onTouch: move");
                if (touchState == DRAG) {
                    float x = event.getX(0) - centerX;
              //      Log.d(TAG, "onTouch: " + x);
                    if (x < -300) {
                        now = Math.min(now + 1, 2);
                        imageView.setImageResource(mainActivity.image.get(now));
                        imageView.setScaleType(ImageView.ScaleType.MATRIX);
                        centerX = event.getX(0);
                        matrix.reset();
                        eventMat.reset();
                        touchState = NONE;
                    } else if (x > 300) {
                        now = Math.max(now - 1, 0);
                        imageView.setImageResource(mainActivity.image.get(now));

                        imageView.setScaleType(ImageView.ScaleType.MATRIX);
                        centerX = event.getX(0);
                        matrix.reset();
                        eventMat.reset();
                        touchState = NONE;
                    } else {
                        matrix.set(eventMat);
                        matrix.postTranslate(event.getX(0) - centerX,
                                event.getY(0) - centerY);
                    }
                } else if (touchState == ZOOM) {
                    matrix.set(eventMat);
                    float dist = calcDistance(event);


                    if (dist > MIN_DIST) {


                        float scale = dist / eventDistance;
                        matrix.postScale(scale, scale, centerX, centerY);

                    }
                    float X = (event.getX(1) + event.getX(0)) / 2;
                    float Y = (event.getY(1) + event.getY(0)) / 2;

                    matrix.postTranslate(X - centerX, Y - centerY);


                    if ( (event.getPointerCount() == 2 || event.getPointerCount() == 3)) {

                        float root = rotation(event);
                        float rotate = root - d;
                        float values[] = new float[9];
                        matrix.getValues(values);
                        float tx = values[2];
                        float ty = values[5];
                        float sx = values[0];
                        float sy = values[4];
                        float xc = (float) (imageView.getWidth() / 2) * sx;
                        float yc = (float) (imageView.getHeight() / 2) * sy;
                        //imageView.setRotation(rotate);
                        Log.d("Rott", "" + d + " " + root + " " + rotate);
                        matrix.postRotate(rotate, xc + tx, yc + ty);
                        Log.d("rooooot", "onTouch: " + rotate);
                    }
                }

                imageView.setImageMatrix(matrix);
                // Log.d(TAG, "evenMat: " + eventMat.toString());
                // Log.d("after", "Mat: after " + matrix.toString());
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                touchState = NONE;
                break;

        }
        //Log.d(TAG, "evenMat: " + eventMat.toString());
        // Log.d(TAG, "Mat: " + matrix.toString());

        return true;
    }

    private float calcDistance(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getX(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    private void calcMidpoint(MotionEvent event) {

        centerX = (event.getX(0) + event.getX(1)) / 2;
        centerY = (event.getY(0) + event.getY(1)) / 2;

    }

    private float rotation(MotionEvent event) {
        double delta_x = (event.getX(0) - event.getX(1));
        double delta_y = (event.getY(0) - event.getY(1));

        double radians = Math.atan(delta_y / delta_x);
        return (float) Math.toDegrees(radians);

    }
}
