package com.ksemin.raksha.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post_carertaker {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("caretaker_number")
    @Expose
    private String caretaker_number;
    @SerializedName("caretaker_fire_id")
    @Expose
    private String caretaker_fire_id;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.caretaker_number= caretaker_number;
    }
    public String getCaretaker_number() {
        return caretaker_number;
    }

    public void setCaretaker_number(String title) {
        this.caretaker_number= caretaker_number;
    }

    public String getCaretaker_fire_id() {return caretaker_fire_id ;
    }

    public void setCaretaker_fire_id(String title) {
        this.caretaker_fire_id = caretaker_fire_id;
    }

    @Override
    public String toString() {
        return "Post_caretaker{" +
                ", name=" + name+ '\'' +
                ", caretaker_number=" + caretaker_number+ '\'' +
                ", caretaker_fire_id=" + caretaker_fire_id +
                '}';
    }
}
