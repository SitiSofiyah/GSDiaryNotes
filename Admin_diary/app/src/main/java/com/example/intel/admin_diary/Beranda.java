package com.example.intel.admin_diary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Beranda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent mIntent;
        switch (item.getItemId()) {

            case R.id.menuListLokasi:
                mIntent = new Intent(this, layarutama.class);
                startActivity(mIntent);
                return true;
            case R.id.menuListMood:
                mIntent = new Intent(this, MainActivity.class);
                startActivity(mIntent);
                return true;
            case R.id.logout:
                SharedPreferences setting = getSharedPreferences("key", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = setting.edit();
                editor.remove("username");
                editor.remove("password");
                editor.commit();
                finish();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
