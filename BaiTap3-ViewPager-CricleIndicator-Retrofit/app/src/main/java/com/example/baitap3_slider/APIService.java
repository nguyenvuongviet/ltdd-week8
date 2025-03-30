package com.example.baitap3_slider;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {
    @FormUrlEncoded
    @POST("newimagesmanager.php")
    Call<MessageModel> LoadImageSlider(@Field("position") int position);
}
