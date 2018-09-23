package com.ksemin.raksha.data.model.remote;

public class ApiUtils_falltest {
    private ApiUtils_falltest() {}

    public static final String BASE_URL = "http://kshemin.co.in:3000/";

    public static APIService_falltest getAPIService() {

        return (APIService_falltest) RetrofitClient.getClient(BASE_URL).create(APIService_falltest.class);
    }
}
