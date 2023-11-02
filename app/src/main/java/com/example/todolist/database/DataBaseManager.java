package com.example.todolist.database;

import static com.example.todolist.database.DataBaseConstant.NOTES_TABLE_NAME;
import static com.example.todolist.database.DataBaseConstant.NOTE_BODY;
import static com.example.todolist.database.DataBaseConstant.NOTE_ID;
import static com.example.todolist.database.DataBaseConstant.NOTE_TITLE;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.todolist.Notes;

import java.util.ArrayList;
import java.util.List;

public class DataBaseManager {
    private Context context;
    private DataBaseHelper dbHelper;
    private SQLiteDatabase db;

    public DataBaseManager(Context context){
        this.context = context;
        dbHelper = new DataBaseHelper(this.context);
    }

    public void openDB(){
        db = dbHelper.getWritableDatabase();
    }

    public void closeDB(){
        db.close();
    }

    @SuppressLint("Range")
    public List<Notes> getNotes(){
        List<Notes> notes = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                NOTES_TABLE_NAME, null);
        while (cursor.moveToNext()){
            Notes note = new Notes();
            note.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(NOTE_ID))));
            note.setTitle(cursor.getString(cursor.getColumnIndex(NOTE_TITLE)));
            note.setBody(cursor.getString(cursor.getColumnIndex(NOTE_BODY)));
            notes.add(note);
        }
        cursor.close();
        return notes;
    }

    public void addNote(Notes note){
        ContentValues cv = new ContentValues();
        cv.put(NOTE_TITLE, note.getTitle());
        cv.put(NOTE_BODY, note.getBody());
        db.insert(NOTES_TABLE_NAME, null, cv);
    }

    public void updateNote(Notes note){
        ContentValues cv = new ContentValues();
        cv.put(NOTE_TITLE, note.getTitle());
        cv.put(NOTE_BODY, note.getBody());
        db.update(NOTES_TABLE_NAME, cv, NOTE_ID + " = " + note.getId(), null);
    }

    public void deleteNote(Notes note){
        db.delete(NOTES_TABLE_NAME, NOTE_ID + " = " + note.getId(), null);
    }
}
