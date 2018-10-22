package com.example.garong.sqlgalih;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewMovie extends AppCompatActivity {

    protected Cursor cursor;
    MyDataHelper dbhHelper;
    Button btn2;
    FloatingActionButton fb;
    TextView text1, text2, text3, text4, text6;
    List<DatabaseModel> dbList;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_movie);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        dbhHelper = new MyDataHelper(this);
        text2 = (TextView) findViewById(R.id.textView2);
        text3 = (TextView) findViewById(R.id.textView3);
        text4 = (TextView) findViewById(R.id.textView4);
        text6 = (TextView) findViewById(R.id.textView6);

        position = bundle.getInt("position");
        fb = findViewById(R.id.fb);

        text2 =(TextView)findViewById(R.id.textView2);
        text3 =(TextView)findViewById(R.id.textView3);
        text4 =(TextView)findViewById(R.id.textView4);
        text6 =(TextView)findViewById(R.id.textView6);
        dbhHelper = new MyDataHelper(this);
        dbList= new ArrayList<DatabaseModel>();
        dbList = dbhHelper.getDataFromDB();

        if(dbList.size()>0){
            String judul= dbList.get(position).getJudul();
            String isi=dbList.get(position).getIsi();
            String tanggal=dbList.get(position).getTanggal();
            String mood=dbList.get(position).getMood();
            text2.setText(judul);
            text6.setText(mood);
            text3.setText(tanggal);
            text4.setText(isi);
        }

        Toast.makeText(ViewMovie.this, dbList.toString(), Toast.LENGTH_LONG);


        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }
}
