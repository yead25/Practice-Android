package com.example.time;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TimeViewModel extends ViewModel {

    private MutableLiveData<String> date;
    private Model model;
    public TimeViewModel(){
        model = Model.get_instance();
        date = model.getTime();
    }
    public MutableLiveData<String> get_time(){
        return date;
    }
    public void update_time(String city){
        model.Update_time(city);
    }
}
