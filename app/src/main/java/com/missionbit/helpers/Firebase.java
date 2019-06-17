package com.missionbit.helpers;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class Firebase {

    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final FirebaseStorage storage = FirebaseStorage.getInstance();

    private final FirebaseConfig config;

    public Firebase(FirebaseConfig config){
        this.config = config;
    }

    public String saveRecord(String collection, Map<String, Object> data){
        Task<DocumentReference> saveRecordTask = db.collection(collection).add(data);

        DocumentReference docRef = saveRecordTask.getResult();
        return docRef.getId();
    }

    public ArrayList<Map<String, Object>> getRecords(String collection){
        final ArrayList<Map<String, Object>>  data = new ArrayList<>();

        Task<QuerySnapshot> queryTask = db.collection(collection).get()
        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Map<String, Object> item = document.getData();
                        item.put("id", document.getId());
                        data.add(item);
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });

        queryTask.getResult();
        return data;
    }

    public String uploadFile(String name, byte[] data, OnSuccessListener<UploadTask.TaskSnapshot> onSuccess, OnFailureListener onError){
        StorageReference storageRef = storage.getReference();

        StorageReference mountainsRef = storageRef.child(name);

        final StorageReference mountainImagesRef = storageRef.child(String.format("%s/mountains.jpg", this.config.getStoragePath()));

        //        mountainsRef.getName().equals(mountainImagesRef.getName());
        //        mountainsRef.getPath().equals(mountainImagesRef.getPath());


        UploadTask uploadTask = mountainsRef.putBytes(data);
        uploadTask.addOnFailureListener(onError)
                .addOnSuccessListener(onSuccess);

        //        uploadTask.addOnFailureListener(new OnFailureListener() {
        //            @Override
        //            public void onFailure(@NonNull Exception exception) {
        //                // Handle unsuccessful uploads
        //            }
        //        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
        //            @Override
        //            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
        //                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
        //                // ...
        //            }
        //        });

        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                return mountainImagesRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                } else {
                    // Handle failures
                    // ...
                }
            }
        });

        Uri uri = urlTask.getResult();
        return uri.toString();

//        return "";
    }
}
