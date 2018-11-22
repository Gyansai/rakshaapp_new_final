package com.ksemin.raksha.data.model.remote;

import android.util.Log;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService_carertaker {
    @POST("http://kshemin.co.in:3000" +
            "/linking")
    @FormUrlEncoded
    Call<POST> savePost_caretaker(@Field("name")String name,
                                  @Field("caretaker_number") String caretaker_number,
                                  @Field("caretaker_fire_id") String caretaker_fire_id);
}

