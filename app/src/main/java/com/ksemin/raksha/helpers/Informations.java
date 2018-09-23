package com.ksemin.raksha.helpers;

import io.realm.RealmObject;

public class Informations extends RealmObject {

    String name,blood_group;
    String age;
    String gender;
    String user_id;
    String wearer_fire_id;
    String careatker_number;

    @Override
    public String toString() {
        return "Information{" +
                "name='" + name + '\'' +
                ",Blood_group ='" + blood_group + '\'' +
                ", age=" + age + '\''+
                ", gender=" + gender + '\''+
                ", user_id=" + user_id+'\''+
                ", wearer_fire_id=" + wearer_fire_id+'\''+
                ", careatker_number=" + careatker_number+
                '}';
    }

    public  String getName() {
        return name;
    }

    public void setName(String name1) {
        this.name = name1;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group1) {
        this.blood_group = blood_group1;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age1) {
        this.age = age1;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender1) {
        this.gender = gender1;
    }
}
