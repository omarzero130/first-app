package com.example.omarshalaby.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity implements View.OnClickListener {
  EditText username,password,confirm,email,age;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username=(EditText)findViewById(R.id.username_reg);
        password=(EditText)findViewById(R.id.password_reg);
        confirm=(EditText)findViewById(R.id.confirm);
        email=(EditText)findViewById(R.id.Email);
        age=(EditText)findViewById(R.id.Age);
        register = (Button) findViewById(R.id.Register_reg);
        register.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Register_reg:
                if(confirm.getText().toString().equals(password.getText().toString())){
                    if(!age.getText().toString().equals("") && !email.getText().toString().equals("") && !confirm.getText().toString().equals("") && !password.getText().toString().equals("") && !username.getText().toString().equals("")) {
                        try {
                            Database db = new Database(this);
                            Person p = new Person(username.getText().toString(), password.getText().toString(), email.getText().toString(), Integer.parseInt(age.getText().toString()));
                            db.insertPerson(p);
                            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();

                        } catch (Exception e) {
                            Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show();

                        }
                        Intent i = new Intent(this, MainActivity.class);
                        startActivity(i);
                        finish();


                    }
                    else
                        Toast.makeText(this, "Please enter all filled please ", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(this, "Please enter the same password in Filled RePassword", Toast.LENGTH_SHORT).show();
                break;
        }

    }

}
