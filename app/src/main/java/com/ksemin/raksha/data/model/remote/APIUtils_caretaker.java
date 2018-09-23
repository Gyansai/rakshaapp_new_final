package com.ksemin.raksha.data.model.remote;

public class APIUtils_caretaker {
    private APIUtils_caretaker() {}

    public static final String BASE_URL = "http://kshemin.co.in:3000/";

    public static APIService_carertaker getAPIService() {

        return (APIService_carertaker) RetrofitClient.getClient(BASE_URL).create(APIService_carertaker.class);
    }
}
