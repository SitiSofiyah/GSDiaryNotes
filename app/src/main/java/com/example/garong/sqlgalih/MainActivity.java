package com.example.garong.sqlgalih;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView ListView01;
    Button logot;
    EditText filter;

    protected Cursor cursor;
    MyDataHelper datasinetron;

    public static  MainActivity layarutama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fb = findViewById(R.id.fab);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(MainActivity.this,InsertMovie.class);
                startActivity(myintent);
            }
        });

        layarutama = this;
        TampilkanList();

        logot = findViewById(R.id.btn_logout);

        logot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences uname = getSharedPreferences("username", Context.MODE_PRIVATE);
                SharedPreferences.Editor edituname = uname.edit();
                SharedPreferences pass = getSharedPreferences("password",Context.MODE_PRIVATE);
                SharedPreferences.Editor editpass = pass.edit();
                edituname.clear();
                edituname.commit();
                editpass.clear();
                editpass.commit();
                finish();

            }
        });


    }

    public void TampilkanList() {
        datasinetron = new MyDataHelper(this);


        SQLiteDatabase db =datasinetron.getReadableDatabase();

        cursor = db.rawQuery("SELECT * FROM sinetrons",null);
        final String[] databaru = new String[cursor.getCount()];


        cursor.moveToFirst();

        for(int cc=0;cc<cursor.getCount();cc++){
            cursor.moveToPosition(cc);
            databaru[cc]=cursor.getString(1);
        }


        ListView01 = (ListView) findViewById(R.id.listView1);
        ListView01.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,databaru));
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = databaru[arg2];
                final CharSequence[] dialogitem = {"Lihat Diary","Hapus Diary"};

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Pilih");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item){
                            case 0 :
                                Intent intent1 = new Intent(getApplicationContext(),ViewMovie.class);
                                intent1.putExtra("judul",selection);
                                startActivity(intent1);
                                break;
                            case 1 :
                                SQLiteDatabase db = datasinetron.getWritableDatabase();
                                db.execSQL("DELETE FROM sinetrons WHERE judul = '" + selection + "'");
                                TampilkanList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });

        ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();

        cursor.close();
    }

}
