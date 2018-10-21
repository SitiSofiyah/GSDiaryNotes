package com.example.garong.sqlgalih;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataHelper extends SQLiteOpenHelper{
    private static final String NAMA_DB = "sinetrans.db";
    private static final String NAMA_TABEL = "sinetrons";
    private static final int VERSI_DB = 1;
    public static final int VERSI_DB2=2;

    private static final String ID = "id";
    private static final String JUDUL = "judul";
    private static final String TANGGAL= "tanggal";
    private static final String ISI = "isi";
    private static final String MOOD = "mood";

    private static final String CREATE_TABLE = "CREATE TABLE " + NAMA_TABEL + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + JUDUL + " VARCHAR(255), " + TANGGAL + " VARCHAR(255));";
    private static final String CREATE_TABLE_REVISI = "CREATE TABLE " + NAMA_TABEL + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + JUDUL + " VARCHAR(255), " + TANGGAL + " VARCHAR(255)," +
            ISI + " VARCHAR(255), " + MOOD + " VARCHAR(255));";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + NAMA_TABEL;

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
}
