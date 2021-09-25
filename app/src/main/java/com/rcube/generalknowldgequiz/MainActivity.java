package com.rcube.generalknowldgequiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.ServerTimestamp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {
    private GridView mGridView;

    private TextView nameTV;

    private TextView historyTv1;
    private TextView historyTv2;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    private Button viewallBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Paper.init(this);

        //getand set name
        nameTV=findViewById(R.id.welcome_id);
        String welcomeTxt="Welcome "+Paper.book().read("name","");
        nameTV.setText(welcomeTxt);
        mGridView=findViewById(R.id.grid_layout);
        mGridView.setColumnWidth(2);


        List<GridModal> gridModalList=new ArrayList<>();
        gridModalList.add(new GridModal("Technology"));
        gridModalList.add(new GridModal("Sports"));
        gridModalList.add(new GridModal("General Awareness"));
        gridModalList.add(new GridModal("Current Affairs"));
//        gridModalList.add(new GridModal("History"));

        GridAdapter gridAdapter=new GridAdapter(gridModalList);
        mGridView.setAdapter(gridAdapter);

        historyTv1=findViewById(R.id.history_1_tv);
        historyTv2=findViewById(R.id.history_2_tv);

        viewallBtn=findViewById(R.id.view_all_btn);
        viewallBtn.setOnClickListener(v -> {
            startActivity(new Intent(this,HistoryActivity.class));
        });

        mAuth=FirebaseAuth.getInstance();

        String uuid=mAuth.getUid();

        db=FirebaseFirestore.getInstance();

        // Create a reference to the cities collection
        CollectionReference histref = db.collection("history");


//        histref.orderBy("date", Query.Direction.DESCENDING);
        Query query=histref.whereEqualTo("uuid",uuid).orderBy("date", Query.Direction.ASCENDING).limit(2);
        List<Map<String, Object>> hashMapList=new ArrayList<>();

        query.get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            hashMapList.add(document.getData());


                            Log.d("TAG", document.getId() + " => " + document.getData());
                        }
                        if (hashMapList.size()==0)
                        {
                            historyTv1.setText("You havenot take any quiz");
                        }
                        else{
                            int i=0;
                            for (Map<String, Object> hashMap:hashMapList) {
//                                    String date= millisToDate( Long.parseLong((String)hashMap.get("date")));
                                Timestamp timestamp= (Timestamp) hashMap.get("date");
                                String date=DateFormat.getDateTimeInstance().format(timestamp.toDate().getTime());

                                String s="You have taken quiz in "+hashMap.get("category")+" at "+date
                                        +" and scored "+hashMap.get("score")+" out of "+hashMap.get("total");
                                if (i==0)
                                {
                                    historyTv1.setText(s);
                                    i=1;
                                }
                                else{
                                    historyTv2.setText(s);
                                }
                            }
                        }
                    } else {
                        Log.w("TAG", "Error getting documents.", task.getException());
                    }
                });



    }

    public static String millisToDate(long millis){
        DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
        // Creating date from milliseconds
        // using Date() constructor
        Date result = new Date(millis);
        //You can use DateFormat.LONG instead of SHORT
        return simple.format(result);

    }

    public void goBookmarks(View view) {
        startActivity(new Intent(this,BookmarksActivity.class));
    }

    public void logOut(View view) {
        mAuth.signOut();
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
        finish();
    }


    boolean backpressonce=false;
    @Override
    public void onBackPressed() {
        if (backpressonce)
        {
            super.onBackPressed();
        }else
        {
            Toast.makeText(MainActivity.this, "Press back again to exist", Toast.LENGTH_SHORT).show();
            backpressonce=true;
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    backpressonce=false;
                }
            },2000);
        }
    }
}