package com.example.todolist.database;

public class DataBaseConstant {

    public static final String DATA_BASE_NAME = "todolist.db";
    public static final int DATA_BASE_VERSION = 1;
    public static final String NOTES_TABLE_NAME = "Notes";
    public static final String NOTE_ID = "id";
    public static final String NOTE_TITLE = "title";
    public static final String NOTE_BODY = "body";

    public static final String CREATE_TABLE_NOTES = "CREATE TABLE IF NOT EXISTS " +
            "" + NOTES_TABLE_NAME + " ( " + NOTE_ID + " INTEGER PRIMARY KEY, " +
            "" + NOTE_TITLE + " TEXT, " + NOTE_BODY + " TEXT);";
    public static final String DELETE_TABLE_NOTES = "DROP TABLE IF EXISTS " + NOTES_TABLE_NAME;

}
