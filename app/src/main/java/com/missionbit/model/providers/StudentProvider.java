
package com.missionbit.model.providers;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.UploadTask;
import com.missionbit.helpers.Firebase;
import com.missionbit.model.entities.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StudentProvider {

    final Firebase firebase = new Firebase(new StudentsFirebaseConfig());

    public String Save(Student student){
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("name",student.getName());
        data.put("fun_fact",student.getFunFact());
        data.put("photo_url",student.getPhotoUrl());
        String studentId = firebase.saveRecord("students", data);
        return studentId;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ArrayList<Student> read(){
        ArrayList<Student> students = new ArrayList<Student>();
        ArrayList<Map<String, Object>> records = firebase.getRecords("students");
        for (Map<String, Object> document : records) {


            String name = document.get("name").toString();
            String funFact = document.get("fun_fact").toString();
            String photoUrl = document.get("photo_url").toString();

            Student student = new Student();
            student.setName(name);
            student.setFunFact(funFact);
            student.setPhotoUrl(photoUrl);

            students.add(student);
        }
        return students;
    }

    public String UploadPhoto(byte[] imageBytes){
        String url = firebase.uploadFile("test", imageBytes, new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                System.out.println("File has been uploaded");
            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println(String.format("Error while uploading file. %s", e.getMessage()));
            }
        });
        return  url;
    }
}
