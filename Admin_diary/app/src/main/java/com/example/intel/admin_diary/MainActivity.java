package com.example.intel.admin_diary;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.intel.admin_diary.Adapter.MoodAdapter;
import com.example.intel.admin_diary.Model.GetMood;
import com.example.intel.admin_diary.Model.Mood;
import com.example.intel.admin_diary.Rest.ApiClient;
import com.example.intel.admin_diary.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ApiInterface mApiInterface ;
    FloatingActionButton btAddData;
    Button btGet;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btGet = ( Button ) findViewById (R.id.btGet );
        btAddData = ( FloatingActionButton ) findViewById (R.id.btAdd );

        mApiInterface = ApiClient. getClient (). create ( ApiInterface . class );

        mRecyclerView = (RecyclerView)  findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        btGet.setOnClickListener ( new View. OnClickListener () {
            @Override
            public void onClick ( View view ) {
                Call<GetMood> moodCall = mApiInterface.getMood();
                moodCall.enqueue ( new Callback<GetMood>() {
                    @Override
                    public void onResponse(Call<GetMood> call, Response<GetMood> response) {
                        List<Mood> moodList = response.body().getResult();
                        Log.d("Retrofit Get", "Jumlah data pembelian: " +
                                String.valueOf(moodList.size()));
                        mAdapter = new MoodAdapter(moodList);
                        mRecyclerView.setAdapter(mAdapter);

                    }

                    @Override
                    public void onFailure(Call<GetMood> call, Throwable t) {
                        Log.e("Retrofit Get", t.toString());
                    }
                });
            }
        });

        btAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddMood.class);
                startActivity(intent);
            }
        });


    }
}
