package com.rcube.generalknowldgequiz;

import static com.rcube.generalknowldgequiz.MainActivity.millisToDate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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

import io.paperdb.Paper;

public class BookmarksActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseFirestore db;

    ListView mListView;
    List<String> mStringList;
    List<Map<String, Object>> mMapList;

    public static final String TAG="TAG";
    List<BookmarksModal> bookmarksModalList;

    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        mToolbar=findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Bookmarks");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mStringList=new ArrayList<>();
        mMapList=new ArrayList<>();

        db=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();

        mListView=findViewById(R.id.listview);


        // Create a reference to the cities collection
        String uuid=mAuth.getUid();
        CollectionReference histref = db.collection("bookmarks");

        List<Map<String, Object>> hashMapList=new ArrayList<>();

        ArrayAdapter adapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mStringList);
        mListView.setAdapter(adapter);

        Paper.init(this);
        bookmarksModalList=new ArrayList<>();
        bookmarksModalList= Paper.book().read(Constants.BOOKMARKS_LIST,bookmarksModalList);
        for (BookmarksModal m:bookmarksModalList) {
            mStringList.add("Question : "+ m.getQuestion()+"\n Answer: "+m.getAnswer());
        }



        Log.d(TAG, "onCreate: after add to list");
        mListView.setOnItemClickListener((parent, view, position, id) -> new   AlertDialog.Builder(BookmarksActivity.this).setTitle("Delete?")
                .setMessage("Do you want delete selected item")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        bookmarksModalList.remove(position);
                        mStringList.remove(position);

                        Paper.book().write(Constants.BOOKMARKS_LIST,bookmarksModalList);
                        adapter.notifyDataSetChanged();

                    }
                })
                .setNegativeButton("No",null)
                .setCancelable(true)
                .show());



    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}