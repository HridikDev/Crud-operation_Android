package com.example.crudapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.database.sqlite.SQLiteDatabase;


public class MainActivity extends AppCompatActivity {

    EditText names;

    EditText emai;

    EditText passw;

    Button btn;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        names= findViewById(R.id.name);
        emai=findViewById(R.id.email);
        passw=findViewById(R.id.pass);
        btn=findViewById(R.id.button);
        db=openOrCreateDatabase("studentdb",MODE_PRIVATE,null);
        db.execSQL("create table if not exists student (id INTEGER primary key AUTOINCREMENT,name text,email text,pass text)");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=names.getText().toString();
                String email=emai.getText().toString();
                String pass=passw.getText().toString();

                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
              //  Toast.makeText(MainActivity.this, "name:"+name+"email:"+email+"password:"+pass , Toast.LENGTH_SHORT).show();
                String querry="insert into student(name,email,pass) values('"+name+"','"+email+"','"+pass+"')";
                db.execSQL(querry);
                Toast.makeText(MainActivity.this, "Inserted Sucessfull" , Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

    }
}