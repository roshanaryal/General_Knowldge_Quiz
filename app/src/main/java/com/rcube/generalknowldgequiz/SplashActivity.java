package com.rcube.generalknowldgequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mAuth=FirebaseAuth.getInstance();
        Intent intent;



        if (mAuth.getCurrentUser()!=null)
        {
            intent=new Intent(this,MainActivity.class);
        }else
        {
            intent=new Intent(this,LoginActivity.class);

        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        },1000);

    }
}