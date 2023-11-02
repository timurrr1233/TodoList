package com.example.todolist;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.database.DataBaseManager;

import java.io.Serializable;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    LayoutInflater inflater;
    List<Notes> notes;
    DataBaseManager dbManager;

    public NotesAdapter(List<Notes> notes, Context context, DataBaseManager dbManager){
        this.notes = notes;
        this.inflater = LayoutInflater.from(context);
        this.dbManager = dbManager;
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.notes_list, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Notes note = notes.get(position);
        holder.id.setText("Номер заметки: " + String.valueOf(note.getId()));
        holder.title.setText("Заголовок: " + note.getTitle());
        holder.body.setText("Текст заметки: " + note.getBody());
        holder.body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(inflater.getContext());
                builder.setTitle("Выберите действие");
                // Кнопка редактировать заметку
                builder.setPositiveButton("Редактировать", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent editIntent = new Intent(view.getContext(), EditNoteActivity.class);
                        editIntent.putExtra(Notes.class.getSimpleName(), note);
                        view.getContext().startActivity(editIntent);
                    }
                });
                // Кнопка удалить заметку
                builder.setNegativeButton("Удалить", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dbManager.openDB();
                        dbManager.deleteNote(note);
                        dbManager.closeDB();
                    }
                });
                // Кнопка отмена
                builder.setNeutralButton("Отмена", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, title, body;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.idNote);
            title = itemView.findViewById(R.id.titleNote);
            body = itemView.findViewById(R.id.bodyNote);
        }
    }
}
