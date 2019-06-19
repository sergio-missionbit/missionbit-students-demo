package com.missionbit.students;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.missionbit.students.databinding.ActivityAboutMeBinding;

public class AboutMeActivity extends AppCompatActivity {
     String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        Intent intent = getIntent();
        message = intent.getStringExtra("message");

        final ActivityAboutMeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_about_me);
        binding.setMessage(message);
    }
}
