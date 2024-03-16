package com.flashfuse.data.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.flashfuse.data.SQLiteHelper;
import com.flashfuse.data.entity.Card;

import java.util.ArrayList;
import java.util.List;

public class CardDAO {
    SQLiteHelper db;
    SQLiteDatabase sqLiteDatabase;

    public CardDAO(Context context) {
        this.db = new SQLiteHelper(context);
        sqLiteDatabase = db.getWritableDatabase();
    }

    //insert card
    public long insertCard(Card card){
        sqLiteDatabase = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("front", card.getFront());
        contentValues.put("back", card.getBack());
        contentValues.put("is_learning", card.getIsLearned());
        contentValues.put("deck_id", card.getDeck_id());
        long result = sqLiteDatabase.insert("card", null, contentValues);
        return result;
    }

    public List<Card> getCardByDeckId(int deckId){
        sqLiteDatabase= db.getReadableDatabase();
        List<Card> list= new ArrayList<>();
        String query = "SELECT * FROM card WHERE deck_id = "+deckId;
        try (android.database.Cursor cursor = sqLiteDatabase.rawQuery(query, null)) {
            if (cursor.moveToFirst()) {
                do {
                    Card card = new Card();
                    card.setId(cursor.getInt(0));
                    card.setFront(cursor.getString(1));
                    card.setBack(cursor.getString(2));
                    card.setIsLearned(cursor.getInt(3));
                    card.setDeck_id(cursor.getInt(4));
                    list.add(card);
                } while (cursor.moveToNext());
            }
        }
        return list;
    }

    public List<Card> getAllCard(){
        sqLiteDatabase= db.getReadableDatabase();
        List<Card> list= new ArrayList<>();
        String query = "SELECT * FROM card";
        try (android.database.Cursor cursor = sqLiteDatabase.rawQuery(query, null)) {
            if (cursor.moveToFirst()) {
                do {
                    Card card = new Card();
                    card.setId(cursor.getInt(0));
                    card.setFront(cursor.getString(1));
                    card.setBack(cursor.getString(2));
                    card.setIsLearned(cursor.getInt(3));
                    card.setDeck_id(cursor.getInt(4));
                    list.add(card);
                } while (cursor.moveToNext());
            }
        }
        return list;
    }

    public void deleteCard(int cardId){
        sqLiteDatabase = db.getWritableDatabase();
        sqLiteDatabase.delete("card", "id = ?", new String[]{String.valueOf(cardId)});
    }


}
