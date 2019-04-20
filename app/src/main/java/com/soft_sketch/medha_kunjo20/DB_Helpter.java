package com.soft_sketch.medha_kunjo20;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.soft_sketch.medha_kunjo20.DB_Content.*;

import java.util.ArrayList;
import java.util.List;

public class DB_Helpter extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MedhaKunjo_Database";
    private static final int DATABASE_VERSION = 2;

    private SQLiteDatabase medhaKunjoDB;

    public DB_Helpter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.medhaKunjoDB = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLOUM_QUESTION + " TEXT, " +
                QuestionsTable.COLOUM_OPTION1 + " TEXT, " +
                QuestionsTable.COLOUM_OPTION2 + " TEXT, " +
                QuestionsTable.COLOUM_OPTION3 + " TEXT, " +
                QuestionsTable.COLOUM_OPTION4 + " TEXT, " +
                QuestionsTable.COLOUM_ANS_NUM + " INTEGER, " +
                QuestionsTable.COLOUM_CATEGORY + " TEXT, " +
                QuestionsTable.COLOUM_STRENGTH + " TEXT"+
                ")";

        medhaKunjoDB.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        insertdata();


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ QuestionsTable.TABLE_NAME);
        onCreate(db);

    }

    private void insertdata(){
        Questions ques1 = new Questions("Who did create us? ","Allah","Science","Human","Mankind",1,"General Knowledge","Simple");
        AddDataToDB(ques1);
        Questions ques2 = new Questions("সূর্যকিরণ হতে যে ভিটামিন পাওয়া যায় ?","এ ","ডি ","সি ","বি ",2,"Science","Moderate");
        AddDataToDB(ques2);
        Questions ques3 = new Questions("Free hit কোন খেলার সাথে সম্পর্কিত ?","ফুটবল ","হাডুডু ","ক্রিকেট","বলিবল ",3,"General Knowledge","Simple");
        AddDataToDB(ques3);
        Questions ques4 = new Questions(" “A green Book” গ্রন্থটির লেখক ? ","মহাত্নাগান্ধি"," কালীপ্রসন্ন সিংহ","গাদ্দাফি","আবু ইসহাক",3,"General Knowledge","Difficult");
        AddDataToDB(ques4);
        Questions ques5 = new Questions("বায়ু মন্ডলে অক্সিজেনের পরিমান কত ?","20.71%","15.72%","32.62%","23.45%",1,"Math","Difficult");
        AddDataToDB(ques5);
        Questions ques6 = new Questions("কুমিল্লার পূর্ব নাম কি?","মেহেরপুর ","ত্রিপুরা ","ঢাকা ","সিলেট ",2,"Banking","Moderate");
        AddDataToDB(ques6);

    }

    private void AddDataToDB(Questions questions){
        ContentValues cValues = new ContentValues();
        cValues.put(QuestionsTable.COLOUM_QUESTION,questions.getQuestion());
        cValues.put(QuestionsTable.COLOUM_OPTION1,questions.getOption1());
        cValues.put(QuestionsTable.COLOUM_OPTION2,questions.getOption2());
        cValues.put(QuestionsTable.COLOUM_OPTION3,questions.getOption3());
        cValues.put(QuestionsTable.COLOUM_OPTION4,questions.getOption4());
        cValues.put(QuestionsTable.COLOUM_ANS_NUM,questions.getAnsNumber());

        medhaKunjoDB.insert(QuestionsTable.TABLE_NAME,null,cValues);
    }

    public List<Questions> retrivedata(String cat, String strength){
        List<Questions> questionsList = new ArrayList<>();
        medhaKunjoDB = getReadableDatabase();
       /* String[] coloumToShow = {QuestionsTable.COLOUM_QUESTION,QuestionsTable.COLOUM_OPTION1,QuestionsTable.COLOUM_OPTION2,QuestionsTable.COLOUM_OPTION3,QuestionsTable.COLOUM_OPTION4};
        String[] arguments = {cat,strength};*/

        Cursor cursor = medhaKunjoDB.rawQuery("SELECT * FROM "+QuestionsTable.TABLE_NAME,null);
        if (cursor.moveToFirst()){
            do{
                Questions questions = new Questions();
                questions.setQuestion(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLOUM_QUESTION)));
                questions.setOption1(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLOUM_OPTION1)));
                questions.setOption2(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLOUM_OPTION2)));
                questions.setOption3(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLOUM_OPTION3)));
                questions.setOption4(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLOUM_OPTION4)));
                questions.setAnsNumber(cursor.getInt(cursor.getColumnIndex(QuestionsTable.COLOUM_ANS_NUM)));
                questionsList.add(questions);
            }while (cursor.moveToNext());
        }else {
        }
        cursor.close();
        return questionsList;
    }
}

