package com.rcube.generalknowldgequiz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.common.collect.Maps;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {

//    private String clientid="231347383423-cl8gga2mvg5n5bc30ts176qeeufpbh0i.apps.googleusercontent.com";
private String clientid="136365600563-is41aaoqjtr87mpkbgfc675bvmm8endv.apps.googleusercontent.com";
    LinearLayoutCompat googleLoginBtn,facebookLoginBtn,twitterKloginBtn;

    GoogleSignInClient mGoogleSignInClient;

     FirebaseAuth mAuth;
// ...
    FirebaseFirestore db;


    public static final int SIGN_IN_with_GOOGLE=3;

    public static final String TAG="TAG Loginactivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        googleLoginBtn=findViewById(R.id.login_with_google);
        facebookLoginBtn=findViewById(R.id.login_with_facebook);


        googleLoginBtn.setOnClickListener(v -> {
           loginWithGoogle();
        });
        facebookLoginBtn.setOnClickListener(v -> {
            Intent intent=new Intent(this,FacebookAuthActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);

        });


        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(clientid)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);



        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        db  = FirebaseFirestore.getInstance();


    }

    private void loginWithGoogle() {
        Intent signinintent=mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signinintent,SIGN_IN_with_GOOGLE);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == SIGN_IN_with_GOOGLE) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);


                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Paper.init(LoginActivity.this);

                            assert user != null;
                            Paper.book().write("name",user.getDisplayName());

                            if (Objects.requireNonNull(task.getResult().getAdditionalUserInfo()).isNewUser()){

                                Map<String,Object> usertostore=new HashMap<>();
                                usertostore.put("name",user.getDisplayName());

                                db.collection("users").add(usertostore).addOnCompleteListener(
                                        new OnCompleteListener<DocumentReference>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                                gotoMainActivity();
                                            }
                                        }
                                ).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });

                            }else
                            {
                                gotoMainActivity();
                            }
                            // Sign in success, update UI with the signed-in user's information



//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
//                            updateUI(null);
                        }
                    }
                });
    }


    private void   gotoMainActivity()
    {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}