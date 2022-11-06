package com.example.myapp.service;

import com.example.myapp.constants.Urls;
import com.example.myapp.interceptors.JWTInterceptor;
import com.example.myapp.network.CategoriesApi;

import java.util.concurrent.TimeUnit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.OkHttpClient;

public class CategoryNetwork {
    private static CategoryNetwork mInstance;
    private static final String BASE_URL = Urls.BASE;
    private Retrofit mRetrofit;

    private CategoryNetwork() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new JWTInterceptor())
                .build();
        mRetrofit = new Retrofit.Builder()
                .client(okHttpClient)
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
