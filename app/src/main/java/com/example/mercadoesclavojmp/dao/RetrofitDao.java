package com.example.mercadoesclavojmp.dao;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDao {

    private static final String BASE_URL = "https://api.mercadolibre.com";
    protected Retrofit retrofit;

    public RetrofitDao(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
