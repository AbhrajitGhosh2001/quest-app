package com.example.questapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

public static String DATABASE_NAME = "quest.db";
public static String TABLE1_NAME = "users_table";
public static String TABLE2_NAME = "transactions_table";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    public void insertUser(String email, String password, int phoneNumber, String location){//returns -1 on insertion fail
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cvs = new ContentValues();
        cvs.put("Email", email);
        cvs.put("Password", password);//subject to change, look into firebase auth?
        cvs.put("PhoneNumber",phoneNumber);
        cvs.put("Location", location);
        db.insert(TABLE1_NAME,null,cvs);//inserts row with set attributes into user table inside db
    }
    //will need to overload to create a job without knowing client/fulfiller ahead of time
    public void insertTransaction(int transactionID, String client, String fulfiller, String location, String description, boolean isCompleted){//returns -1 on insertion fail
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cvs = new ContentValues();
        cvs.put("TransactionID", transactionID);//id of current transaction, maybe use SQLite given ID instead?
        cvs.put("Client", client);//person who requested job
        cvs.put("Fulfiller", fulfiller);//person who accepted job
        cvs.put("Location", location);//where job is to be fulfilled
        cvs.put("Description", description);//description of job
        cvs.put("isCompleted", isCompleted);//has the transaction been completed, or is it currently open?
        db.insert(TABLE2_NAME,null,cvs);//inserts row with set attributes into transaction table inside db
    }

    public Cursor getUserInfo(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from "+TABLE1_NAME,null);
        return result;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE1_NAME+" (Email TEXT PRIMARY KEY, Password TEXT, PhoneNumber INTEGER, Location TEXT)");//executes SQL arguments to create user table, maybe implement rating system later?
        db.execSQL("create table "+TABLE2_NAME+" (TransactionID INTEGER PRIMARY KEY, Client TEXT, Fulfiller INTEGER, Location TEXT, Description TEXT, isCompleted BOOL)");//executes SQL arguments to create transaction table
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){
    }
}
