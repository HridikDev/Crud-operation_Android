package com.example.crudapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    EditText iedt;
    EditText nedt;
    EditText pedt;
    Button uubtn;

    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        iedt=findViewById(R.id.editTextText2);
        nedt=findViewById(R.id.editTextText3);
        pedt=findViewById(R.id.editTextText4);
        uubtn=findViewById(R.id.button5);
        db=openOrCreateDatabase("studentdb",MODE_PRIVATE,null);
        db.execSQL("create table if not exists student (id INTEGER primary key AUTOINCREMENT,name text,email text,pass text)");

        uubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=iedt.getText().toString();
                String name=nedt.getText().toString();
                String pass=pedt.getText().toString();

                String querry="update student set name ='" + name + "',pass='" + pass + "' where id=" + id;
                db.execSQL(querry);
                Toast.makeText(MainActivity3.this, "Updated Sucessfull" , Toast.LENGTH_SHORT).show();
                Intent intent3=new Intent(MainActivity3.this,MainActivity2.class);
                startActivity(intent3);
            }
        });
    }
}