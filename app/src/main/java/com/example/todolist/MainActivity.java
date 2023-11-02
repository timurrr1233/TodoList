package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.todolist.database.DataBaseManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DataBaseManager dbManager;
    List<Notes> notes;
    RecyclerView recyclerView;
    Button addNote;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addNote = findViewById(R.id.buttonAddNote);
        addNote.setBackgroundResource(R.drawable.round_button);
        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addIntent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivity(addIntent);
            }
        });
        //addNotes();
        dbManager = new DataBaseManager(this);
        dbManager.openDB();
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        notes = dbManager.getNotes();
        NotesAdapter notesAdapter = new NotesAdapter(notes, this, dbManager);
        if (notes.size() > 0){
            recyclerView.setAdapter(notesAdapter);
        }

    }

    public void addNotes(){
        DataBaseManager dataBaseManager = new DataBaseManager(this);
        dataBaseManager.openDB();
        dataBaseManager.addNote(new Notes("Заголовок 1", "Тело заметки 1"));
        dataBaseManager.addNote(new Notes("Заголовок 2", "Тело заметки 2"));
        dataBaseManager.addNote(new Notes("Заголовок 3", "Тело заметки 3"));
        dataBaseManager.addNote(new Notes("Заголовок 4", "Тело заметки 4"));
        dataBaseManager.addNote(new Notes("Заголовок 5", "Тело заметки 5"));
        dataBaseManager.closeDB();
    }

    @Override
    protected void onResume() {
        super.onResume();
        dbManager = new DataBaseManager(this);
        dbManager.openDB();
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        notes = dbManager.getNotes();
        NotesAdapter servicesAdapter = new NotesAdapter(notes, this, dbManager);
        if (notes.size() > 0){
            recyclerView.setAdapter(servicesAdapter);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.closeDB();
    }
}