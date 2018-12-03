package com.example.intel.admin_diary;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.intel.admin_diary.Model.GetMood;
import com.example.intel.admin_diary.Rest.ApiClient;
import com.example.intel.admin_diary.Rest.ApiInterface;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMood extends AppCompatActivity {

    Context mContext;
    Button btAddData;
    ImageView mood;
    String imagePath = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mood);

        mContext = getApplicationContext();
        mood = (ImageView) findViewById(R.id.imgMood);

        btAddData = (Button) findViewById(R.id.btAddMood);

        btAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_PICK);
                Intent intentChoose = Intent.createChooser(
                        galleryIntent,
                        "Pilih foto untuk di-upload");
                startActivityForResult(intentChoose, 10);

                ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);

                MultipartBody.Part body = null;
                if (!imagePath.isEmpty()) {
                    // Buat file dari image yang dipilih
                    File file = new File(imagePath);

                    // Buat RequestBody instance dari file
                    RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);

                    // MultipartBody.Part digunakan untuk mendapatkan nama file
                    body = MultipartBody.Part.createFormData("mood", file.getName(),
                            requestFile);
                }
                RequestBody reqAction = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        "insert");
                Call<GetMood> moodCall = mApiInterface.postMood(body, reqAction);
                moodCall.enqueue(new Callback<GetMood>() {
                    @Override
                    public void onResponse(Call<GetMood> call, Response<GetMood> response) {
//                      Log.d("Insert Retrofit",response.body().getMessage());
                        if (response.body().getStatus().equals("failed")) {
                            Log.d("Insert Retrofit", response.body().getMessage());
                        } else {
                            Intent intent = new Intent(mContext, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                    @Override
                    public void onFailure(Call<GetMood> call, Throwable t) {
                      Log.d("Insert Retrofit", t.getMessage());

                    }
                });
            }
        });
    }


}
