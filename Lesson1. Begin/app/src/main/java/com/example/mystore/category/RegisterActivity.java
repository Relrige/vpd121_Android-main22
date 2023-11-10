package com.example.mystore.category;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mystore.R;

public class RegisterActivity extends AppCompatActivity {

    private static final int SELECT_PHOTO_REQUEST = 1;
    private ImageView imageViewSelectedPhoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        Button buttonSelectPhoto = findViewById(R.id.buttonSelectPhoto);
        imageViewSelectedPhoto = findViewById(R.id.imageViewSelectedPhoto);

        buttonSelectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPhoto();
            }
        });

        Button buttonRegister = findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle registration logic, including uploaded photo (imageViewSelectedPhoto)
            }
        });
    }

    private void selectPhoto() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECT_PHOTO_REQUEST && resultCode == RESULT_OK) {
            if (data != null) {
                Uri selectedImage = data.getData();
                imageViewSelectedPhoto.setImageURI(selectedImage);
            }
        }
    }
}
