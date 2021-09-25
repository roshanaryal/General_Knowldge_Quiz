package com.rcube.generalknowldgequiz;

import static com.rcube.generalknowldgequiz.MainActivity.millisToDate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class HistoryActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseFirestore db;

    ListView mListView;
    List<String> mStringList;
    List<Map<String, Object>> mMapList;
    
    public static final String TAG="TAG";

    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        mToolbar=findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("History");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mStringList=new ArrayList<>();
        mMapList=new ArrayList<>();

        db=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();

        mListView=findViewById(R.id.listview);


        // Create a reference to the cities collection
        CollectionReference histref = db.collection("history");
        histref.orderBy("date", Query.Direction.ASCENDING);

        String uuid=mAuth.getUid();
        Log.d(TAG, "onCreate: "+uuid);
        List<Map<String, Object>> hashMapList=new ArrayList<>();

        ArrayAdapter adapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mStringList);
        mListView.setAdapter(adapter);

        Query query=histref.whereEqualTo("uuid",uuid).orderBy("date", Query.Direction.DESCENDING);
        query.get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
//
                            Log.d(TAG, "onCreate:before map ");
                            Map<String, Object> hashMap=document.getData();
                            mMapList.add(hashMap);
//
                            Timestamp timestamp= (Timestamp) hashMap.get("date");
                            String date= DateFormat.getDateTimeInstance().format(timestamp.toDate().getTime());

                            String s="You have taken quiz in "+hashMap.get("category")+" at "+date
                                    +" and scored "+hashMap.get("score")+" out of "+hashMap.get("total");
                            mStringList.add(s);



                            adapter.notifyDataSetChanged();
                            Log.d(TAG, "onCreate: after add to list");


                            mListView.setOnItemClickListener((parent, view, position, id) -> new   AlertDialog.Builder(HistoryActivity.this).setTitle("Take again.")
                                    .setMessage("Do you want to take quiz in "+mMapList.get(position).get("category"))
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent=new Intent(HistoryActivity.this,QuestionActivity.class);
                                            intent.putExtra("category",(String) mMapList.get(position-1).get("category"));
                                            parent.getContext().startActivity(intent);
                                            finish();
                                        }
                                    })
                                    .setNegativeButton("No",null)
                                    .setCancelable(true)
                                    .show());
                        }
//
                    } else {
                        Log.w("TAG", "Error getting documents.", task.getException());
                    }
                });

        for (String s:mStringList
             ) {
            Log.d("TAG List", s);
        }
        Log.d("TAG", "onCreate: "+mStringList.size());



    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}