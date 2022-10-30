package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class CreateActivity extends AppCompatActivity {

    int SELECT_CROPPER = 300;
    ImageView IVPreviewImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        IVPreviewImage=findViewById(R.id.IVPreviewImage);
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
            Uri uri = (Uri) data.getParcelableExtra("croppedUri");
            IVPreviewImage.setImageURI(uri);
            int a = 12;
            a = 16;
        }
    }

}