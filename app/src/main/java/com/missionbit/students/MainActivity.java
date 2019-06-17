package com.missionbit.students;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.missionbit.helpers.Firebase;
import com.missionbit.model.entities.Student;
import com.missionbit.model.providers.StudentProvider;
import com.missionbit.model.providers.StudentsFirebaseConfig;
import com.missionbit.students.databinding.ActivityMainBinding;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {


    Student student = new Student();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        student.setName("Your name");
        student.setFunFact("Your fun fact");

        final ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setStudent(student);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap bmp = (Bitmap) data.getExtras().get("data");

            ImageView iv = findViewById(R.id.studentImg);
            iv.setImageBitmap(bmp);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            StudentProvider sp = new StudentProvider();
            String url = sp.UploadPhoto(byteArray);
            System.out.println(url);
        }
    }

    public void onSaveClick(View view) {

    }

    public void onPhotoClick(View view){
        System.out.println("Photo");
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

}
