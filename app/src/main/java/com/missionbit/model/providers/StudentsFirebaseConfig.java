package com.missionbit.model.providers;

import com.missionbit.helpers.FirebaseConfig;

public class StudentsFirebaseConfig implements FirebaseConfig {

    private String storagePath = "students";

    @Override
    public String getStoragePath() {
        return this.storagePath;
    }


}
