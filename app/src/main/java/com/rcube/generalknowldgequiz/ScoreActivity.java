package com.rcube.generalknowldgequiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import io.paperdb.Paper;

public class ScoreActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseFirestore db;

    String score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        score=getIntent().getStringExtra("score");


       TextView scoreTV= findViewById(R.id.scoreText);
       scoreTV.setText("Your score : "+score);

       mAuth=FirebaseAuth.getInstance();

       db=FirebaseFirestore.getInstance();

       Intent intent=getIntent();

       String total=intent.getStringExtra("total");
       String score=intent.getStringExtra("score");
       String category=intent.getStringExtra("category");


       String uuid=mAuth.getUid();



        String date=String.valueOf(new Date().getTime());

        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("total",total);
        hashMap.put("category",category);
        hashMap.put("uuid",uuid);
        hashMap.put("date", FieldValue.serverTimestamp());
        hashMap.put("score",score);

        CollectionReference reference=db.collection("history");
        reference.document(uuid);
        reference.add(hashMap)
//        db.collection("history")
//                .document(uuid)
//                .add(hashMap)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Log.d("TAG", "onComplete: ");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("TAG", "onFailure: ");

            }
        });

    }

    public void goHome(View view) {
           onBackPressed();
    }
}