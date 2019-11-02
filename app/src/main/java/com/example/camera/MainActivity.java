package com.example.camera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mTakePictureButton;
    private ImageView mCameraPicture;

    private static int REQUEST_CODE_TAKE_PICTURE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCameraPicture = findViewById(R.id.camera_thumbnail_picture); //connect to front end picture
        mTakePictureButton = findViewById(R.id.take_picture_button); //connect to front end button

        mTakePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeThumbnailPicture(); //on click, send to thumbnail method
            }
        });
    }

    private void takeThumbnailPicture() {

        Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //image capture intent

        if(pictureIntent.resolveActivity(getPackageManager()) != null){ //check if you can take a picture
            startActivityForResult(pictureIntent, REQUEST_CODE_TAKE_PICTURE); //send to activity to create thumbnail
        } else {
            Toast.makeText(this, "Your device does not have a camera app", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_TAKE_PICTURE && resultCode == RESULT_OK) { //take result from thumbnail image capture
            Bitmap thumbnail = data.getParcelableExtra("data"); //take result info and create thumbnail
            mCameraPicture.setImageBitmap(thumbnail); //add thumbnail to screen
        }
    }
}
