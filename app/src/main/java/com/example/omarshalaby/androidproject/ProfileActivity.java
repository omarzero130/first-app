package com.example.omarshalaby.androidproject;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    Button camera;
    ImageView Img;
    TextView t,t1,t2,t3;
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    ArrayList<Object> test;
    Database db=new Database(this);
    Person PerID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        test=db.searchTest();
        String  id=test.get(1).toString();
        PerID=db.searchPersonID(id);
        t=(TextView)findViewById(R.id.username_profile);
        t.setText(PerID.getUsername());
       t1=(TextView)(findViewById(R.id.password_profile));
       t1.setText(PerID.getPass());
        t2=(TextView)findViewById(R.id.email_profile);
        t2.setText(PerID.getEmail());
        t3=(TextView)(findViewById(R.id.Age_profile));
       t3.setText(""+PerID.getAge());

        camera=(Button) (findViewById(R.id.Photo));
        camera.setOnClickListener(this);

        Img=(ImageView) (findViewById(R.id.imagee));
        if(!PerID.getPhoto().equals("no")){
            Img.setImageBitmap(StringToBitMap(PerID.getPhoto()));

        }


    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");

            Img.setImageBitmap(photo);
            String s=getPngAsString(photo);
            PerID.setPhoto(s);
            db.updatePeson(PerID);
            Toast.makeText(this,"Done",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new
                        Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();

            }

        }


    }
    public Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }
    public static String getPngAsString(Bitmap bitmap){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
        byte[] bitmapBytes = bos.toByteArray();
        return Base64.encodeToString(bitmapBytes, Base64.NO_WRAP);
    }

    }