package com.rcube.generalknowldgequiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.Animator;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;


public class QuestionActivity extends AppCompatActivity {

    private TextView question;
    private TextView noIndicator;
    private TextView timerIndicator;
    private FloatingActionButton bookmarks;
    private LinearLayout optionContainer;
    private Button shareButton,nextButton;
    private int count=0;
    private List<QuestionModal> questionModalList;
    private int position=0;
    private  int score=0;

    private String category;
    private int setNo;
    CountDownTimer countDownTimer;


    private FloatingActionButton bookMarksBtn;

    private Dialog loadingDialog;

    private TextView categoryTV;

    MediaPlayer mp;

    FirebaseFirestore db;
    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Toolbar toolbar=findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        Paper.init(this);

        question=findViewById(R.id.questionTextView);
        optionContainer=findViewById(R.id.linearLayout2);
        nextButton=findViewById(R.id.nextButton);
        noIndicator=findViewById(R.id.textIndicator);
        timerIndicator=findViewById(R.id.timeIndicator);





        Intent intent=getIntent();
        category=intent.getStringExtra("category");
        categoryTV=findViewById(R.id.categoryTV);
        categoryTV.setText(" Quiz question in "+category);


        db=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        String uuid=mAuth.getUid();

        bookMarksBtn=findViewById(R.id.bookmarksButton);
        bookMarksBtn.setOnClickListener(v -> {

            bookMarksBtn.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(255,50,50)));
            BookmarksModal modal=new BookmarksModal(uuid,questionModalList.get(position).getQuestion(),questionModalList.get(position).getCorrectAnswer());
            List<BookmarksModal> bookmarksModalList=new ArrayList<>();
            bookmarksModalList=Paper.book().read(Constants.BOOKMARKS_LIST,bookmarksModalList);

            boolean exist=false;
            for (BookmarksModal modal1:bookmarksModalList) {
                if (modal.answer.equals(modal1.answer) && modal.question.equals(modal1.question) && modal.uuid.equals(modal1.uuid))
                {
                    exist=true;
                    break;
                }
            }
            if (exist)
            {
                Toast.makeText(QuestionActivity.this, "Question already in the bookmarks", Toast.LENGTH_SHORT).show();

            }
            else{
                bookmarksModalList.add(modal);
                Paper.book().write(Constants.BOOKMARKS_LIST,bookmarksModalList);
                Toast.makeText(QuestionActivity.this, "Added to bookmarks bookmarks", Toast.LENGTH_SHORT).show();

            }
        });

        questionModalList=new ArrayList<>();





            questionModalList=Constants.getQuestionModelList(category);

        for (int i = 0; i < 4; i++) {

            optionContainer.getChildAt(i).setOnClickListener(v -> checkAnswer(((Button) v)));
        }

        playAnim(question, 0, questionModalList.get(position).getQuestion());
        countDownTimer();

        nextButton.setOnClickListener(v -> {
          nextClick();
        });


    }

    private void nextClick() {
        cancelTimer();
        count = 0;
//                nextButton.setEnabled(false);
//                nextButton.setAlpha(0.7f);
        nextButton.setVisibility(View.INVISIBLE);

        position++;

        if (position == questionModalList.size()) {
            //score activity
            Intent scoreIntent = new Intent(getApplicationContext(), ScoreActivity.class);
            scoreIntent.putExtra("score", String.valueOf(score));
            scoreIntent.putExtra("total",String.valueOf(questionModalList.size()));
            scoreIntent.putExtra("category",category);

            mp.release();
            startActivity(scoreIntent);
            finish();
        } else {
            enableOption(true);
            playAnim(question, 0, questionModalList.get(position).getQuestion());
            countDownTimer();
        }


        count = 0;


    }

    private void cancelTimer() {
        countDownTimer.cancel();
        countDownTimer=null;
    }

    private void  playAnim(final View view , final int value, final String data){
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(100).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

                if (value==0 && count<4){
                    String option="";

                    if (count==0){
                        option=questionModalList.get(position).getOptionA();
                    }
                    else if (count==1)
                    {
                        option=questionModalList.get(position).getOptionB();
                    }
                    else if (count==2)
                    {
                        option=questionModalList.get(position).getOptionC();
                    }
                    else if (count==3)
                    {
                        option=questionModalList.get(position).getOptionD();
                    }
                    playAnim(optionContainer.getChildAt(count),0,option);
                    count++;
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //change data here and make view visibe


                if (value==0){

                    try {
                        ( (TextView)view).setText(data);
                        String position1=String.valueOf(position+1);
                        noIndicator.setText(position1+"/"+questionModalList.size());
                        timerIndicator.setText("0:30 sec");

                    }catch (Exception e){

                    }

                    view.setTag(data);

                    playAnim(view,1,data);

                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void countDownTimer() {
         countDownTimer=new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerIndicator.setText("0: "+millisUntilFinished / 1000+" sec");
            }

            @Override
            public void onFinish() {
                nextClick();
            }
        };
        countDownTimer.start();
    }

    private void checkAnswer(Button slectedOption){
        enableOption(false);
//        nextButton.setEnabled(true);
//        nextButton.setAlpha(1);
        nextButton.setVisibility(View.VISIBLE);
        if (slectedOption.getText().toString().equals(questionModalList.get(position).getCorrectAnswer())){
            //correct
            slectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
            playCorrect();
            score++;
        }
        else
        {
            //incorrect
            playIncorrectCorrect();
            slectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF0000")));
            Button correctButton=  (Button)optionContainer.findViewWithTag(questionModalList.get(position).getCorrectAnswer());
            correctButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
        }

    }


    private void enableOption(boolean b){
        for (int i=0;i<4;i++){

            optionContainer.getChildAt(i).setEnabled(b);
            if (b){
                optionContainer.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#989898")));
            }
        }


    }

    public void playCorrect(){
        mp=MediaPlayer.create(this,R.raw.correct);
        mp.start();
//        new Handler().postDelayed(() -> {
//            mp.stop();
//            mp.reset();
//        },4000);
    }

    public void playIncorrectCorrect(){
        mp=MediaPlayer.create(this,R.raw.incorrect);
        mp.start();

//        new Handler().postDelayed(() -> {
//            mp.stop();
//            mp.reset();
//        },4000);
    }

    @Override
    public void onBackPressed() {
        new   AlertDialog.Builder(QuestionActivity.this).setTitle("Cancel?")
                .setMessage("Do you want to exit from quiz?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       QuestionActivity.super.onBackPressed();

                    }
                })
                .setNegativeButton("No",null)
                .setCancelable(true)
                .show();
    }
}
