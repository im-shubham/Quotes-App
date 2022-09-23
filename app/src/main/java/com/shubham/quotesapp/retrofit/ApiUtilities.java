package com.shubham.quotesapp.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtilities {

    public   static  ApiUtilities apiUtilities;

    private static Retrofit retrofit;

    public ApiUtilities() {
        retrofit = new Retrofit.Builder().baseUrl("https://type.fit/").addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static synchronized ApiUtilities getInstance(){
        if(apiUtilities == null){
            apiUtilities =  new ApiUtilities();
        }
        return apiUtilities;
    }
    public ApiInterface getApi(){
        return  retrofit.create(ApiInterface.class);
    }
}
