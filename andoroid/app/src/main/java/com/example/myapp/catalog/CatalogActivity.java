package com.example.myapp.catalog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapp.BaseActivity;
import com.example.myapp.R;
import com.example.myapp.application.HomeApplication;
import com.example.myapp.constants.Urls;
import com.example.myapp.dto.category.CategoryAdapter;
import com.example.myapp.dto.category.CategoryItemDTO;
import com.example.myapp.service.CategoryNetwork;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatalogActivity extends BaseActivity {

    private ImageView ivSimple;
    CategoryAdapter categoryAdapter;
    private ListView categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

                categoryList = findViewById(R.id.categoryList);
        requestServer();

        String url = Urls.BASE+"/images/best.jpg";
        ivSimple = (ImageView)findViewById(R.id.ivSimple);
        Glide.with(HomeApplication.getAppContext())
                .load(url)
                .apply(new RequestOptions().override(600))
                .into(ivSimple);
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
                        categoryAdapter = new CategoryAdapter(HomeApplication.getAppContext(), R.layout.category_view, data);
                        categoryList.setAdapter(categoryAdapter);
                        //CategoryItemDTO one = data.get(0);
                        //one.
                    }

                    @Override
                    public void onFailure(Call<List<CategoryItemDTO>> call, Throwable t) {

                    }
                });
    }
}