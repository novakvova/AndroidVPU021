package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;

import com.example.myapp.utils.CommonUtils;
import com.oginotihiro.cropview.CropUtil;
import com.oginotihiro.cropview.CropView;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class ChangeImageActivity extends AppCompatActivity {

    private static int RESULT_LOAD_IMAGE = 1;
    public static String base64="";
private CropView cropView;
    private Bitmap croppedBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_image);
        cropView = (CropView) findViewById(R.id.cropView);

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
            cropView.of(selectedImage).asSquare().initialize(this);
//            cropView.of(selectedImage)
//                    //.withAspect(x, y)
//                    .withOutputSize(100, 100)
//                    .initialize(this);
            CommonUtils.hideLoading();
        }
    }
    public void RotateRightImage(View view) {
        //CropView cropView = (CropView) findViewById(R.id.cropView);
        cropView.setRotation(cropView.getRotation()+90);
    }
    public void RotateLeftImage(View view) {
        //CropView cropView = (CropView) findViewById(R.id.cropView);
        cropView.setRotation(cropView.getRotation()-90);
    }
    public void ChangeImage(View view) {
        CommonUtils.showLoading();

        String fileTemp = java.util.UUID.randomUUID().toString();
        Bitmap croppedBitmap = cropView.getOutput();
                Matrix matrix = new Matrix();
       matrix.postRotate(cropView.getRotation());
       Bitmap rotatedBitmap = Bitmap.createBitmap(croppedBitmap, 0, 0, croppedBitmap.getWidth(), croppedBitmap.getHeight(), matrix, true);

        Uri destination = Uri.fromFile(new File(getCacheDir(), fileTemp));
        CropUtil.saveOutput(this, destination, rotatedBitmap, 90);
        CommonUtils.hideLoading();

//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        rotatedBitmap.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
//        byte[] byteArray = byteArrayOutputStream.toByteArray();
//        String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
//        int a = 32;
        Intent intent = new Intent();
//        intent.putExtra("base64", encoded);
        intent.putExtra("croppedUri", destination);
       setResult(300, intent);
        finish();
    }
}