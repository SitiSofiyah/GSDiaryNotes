package com.example.garong.sqlgalih;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewMovie extends AppCompatActivity {

    protected Cursor cursor;
    MyDataHelper dbhHelper;
    Button btn2;
    TextView text1, text2, text3, text4, text6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_movie);

        dbhHelper = new MyDataHelper(this);
        text2 = (TextView) findViewById(R.id.textView2);
        text3 = (TextView) findViewById(R.id.textView3);
        text4 = (TextView) findViewById(R.id.textView4);
        text6 = (TextView) findViewById(R.id.textView6);

        SQLiteDatabase db = dbhHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM sinetrons WHERE judul = '" + getIntent().getStringExtra("judul") + "'", null);
        cursor.moveToFirst();

        if(cursor.getCount()>0){
            cursor.moveToPosition(0);
            text2.setText(cursor.getString(1));
            text3.setText(cursor.getString(2));
            text4.setText(cursor.getString(3));
            text6.setText(cursor.getString(4));
        }

        btn2 = (Button) findViewById(R.id.button1);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }
}
