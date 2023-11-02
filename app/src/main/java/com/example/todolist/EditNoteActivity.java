package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todolist.database.DataBaseManager;

public class EditNoteActivity extends AppCompatActivity {

    EditText title, body;
    Button save;
    DataBaseManager dbManager;
    Notes editNote = new Notes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        dbManager = new DataBaseManager(this);
        dbManager.openDB();
        title = findViewById(R.id.editTitleNote);
        body = findViewById(R.id.editBodyNote);
        save = findViewById(R.id.buttonSaveNote);

        Bundle items = getIntent().getExtras();
        if (items != null){
            editNote = (Notes) items.getSerializable(Notes.class.getSimpleName());
            title.setText(editNote.getTitle());
            body.setText(editNote.getBody());
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editNote.setId(editNote.getId());
                editNote.setTitle(title.getText().toString());
                editNote.setBody(body.getText().toString());
                Toast.makeText(EditNoteActivity.this, "Данные обновлены",
                        Toast.LENGTH_SHORT).show();
                dbManager.openDB();
                dbManager.updateNote(editNote);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.closeDB();
    }
}