package com.example.intel.admin_diary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.intel.admin_diary.Model.PostPutDellokasi;
import com.example.intel.admin_diary.Rest.ApiClient;
import com.example.intel.admin_diary.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarRinci extends AppCompatActivity {

    EditText edtIdLokasi, edtlokasi;
    Button btInsert, btUpdate, btDelete, btBack;
    TextView tvMessage;
    ApiInterface mApiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_rinci);

        edtIdLokasi = (EditText) findViewById(R.id.edtIdLokasi);
        edtlokasi = (EditText) findViewById(R.id.edtLokasi);
        tvMessage = (TextView) findViewById(R.id.tvMessage2);

        btInsert = (Button) findViewById(R.id.btInsert2);
        btUpdate = (Button) findViewById(R.id.btUpdate2);
        btDelete = (Button) findViewById(R.id.btDelete2);
        btBack = (Button) findViewById(R.id.btBack);

        Intent mIntent = getIntent();
        edtIdLokasi.setText(mIntent.getStringExtra("id_lokasi"));
        edtlokasi.setText(mIntent.getStringExtra("lokasi"));

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),Beranda.class);
                startActivity(in);
            }
        });

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<PostPutDellokasi> updateLokasiCall = mApiInterface.putLokasi(
                        edtIdLokasi.getText().toString(),
                        edtlokasi.getText().toString()
                );

                updateLokasiCall.enqueue(new Callback<PostPutDellokasi>() {
                    @Override
                    public void onResponse(Call<PostPutDellokasi> call, Response<PostPutDellokasi> response) {
                        tvMessage.setText(" Retrofit Update: " +
                                "\n " + " Status Update : " +response.body().getStatus() +
                                "\n " + " Message Update : "+ response.body().getMessage());
                    }

                    @Override
                    public void onFailure(Call<PostPutDellokasi> call, Throwable t) {
                        tvMessage.setText("Retrofit Update: \n Status Update :"+ t.getMessage());
                    }
                });

            }
        });

        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<PostPutDellokasi> postPembelianCall = mApiInterface.postLokasi(
                        edtIdLokasi.getText().toString(),
                        edtlokasi.getText().toString()
                );

                postPembelianCall.enqueue(new Callback<PostPutDellokasi>() {
                    @Override
                    public void onResponse(Call<PostPutDellokasi> call, Response<PostPutDellokasi> response) {
                        tvMessage.setText(" Retrofit Insert: " +
                                "\n " + " Status Insert : " +
                                response.body().getStatus() +
                                "\n " + " Message Insert : "+ response.body().getMessage());
                    }

                    @Override
                    public void onFailure(Call<PostPutDellokasi> call, Throwable t) {
                        tvMessage.setText("Retrofit Insert: \n Status Insert :"+ t.getMessage());
                    }
                });
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtIdLokasi.getText().toString().trim().isEmpty()){

                    Call<PostPutDellokasi> deletePembelian = mApiInterface.deleteLokasi(edtIdLokasi.getText().toString());
                    deletePembelian.enqueue(new Callback<PostPutDellokasi>() {
                        @Override
                        public void onResponse(Call<PostPutDellokasi> call, Response<PostPutDellokasi> response) {
                            tvMessage.setText(" Retrofit Delete: " +
                                    "\n " + " Status Delete : " +response.body().getStatus() +
                                    "\n " + " Message Delete : "+ response.body().getMessage());
                        }

                        @Override
                        public void onFailure(Call<PostPutDellokasi> call, Throwable t) {
                            tvMessage.setText("Retrofit Delete: \n Status Delete :"+ t.getMessage());
                        }
                    });
                }else{
                    tvMessage.setText("id_pembelian harus diisi");
                }
            }
        });

    }
}
