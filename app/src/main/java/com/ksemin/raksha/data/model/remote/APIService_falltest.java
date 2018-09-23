package com.ksemin.raksha.data.model.remote;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService_falltest {


    @POST("http://192.168.1.4:3000" +
            "/fall_alert")
    @FormUrlEncoded
    Call<POST> savepost_falltest(@Field("user_id")String user_id);

}
