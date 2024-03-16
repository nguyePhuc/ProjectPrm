package com.flashfuse.data.dao;

import android.database.sqlite.SQLiteDatabase;

import com.flashfuse.data.SQLiteHelper;

public class FeedbackDAO {
    SQLiteHelper db;
    SQLiteDatabase sqLiteDatabase;

    public FeedbackDAO(SQLiteHelper db) {
        this.db = db;
        sqLiteDatabase = db.getWritableDatabase();
    }

    public void insertFeedback(String feedback){
        sqLiteDatabase = db.getWritableDatabase();
        String query = "INSERT INTO feedback (feedback) VALUES ('"+feedback+"')";
        sqLiteDatabase.execSQL(query);
    }

    public void deleteFeedback(int feedbackId){
        sqLiteDatabase = db.getWritableDatabase();
        String query = "DELETE FROM feedback WHERE id = "+feedbackId;
        sqLiteDatabase.execSQL(query);
    }

    public void updateFeedback(int feedbackId, String feedback){
        sqLiteDatabase = db.getWritableDatabase();
        String query = "UPDATE feedback SET feedback = '"+feedback+"' WHERE id = "+feedbackId;
        sqLiteDatabase.execSQL(query);
    }

    public void getAllFeedback(){
        sqLiteDatabase = db.getReadableDatabase();
        String query = "SELECT * FROM feedback";
        sqLiteDatabase.execSQL(query);
    }

    public void getFeedbackById(int feedbackId){
        sqLiteDatabase = db.getReadableDatabase();
        String query = "SELECT * FROM feedback WHERE id = "+feedbackId;
        sqLiteDatabase.execSQL(query);
    }


}
