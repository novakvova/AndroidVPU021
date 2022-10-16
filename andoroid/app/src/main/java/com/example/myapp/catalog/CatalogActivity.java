package com.example.myapp.catalog;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapp.BaseActivity;
import com.example.myapp.R;
import com.example.myapp.application.HomeApplication;
import com.example.myapp.catalog.categorycard.CategoriesAdapter;
import com.example.myapp.constants.Urls;
import com.example.myapp.dto.category.CategoryItemDTO;
import com.example.myapp.service.CategoryNetwork;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatalogActivity extends BaseActivity {

    CategoriesAdapter categoriesAdapter;
    private RecyclerView rcvCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        rcvCategories = findViewById(R.id.rcvCategories);
        rcvCategories.setHasFixedSize(true);
        rcvCategories.setLayoutManager(
                new GridLayoutManager(this,1, LinearLayoutManager.VERTICAL,false));
        requestServer();
    }
    private void requestServer()
    {
        CategoryNetwork
                .getInstance()
                .getJSONApi()
                .list()
                .enqueue(new Callback<List<CategoryItemDTO>>() {
                    @Override
                    public void onResponse(Call<List<CategoryItemDTO>> call, Response<List<CategoryItemDTO>> response) {
                        List<CategoryItemDTO> data = response.body();
                        categoriesAdapter = new CategoriesAdapter(data);
                        rcvCategories.setAdapter(categoriesAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<CategoryItemDTO>> call, Throwable t) {

                    }
                });
    }
}