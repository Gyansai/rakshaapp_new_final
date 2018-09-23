package com.ksemin.raksha.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post_falltest {
    @SerializedName("user_id")
    @Expose
    private String user_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String name) {
        this.user_id= user_id;
    }
    @Override
    public String toString() {
        return "Post_falltest{" +
                ", user_id=" + user_id+ '\'' +
                '}';
    }
}
