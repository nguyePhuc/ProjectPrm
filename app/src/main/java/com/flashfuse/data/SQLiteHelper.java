package com.flashfuse.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "flashFuse.db";
    private static final int DATABASE_VERSION = 2;
    public static final String TABLE_USERS = "user";
    public static final String TABLE_DECKS = "deck";
    public static final String TABLE_CARDS = "card";
    public static final String TABLE_FEEDBACKS = "feedback";

    //command
    public static final String COMMAND_CREATE_TABLE = "CREATE TABLE ";
    public static final String COMMAND_DROP_TABLE = "DROP TABLE IF EXISTS ";

    //create sql query
    public static final String CREATE_TABLE_USERS = COMMAND_CREATE_TABLE + TABLE_USERS + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "username TEXT NOT NULL, " +
            "password TEXT NOT NULL, " +
            "email TEXT NOT NULL, " +
            "name TEXT NOT NULL, " +
            "phone TEXT, " +
            "role INTEGER NOT NULL, " +
            "status INTEGER NOT NULL" +
            ");";
    //CREATE_TABLE_DECK
    public static final String CREATE_TABLE_DECKS = COMMAND_CREATE_TABLE + TABLE_DECKS + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT NOT NULL, " +
            "description TEXT, " +
            "star INTEGER, " +
            "user_id INTEGER NOT NULL, " +
            "FOREIGN KEY(user_id) REFERENCES " + TABLE_USERS + "(id)" +
            ");";
    //create table card
    public static final String CREATE_TABLE_CARDS = COMMAND_CREATE_TABLE + TABLE_CARDS + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "front TEXT NOT NULL, " +
            "back TEXT NOT NULL, " +
            "deck_id INTEGER NOT NULL, " +
            "is_learning TEXT NOT NULL, " +
            "FOREIGN KEY(deck_id) REFERENCES " + TABLE_DECKS + "(id)" +
            ");";
    //create table feedback
    public static final String CREATE_TABLE_FEEDBACKS = COMMAND_CREATE_TABLE + TABLE_FEEDBACKS + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "user_id INTEGER NOT NULL, " +
            "content TEXT NOT NULL, " +
            "created_at TEXT NOT NULL, " +
            "updated_at TEXT NOT NULL, " +
            "FOREIGN KEY(user_id) REFERENCES " + TABLE_USERS + "(id)" +
            ");";


    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_DECKS);
        db.execSQL(CREATE_TABLE_CARDS);
        db.execSQL(CREATE_TABLE_FEEDBACKS);

//insert data table user
        db.execSQL("INSERT INTO " + TABLE_USERS + " " + "VALUES('1','admin','admin','abc@gmail.com', 'admin', '123456789', 0, 1)");
        db.execSQL("INSERT INTO " + TABLE_USERS + " " + "VALUES('2','user1','user1','abc1@gmail.com', 'admin', '1234536789', 1, 1)");


//        //insert data table user
//
//
//        db.execSQL("INSERT INTO " + TABLE_USERS + " " +
//                "VALUES('1','admin','admin','abc@gmail.com', 'admin', '123456789', 0, 1)");
//
//        db.execSQL("INSERT INTO " + TABLE_USERS + " " +
//                "VALUES('1','user','user','abc@gmail.com', 'Nguyen Tuan Ninh', '123456789', 1, 1)");
//
//        //insert data table deck
//        db.execSQL("INSERT INTO " + TABLE_DECKS + " " +
//                "VALUES('Deck 1', 'This is deck 1',1, 1)");
//
//        db.execSQL("INSERT INTO " + TABLE_DECKS + " " +
//                "VALUES('Deck 2', 'This is deck 1',1, 1)");
//
//        db.execSQL("INSERT INTO " + TABLE_DECKS + " " +
//                "VALUES('Deck 3', 'This is deck 1',0, 1)");
//        db.execSQL("INSERT INTO " + TABLE_DECKS + " " +
//                "VALUES('Deck 4', 'This is deck 1',0, 1)");
//        db.execSQL("INSERT INTO " + TABLE_DECKS + " " +
//                "VALUES('Deck 5', 'This is deck 1',0, 1)");
//        db.execSQL("INSERT INTO " + TABLE_DECKS + " " +
//                "VALUES('Deck 6', 'This is deck 1',0, 1)");
//        //insert data table card
//        db.execSQL("INSERT INTO " + TABLE_CARDS + " " +
//                "VALUES('Front 1', 'Back 1', 1, 'true', 1)");
//
//        db.execSQL("INSERT INTO " + TABLE_CARDS + " " +
//                "VALUES('Front 2', 'Back 2', 1, 'true', 1)");
//
//        db.execSQL("INSERT INTO " + TABLE_CARDS + " " +
//                "VALUES('Front 2', 'Back 2', 1, 'true', 1)");
//        //insert data table feedback
//        db.execSQL("INSERT INTO " + TABLE_FEEDBACKS + " " +
//                "VALUES(2, 'This is feedback 1', '2023/11/19', '2023/11/19')");
//
//        db.execSQL("INSERT INTO " + TABLE_FEEDBACKS + " " +
//                "VALUES(2, 'This is feedback 2', '2023/11/19', '2023/11/19')");
//
//        db.execSQL("INSERT INTO " + TABLE_FEEDBACKS + " " +
//                "VALUES(2, 'This is feedback 3', '2023/11/19', '2023/11/19')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop table if exists
        db.execSQL(COMMAND_DROP_TABLE + TABLE_USERS);
        db.execSQL(COMMAND_DROP_TABLE + TABLE_DECKS);
        db.execSQL(COMMAND_DROP_TABLE + TABLE_CARDS);
        db.execSQL(COMMAND_DROP_TABLE + TABLE_FEEDBACKS);
        //recreate table
        onCreate(db);
    }
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }
}
