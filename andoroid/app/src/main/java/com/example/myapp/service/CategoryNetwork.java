package com.example.myapp.service;

import com.example.myapp.constants.Urls;
import com.example.myapp.network.CategoriesApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryNetwork {
    private static CategoryNetwork mInstance;
    private static final String BASE_URL = Urls.BASE;
    private Retrofit mRetrofit;

    private CategoryNetwork() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static CategoryNetwork getInstance() {
        if (mInstance == null) {
            mInstance = new CategoryNetwork();
        }
        return mInstance;
    }
    public CategoriesApi getJSONApi() {
        return mRetrofit.create(CategoriesApi.class);
    }
}
