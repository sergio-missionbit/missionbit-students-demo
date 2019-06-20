package com.missionbit.model.entities;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.missionbit.students.BR;

import java.time.LocalDate;
import java.util.Date;

public class Student extends BaseObservable {
    private String name;
    private String funFact;
    private String photoUrl;
    private Date birthdate;


    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }
    @Bindable
    public String getFunFact() {
        return funFact;
    }

    public void setFunFact(String funFact) {
        this.funFact = funFact;
        notifyPropertyChanged(BR.funFact);
    }
    @Bindable
    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
        notifyPropertyChanged(BR.photoUrl);
    }

    @Bindable
    @RequiresApi(api = Build.VERSION_CODES.O)
    public int getAge() {

        LocalDate now = LocalDate.now();
        int currentYear = now.getYear();

        int age = currentYear - birthdate.getYear();
        return age;
    }

    @Bindable
    public Date getBirthdate (){
        return birthdate;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
        notifyPropertyChanged(BR.birthdate);

    }
}

