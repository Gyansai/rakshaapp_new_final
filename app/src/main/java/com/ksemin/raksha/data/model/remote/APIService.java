package com.ksemin.raksha.data.model.remote;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {

    @POST("wearer_details")
    @FormUrlEncoded
    Call<POST> savePost(@Field("user_id") String user_id,
                        @Field("name") String title,
                        @Field("age") String body,
                        @Field("gender")String gender,
                        @Field ("blood_group") String blood_group,
                        @Field("wearer_fire_id")String wearer_fire_id,
                        @Field("caretaker_number")String caretaker_number);


}
