package com.example.learningapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.ContentValues;

import androidx.activity.result.contract.ActivityResultContracts;

import java.util.ArrayList;

public class DbManager extends SQLiteOpenHelper {


    private static final String DB_NAME = "DataBase.db";

    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "User";

    private static final String ID_COL = "id";

    private static final String STUDENT_ID = "student_id";
    private static final String EMAIL_COL = "email";
    private static final String PASSWORD_COL = "password";
    private static final String FULLNAME_COL = "name";
    private static final String MAJOR_COL = "major";
    private static final String COURSES_COL = "courses";

    public DbManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + STUDENT_ID + " TEXT,"
                + FULLNAME_COL + " TEXT,"
                + MAJOR_COL + " TEXT,"
                + EMAIL_COL + " TEXT,"
                + PASSWORD_COL + " TEXT,"
                + COURSES_COL + " TEXT)";

        db.execSQL(query);
    }

    public void addNewUser(String studentId, String fullName,String major,String email, String password) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(STUDENT_ID, studentId);
        values.put(FULLNAME_COL,fullName);
        values.put(MAJOR_COL,major);
        values.put(EMAIL_COL, email);
        values.put(PASSWORD_COL, password);
        values.put(COURSES_COL, "");
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public boolean verifyUser(String email, String password){
        boolean auth = false;
        SQLiteDatabase db = this.getReadableDatabase();
        String [] userInfo = {ID_COL,STUDENT_ID,FULLNAME_COL, MAJOR_COL,EMAIL_COL,PASSWORD_COL};
        String selection = EMAIL_COL + " = ?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query(TABLE_NAME,userInfo,selection,selectionArgs,null,null,null);
        while(cursor.moveToNext()){
            String retrievedPassword = cursor.getString(5);
            if(retrievedPassword.equals(password)){
                auth = true;
            }
        }
        cursor.close();
        return auth;
    }
    public ArrayList<String> getUserInfo(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        String [] userInfo = {ID_COL,STUDENT_ID,FULLNAME_COL, MAJOR_COL,EMAIL_COL,PASSWORD_COL,COURSES_COL};
        ArrayList<String> info = new ArrayList<>();
        String selection = EMAIL_COL + " = ?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query(TABLE_NAME,userInfo,selection,selectionArgs,null,null,null);
        while(cursor.moveToNext()){
            for(int i=0;i<7;++i){
                info.add(cursor.getString(i));
            }
        }
        cursor.close();
        return info;
    }
//    public void addCourseToDb(String email,String courseName){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        ArrayList<String> oldData = getUserInfo("souhailjamhour@gmail.com");
//        String stundentId = oldData.get(1);
//        String fullName = oldData.get(2);
//        String major = oldData.get(3);
//        String userEmail = oldData.get(4);
//        String userPassword = oldData.get(5);
//        String courses = oldData.get(6);
//        String newCourses = courses +","+ courseName;
//        values.put(STUDENT_ID,stundentId);
//        values.put(FULLNAME_COL,fullName);
//        values.put(MAJOR_COL,major);
//        values.put(EMAIL_COL,userEmail);
//        values.put(PASSWORD_COL,userPassword);
//        values.put(COURSES_COL,newCourses);
//        String[] selectionArgs = {email};
//        db.update(TABLE_NAME,values,"email=?",selectionArgs);
//        db.close();
//    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

