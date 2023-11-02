package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todolist.database.DataBaseManager;

public class AddNoteActivity extends AppCompatActivity {

    EditText title, body;
    Button saveNew;
    DataBaseManager dbManager;
    Notes newNote = new Notes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        title = findViewById(R.id.editNewNoteTitle);
        body = findViewById(R.id.editNewNoteBody);
        saveNew = findViewById(R.id.buttonSaveNewNote);
        dbManager = new DataBaseManager(this);
        dbManager.openDB();
        saveNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newNote.setTitle(title.getText().toString());
                newNote.setBody(body.getText().toString());
                dbManager.addNote(newNote);
                Toast.makeText(AddNoteActivity.this, "Заметка сохранена!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.closeDB();
    }
}