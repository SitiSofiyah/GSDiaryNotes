package com.example.garong.sqlgalih;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class InsertMovie extends AppCompatActivity {
    protected Cursor cursor;
    MyDataHelper dbHelper;
    Button btn1, btn2;
    EditText text2, text3, text4, text5;
    Spinner sp;
    DatePickerDialog.OnDateSetListener mdatasetlistner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_movie);
        dbHelper = new MyDataHelper(this);
        text2 = (EditText) findViewById(R.id.editText2);
        text3 = (EditText) findViewById(R.id.editText3);
        text4 = (EditText) findViewById(R.id.editText4);
        sp = (Spinner) findViewById(R.id.spin);

        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);

        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        InsertMovie.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mdatasetlistner,
                        year,month,day
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mdatasetlistner = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                String date= month+"/"+day+"/"+year;
                text3.setText(date);
            }
        };

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("INSERT INTO sinetrons(judul,tanggal,isi,mood) values('" + text2.getText().toString() + "','" + text3.getText().toString() + "','"
                        + text4.getText().toString() + "','" + sp.getSelectedItem().toString() + "')");
                Toast.makeText(getApplicationContext(),"berhasil ditambahkan",Toast.LENGTH_LONG).show();

                MainActivity.layarutama.TampilkanList();

                finish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                finish();
            }
        });

    }
}
