package com.missionbit.model.entities;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.missionbit.students.BR;

public class Student extends BaseObservable {
    private String name;
    private String funFact;
    private String photoUrl;

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
}

