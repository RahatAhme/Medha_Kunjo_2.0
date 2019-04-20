package com.soft_sketch.medha_kunjo20;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class QuessActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final long COUNTDOWN_TIMER = 20000;


    private TextView scoreET,quesNoET,timeET,quesET;
    private RadioGroup radioGroup;
    private RadioButton opt1,opt2,opt3,opt4;
    private Button nextBtn;
    private ColorStateList textColor;
    public ColorStateList timerColor;

    private List<Questions> questionsList;
    private int totalScore;
    private int currentQuesNo;
    private int totalQuesNo;
    private String selectedCategory="";
    private String  selectedStrength="";

    private Questions currentQues;
    private Boolean answered;

    private CountDownTimer countDownTimer;
    private long timeLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quess);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle intentBundle = getIntent().getExtras();
        if (!intentBundle.isEmpty()){
            selectedCategory = intentBundle.getString("category");
            selectedStrength = intentBundle.getString("strength");
        }
        Toast.makeText(this, selectedCategory+"\n"+selectedStrength, Toast.LENGTH_SHORT).show();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        quesET = findViewById(R.id.ques_tv_ID);
        scoreET = findViewById(R.id.score_tv_ID);
        quesNoET = findViewById(R.id.quesNo_tv_ID);
        radioGroup = findViewById(R.id.radio_group_ID);
        opt1 = findViewById(R.id.radio_option1_ID);
        opt2 = findViewById(R.id.radio_option2_ID);
        opt3 = findViewById(R.id.radio_option3_ID);
        opt4 = findViewById(R.id.radio_option4_ID);
        nextBtn = findViewById(R.id.newt_Btn_ID);
        timeET = findViewById(R.id.clock_tv_id);

        textColor = opt1.getTextColors();
        timerColor = timeET.getTextColors();

        DB_Helpter helpter = new DB_Helpter(this);
        questionsList = helpter.retrivedata(selectedCategory,selectedStrength);
        totalQuesNo = questionsList.size();
        if (totalQuesNo<0){
            quesET.setText("No ques. is found!");
        }
        Collections.shuffle(questionsList);

        showQuesOnTV();

    }

    private void showQuesOnTV() {

        radioGroup.clearCheck();

        if (currentQuesNo < totalQuesNo){
            currentQues = questionsList.get(currentQuesNo);
            quesET.setText(currentQues.getQuestion());
            opt1.setText(currentQues.getOption1());
            opt2.setText(currentQues.getOption2());
            opt3.setText(currentQues.getOption3());
            opt4.setText(currentQues.getOption4());

            currentQuesNo++;
            quesNoET.setText("Question: "+currentQuesNo+"/"+totalQuesNo);
            answered = false;
            nextBtn.setText("Confirm");

            timeLeft = COUNTDOWN_TIMER;
            startCountdown();
        }else {
            Intent intent = new Intent(this,Welcome_Activity.class);
            startActivity(intent);
        }

    }

    private void startCountdown() {
        countDownTimer = new CountDownTimer(timeLeft,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateClock();
            }

            @Override
            public void onFinish() {
                timeLeft = 0;
                updateClock();
                checkAnswer();
            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quess, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onNextClicked(View view) {
        opt1.setTextColor(Color.BLACK);
        opt2.setTextColor(Color.BLACK);
        opt3.setTextColor(Color.BLACK);
        opt4.setTextColor(Color.BLACK);


        if (!answered){
            if (opt1.isChecked()||opt2.isChecked()||opt3.isChecked()||opt4.isChecked()){
                checkAnswer();
            }else {
                Toast.makeText(this, "Please answer to the question!", Toast.LENGTH_SHORT).show();
            }
        }else {
            showQuesOnTV();
        }
    }

    private void checkAnswer() {
        answered = true;
        countDownTimer.cancel();

        RadioButton selectedBtn = findViewById(radioGroup.getCheckedRadioButtonId());
        int selectedBtnID = radioGroup.indexOfChild(selectedBtn)+1;
        if (selectedBtnID == currentQues.getAnsNumber()){
            totalScore++;
            scoreET.setText("Score: "+totalScore);
        }

        opt1.setTextColor(Color.RED);
        opt2.setTextColor(Color.RED);
        opt3.setTextColor(Color.RED);
        opt4.setTextColor(Color.RED);

        switch (currentQues.getAnsNumber()){
            case 1:
                opt1.setTextColor(Color.GREEN);
                Toast.makeText(this, currentQues.getOption1()+" is right answer", Toast.LENGTH_SHORT).show();
                break;
                case 2:
                opt2.setTextColor(Color.GREEN);
                Toast.makeText(this, currentQues.getOption2()+" is right answer", Toast.LENGTH_SHORT).show();
                break;
                case 3:
                opt3.setTextColor(Color.GREEN);
                Toast.makeText(this, currentQues.getOption3()+" is right answer", Toast.LENGTH_SHORT).show();
                break;
                case 4:
                opt4.setTextColor(Color.GREEN);
                Toast.makeText(this, currentQues.getOption4()+" is right answer", Toast.LENGTH_SHORT).show();
                break;
        }
        if (currentQuesNo < totalQuesNo){
            nextBtn.setText("Next");
        }else {
            nextBtn.setText("Finish");
        }
    }

    private void updateClock(){
        int minute = (int) ((timeLeft/1000)/60);
        int second = (int)(timeLeft/1000)%60;

        String timeFormate = String.format(Locale.getDefault(),"%02d:%02d",minute,second);
        timeET.setText(timeFormate);

        if (timeLeft <= 5000){
            timeET.setTextColor(Color.RED);
        }else if (timeLeft < 10000 && timeLeft > 5000){
            timeET.setTextColor(Color.YELLOW);
        }else {
            timeET.setTextColor(timerColor);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer!= null){
            countDownTimer.cancel();
        }
    }


}
