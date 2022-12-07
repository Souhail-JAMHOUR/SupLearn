package com.example.learningapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.ContentValues;

public class DbManager extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "DataBase.db";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "User";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // the student id
    private static final String STUDENT_ID = "student_id";

    // below variable is for our course name column
    private static final String EMAIL_COL = "email";

    private static final String PASSWORD_COL = "password";

    // creating a constructor for our database handler.
    public DbManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + STUDENT_ID + " TEXT,"
                + EMAIL_COL + " TEXT,"
                + PASSWORD_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addNewUser(String studentId, String email, String password) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(STUDENT_ID, studentId);
        values.put(EMAIL_COL, email);
        values.put(PASSWORD_COL, password);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }
    public boolean verifyUser(String email, String password){
        boolean auth = false;
        SQLiteDatabase db = this.getReadableDatabase();
        String [] userInfo = {ID_COL,STUDENT_ID,EMAIL_COL,PASSWORD_COL};
        String selection = EMAIL_COL + " = ?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query(TABLE_NAME,userInfo,selection,selectionArgs,null,null,null);
        while(cursor.moveToNext()){
            String retrievedPassword = cursor.getString(3);
            if(retrievedPassword.equals(password)){
                auth = true;
            }
        }
        cursor.close();
        return auth;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

