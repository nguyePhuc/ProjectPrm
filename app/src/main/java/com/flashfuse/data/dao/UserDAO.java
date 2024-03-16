package com.flashfuse.data.dao;

import android.database.sqlite.SQLiteDatabase;

import com.flashfuse.data.SQLiteHelper;

public class UserDAO {
    SQLiteHelper db;
    SQLiteDatabase sqLiteDatabase;

    public UserDAO(SQLiteHelper db) {
        this.db = db;
        sqLiteDatabase = db.getWritableDatabase();
    }

    public int CheckUserRole(String username, String password){
        sqLiteDatabase = db.getReadableDatabase();
        String query = "SELECT role FROM user WHERE username = '"+username+"' AND password = '"+password+"'";
        return sqLiteDatabase.rawQuery(query, null).getInt(0);
    }

}
