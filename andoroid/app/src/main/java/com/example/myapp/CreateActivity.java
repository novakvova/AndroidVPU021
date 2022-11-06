package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.catalog.CatalogActivity;
import com.example.myapp.catalog.categorycard.CategoriesAdapter;
import com.example.myapp.dto.category.CategoryCreateDTO;
import com.example.myapp.dto.category.CategoryItemDTO;
import com.example.myapp.service.CategoryNetwork;
import com.example.myapp.utils.CommonUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateActivity extends BaseActivity {

    int SELECT_CROPPER = 300;
    Uri uri=null;
    ImageView IVPreviewImage;
    private TextInputEditText txtCategoryName;
    private TextInputLayout textFieldCategoryName;
    private TextView imageError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        IVPreviewImage=findViewById(R.id.IVPreviewImage);
        txtCategoryName=findViewById(R.id.txtCategoryName);
        textFieldCategoryName = findViewById(R.id.textFieldCategoryName);
        imageError = findViewById(R.id.textImageError);
    }


    public void handleCreateCategoryClick(View view)
    {

        CategoryCreateDTO categoryCreateDTO=new CategoryCreateDTO();
        categoryCreateDTO.setName(txtCategoryName.getText().toString());
        categoryCreateDTO.setImageBase64(uriGetBase64(uri));
        if(validationFields(categoryCreateDTO)) {
            CommonUtils.setContext(this);
            CommonUtils.showLoading();
            CategoryNetwork
                    .getInstance()
                    .getJSONApi()
                    .create(categoryCreateDTO)
                    .enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            CommonUtils.hideLoading();
                            Intent intent = new Intent(CreateActivity.this, CatalogActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            CommonUtils.hideLoading();
                        }
                    });
        }
    }

    private String uriGetBase64(Uri uri)
    {
        try{
            Bitmap bitmap= null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // initialize byte stream
            ByteArrayOutputStream stream=new ByteArrayOutputStream();
            // compress Bitmap
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
            // Initialize byte array
            byte[] bytes=stream.toByteArray();
            // get base64 encoded string
            String sImage= Base64.encodeToString(bytes, Base64.DEFAULT);
            return sImage;
        }
        catch (Exception ex) {
            return null;
        }

    }

    public void handleSelectImageClick(View view)
    {
        Intent intent = new Intent(this, ChangeImageActivity.class);
        startActivityForResult(intent, SELECT_CROPPER);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == SELECT_CROPPER) {
            //String base64 = data.getStringExtra("base64");
            uri = (Uri) data.getParcelableExtra("croppedUri");
            IVPreviewImage.setImageURI(uri);
            int a = 12;
            a = 16;
        }
    }

    private boolean validationFields(CategoryCreateDTO createCategoryDTO) {
        textFieldCategoryName.setError("");
        if (createCategoryDTO.getName().equals("")) {
            textFieldCategoryName.setError("Вкажіть Назву категорії");
            return false;
        }
        if (createCategoryDTO.getImageBase64() == null) {
            imageError.setVisibility(View.VISIBLE);
            return false;
        }
        return true;
    }

}