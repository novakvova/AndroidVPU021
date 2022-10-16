package com.example.myapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapp.application.HomeApplication;
import com.example.myapp.constants.Urls;
import com.example.myapp.dto.category.CategoryAdapter;
import com.example.myapp.dto.category.CategoryItemDTO;
import com.example.myapp.service.CategoryNetwork;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }


}