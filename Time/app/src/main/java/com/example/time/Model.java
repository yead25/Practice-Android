package com.example.time;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Model {

    private static String TAG = "YEAD";
    MutableLiveData<String> time = new MutableLiveData<>();
    private static Model model;

    public static Model get_instance() {
        if (model == null)
            model = new Model();

        return model;
    }

    public Model() {
        new CalculateRandom().execute("ASIA/DHAKA");
    }

    public MutableLiveData<String> getTime() {
        return time;
    }

    public void setTime(String T) {
        time.setValue(T);
    }

    public void Update_time(String n) {
        new CalculateRandom().execute(n);
    }

    private class CalculateRandom extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {
            String ret = null;
            String city = strings[0];

            Log.d(TAG, "doInBackground: City"+city);
            try {


                URL url = new URL("https://worldtimeapi.org/api/timezone/"+city);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    ret = stringBuilder.toString();
                } finally {
                    urlConnection.disconnect();
                }
            } catch (IOException e) {
                Log.d(TAG, "doInBackground: " + e.toString());
            }

            return ret;
        }

        @Override
        protected void onPostExecute(String aString) {

            setTime(aString);
            Log.d(TAG, "onPostExecute: " + aString);

        }
    }

}
