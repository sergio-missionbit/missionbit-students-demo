package com.missionbit.students;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.missionbit.model.entities.Student;
import com.missionbit.students.databinding.ActivityAboutMeBinding;

public class AboutMeActivity extends AppCompatActivity {
     String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

//        Intent intent = getIntent();
//        message = intent.getStringExtra("message");

        message = String.format("Hello, %s! I need to know your birth date. Cold you tell me it?", State.student.getName());

        final ActivityAboutMeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_about_me);
        binding.setMessage(message);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onSaveClick(View view) {
    }
}
