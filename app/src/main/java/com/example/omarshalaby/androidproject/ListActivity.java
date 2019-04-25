package com.example.omarshalaby.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class ListActivity extends AppCompatActivity implements View.OnClickListener {


    ImageButton btn1,btn2,btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        btn1=(ImageButton)findViewById(R.id.food);
        btn2=(ImageButton)findViewById(R.id.sports);
        btn3=(ImageButton)findViewById(R.id.cart);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.food:
                i =new Intent(this,FoodActivity.class);
                startActivity(i);
                break;
            case R.id.sports:
                i =new Intent(this,SportsActivity.class);
                startActivity(i);
                break;
            case R.id.cart:
                i =new Intent(this,CartActivity.class);
                startActivity(i);
                break;
        }



    }
}
