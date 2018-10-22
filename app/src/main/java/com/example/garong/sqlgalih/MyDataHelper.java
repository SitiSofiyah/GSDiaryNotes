package com.example.garong.sqlgalih;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyDataHelper extends SQLiteOpenHelper {
    private static final String NAMA_DB = "sinetrans.db";
    private static final String NAMA_TABEL = "sinetrons";
    private static final int VERSI_DB = 1;
    public static final int VERSI_DB2=2;

    private static final String ID = "id";
    private static final String JUDUL = "judul";
    private static final String TANGGAL= "tanggal";
    private static final String ISI = "isi";
    private static final String MOOD = "mood";


    private static final String CREATE_TABLE_REVISI = "CREATE TABLE " + NAMA_TABEL + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + JUDUL + " VARCHAR(255), " + TANGGAL + " VARCHAR(255)," +
            ISI + " VARCHAR(255), " + MOOD + " VARCHAR(255));";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + NAMA_TABEL;
    Context context;
    public MyDataHelper(Context context){
        super(context,NAMA_DB,null,VERSI_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_REVISI);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion<2){
            db.execSQL(DROP_TABLE);
            db.execSQL(CREATE_TABLE_REVISI);
        }
    }

    public void insertIntoDB(String judul,String isi,String tanggal,String mood){
        Log.d("insert", "before insert");

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("judul", judul);
//        values.put("isi", isi);
        values.put("tanggal", tanggal);
//        values.put("mood", mood);

        // 3. insert
        db.insert(NAMA_TABEL, null, values);
        // 4. close
        db.close();
        Toast.makeText(context, "insert value", Toast.LENGTH_LONG);
        Log.i("insert into DB", "After insert");
    }

    public List<DatabaseModel> getDataFromDB(){
        List<DatabaseModel> modelList = new ArrayList<DatabaseModel>();
        String query = "select * from "+NAMA_TABEL;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                DatabaseModel model = new DatabaseModel();
                model.setId(cursor.getString(0));
                model.setJudul(cursor.getString(1));
                model.setIsi(cursor.getString(2));
                model.setTanggal(cursor.getString(3));
                model.setMood(cursor.getString(4));

                modelList.add(model);
            }while (cursor.moveToNext());
        }


        Log.d("diary data", modelList.toString());


        return modelList;
    }
}

