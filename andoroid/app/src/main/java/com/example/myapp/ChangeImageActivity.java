package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.myapp.utils.CommonUtils;
import com.oginotihiro.cropview.CropView;

public class ChangeImageActivity extends AppCompatActivity {

    private static int RESULT_LOAD_IMAGE = 1;
    public static String base64="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_image);

        CommonUtils.setContext(this);

        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }
    //Вибір фото в галереї
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            CommonUtils.showLoading();
            Uri selectedImage = data.getData();
            CropView cropView = (CropView) findViewById(R.id.cropView);
            cropView.of(selectedImage)
                    //.withAspect(x, y)
                    .withOutputSize(100, 100)
                    .initialize(this);
            CommonUtils.hideLoading();
        }
    }
    public void RotateRightImage(View view) {
        CropView cropView = (CropView) findViewById(R.id.cropView);
        cropView.setRotation(cropView.getRotation()+90);
    }
    public void RotateLeftImage(View view) {
        CropView cropView = (CropView) findViewById(R.id.cropView);
        cropView.setRotation(cropView.getRotation()-90);
    }
}