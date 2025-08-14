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

public class MainActivity2 extends AppCompatActivity {

    SQLiteDatabase db;
    TextView textView;
    Button vbtn;

    EditText edt;
    Button delbtn;

    Button ubtn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        textView= findViewById(R.id.textView);
        vbtn=findViewById(R.id.button2);

        edt=findViewById(R.id.editTextText);
        delbtn=findViewById(R.id.button3);
        db=openOrCreateDatabase("studentdb",MODE_PRIVATE,null);
        db.execSQL("create table if not exists student (id INTEGER primary key AUTOINCREMENT,name text,email text,pass text)");

        ubtn2=findViewById(R.id.button4);

        vbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               refresh();
            }
        });

        delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=edt.getText().toString();
                String querry="delete from student where name = '"+name+"'";
                db.execSQL(querry);
                Toast.makeText(MainActivity2.this, "Deleted Sucessfull" , Toast.LENGTH_SHORT).show();
                refresh();
            }
        });
        ubtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(MainActivity2.this,MainActivity3.class);
                startActivity(intent1);
            }
        });

    }
    private void refresh(){
        StringBuilder result= new StringBuilder();
        Cursor cursor=db.rawQuery("select *from student",null);
        if(cursor.getCount()==0){
            textView.setText("No values present");
            return;
        }
        while(cursor.moveToNext()){
            int id= cursor.getInt(0);
            String name=cursor.getString(1);
            String pass=cursor.getString(3);
            result.append("id: ").append(id);
            result.append("\tName: ").append(name);
            result.append("\tPassword: ").append(pass);
            //result.append("\tEmail: ").append(email);
            result.append("\n");
        }
        cursor.close();
        textView.setText(result.toString());
    }
}