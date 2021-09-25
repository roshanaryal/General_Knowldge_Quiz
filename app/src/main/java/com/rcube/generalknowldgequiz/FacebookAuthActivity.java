package com.rcube.generalknowldgequiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import io.paperdb.Paper;

public class FacebookAuthActivity extends LoginActivity {

    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
//        facebookLoginBtn.setReadPermissions("email", "public_profile");
        LoginManager.getInstance().logInWithReadPermissions(this, Collections.singletonList("public_profile"));


        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        handleFacebookAccessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Paper.init(FacebookAuthActivity.this);

                            assert user != null;
                            Paper.book().write("name", user.getDisplayName());

                            if (Objects.requireNonNull(task.getResult().getAdditionalUserInfo()).isNewUser()) {

                                Map<String, Object> usertostore = new HashMap<>();
                                usertostore.put("name", user.getDisplayName());

                                db.collection("users").add(usertostore).addOnCompleteListener(
                                        new OnCompleteListener<DocumentReference>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                                updateUi(user);                                            }
                                        }
                                ).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });
                            } else {
//                                // If sign in fails, display a message to the user.
//                                Log.w(TAG, "signInWithCredential:failure", task.getException());
//                                Toast.makeText(FacebookAuthActivity.this, "Authentication failed.",
//                                        Toast.LENGTH_SHORT).show();
                                updateUi(user);

                            }
                        }
                        else{
                            Toast.makeText(FacebookAuthActivity.this, "Error while login", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void updateUi(FirebaseUser user) {
        startActivity(new Intent(this,MainActivity.class));
        finish();

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}