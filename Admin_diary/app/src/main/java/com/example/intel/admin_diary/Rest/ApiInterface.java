package com.example.intel.admin_diary.Rest;

import com.example.intel.admin_diary.Model.GetLokasi;
import com.example.intel.admin_diary.Model.GetMood;
import com.example.intel.admin_diary.Model.PostPutDellokasi;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface ApiInterface {

    //Lokasi
    @GET("lokasi")
    Call<GetLokasi> getLokasi();

    @FormUrlEncoded
    @POST("lokasi")
    Call<PostPutDellokasi> postLokasi
            (@Field("id_lokasi") String idLokasi, @Field("lokasi") String Lokasi);

    @FormUrlEncoded
    @PUT("lokasi")
    Call<PostPutDellokasi> putLokasi(
            @Field("id_lokasi") String idLokasi, @Field("lokasi") String Lokasi);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "lokasi", hasBody = true)
    Call<PostPutDellokasi> deleteLokasi(@Field("id_lokasi") String idLokasi);




    //Mood
    @GET("mood/all")
    Call<GetMood> getMood();

    @Multipart
    @POST("mood/all")
    Call<GetMood> postMood(
            @Part MultipartBody.Part file,
            @Part("action") RequestBody action
    );


    @Multipart
    @POST("mood/all")
    Call<GetMood> deleteMood(
            @Part("id_mood") RequestBody idMood,
            @Part("action") RequestBody action);


}
