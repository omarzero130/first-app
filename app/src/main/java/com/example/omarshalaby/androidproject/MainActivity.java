package com.example.omarshalaby.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText username;
    EditText password;
    Button Login;
    Button registerbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        Login=(Button) findViewById(R.id.login);
        registerbtn=(Button)findViewById(R.id.Registerbtn);
        Login.setOnClickListener(this);
        registerbtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.Registerbtn:
                i= new Intent(MainActivity.this,Register.class);
                startActivity(i);
                break;

            case R.id.login:
                Database db = new Database(this) ;
                int x = db.Login(username.getText().toString() , password.getText().toString()) ;
                Toast.makeText(this, x+""+username.getText().toString()+"   "+password.getText().toString(), Toast.LENGTH_LONG).show();
                if(x != -1){


                    ArrayList<Object> e= db.searchTest();
                    int flag=(int)e.get(0);
                    if(flag==0){

                        db.BigInsert();
                        db.inserttest(username.getText().toString(),1);

                    }

                    else{
                        db.inserttest(username.getText().toString(),1);
                    }


                     i=new Intent(this,ListActivity.class);
                    startActivityForResult(i,444);
                    finish();
                    break;
                }
                else{  Toast.makeText(this, "Wrong Username Or Password", Toast.LENGTH_LONG).show();}


                break;
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Database db=new Database(this);
        db.deleteOrderState();
        Log.d("my", " Destroyed");
        finish();


    }

    }

