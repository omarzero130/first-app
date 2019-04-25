package com.example.omarshalaby.androidproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class SportsActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {
    final  int MyID=0x7323;
    final int But=0x2222;
    final int sum=0x21;
    Database db=new Database(this);
    Spinner MyItems;
    String NameItem;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Categories> Item=db.GetItems("Sport");

        Order ord;
        Person p;
        int ItemID;
        ArrayList<Object> w=db.searchTest();
        String ItemName[]=new String[Item.size()];
        p=db.searchPersonID(w.get(1).toString());
        ord=db.Searchorder2(-1,p.getUid(),FALSE,-1);




        if(Item.get(0).getCatid()!=-1) {

            if(ord.getOid()!=-1){
                for(int i=0;i<Item.size();i++){
                    if(ord.getCid()==Item.get(i).getCatid()){
                        Item.remove(i);
                        ItemName[0]=db.GetItemName(ord.getCid());
                    }
                }
                for(int i=1;i<=Item.size();i++) {
                    ItemName[i] = Item.get(i - 1).getCname();
                }
            }
            else {
                for (int i = 0; i < Item.size(); i++)
                    ItemName[i] = Item.get(i).getCname();
            }


            LinearLayout layout = new LinearLayout(this);

            layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setGravity(View.TEXT_ALIGNMENT_CENTER);
            layout.setWeightSum(100);


            MyItems=new Spinner(this);
            MyItems.setId(MyID);
            MyItems.setGravity(View.TEXT_ALIGNMENT_CENTER);
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_spinner_item, ItemName);
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            MyItems.setAdapter(spinnerArrayAdapter);

            layout.addView(MyItems);

            LinearLayout layout3 = new LinearLayout(this);
            layout3.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,50));
            layout3.setOrientation(LinearLayout.HORIZONTAL);
            layout3.setWeightSum(2);


            Button Button2=new Button(this);
            Button2.setGravity(View.TEXT_ALIGNMENT_CENTER);
            Button2.setTypeface(Button2.getTypeface() , Typeface.BOLD);

            Button2.setText("Save Selection");
            Button2.setId(But);

            LinearLayout layoutBut = new LinearLayout(this);
            layoutBut.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1));
            layoutBut.setOrientation(LinearLayout.HORIZONTAL);
            layoutBut.addView(Button2);
            layout3.addView(layoutBut);

            LinearLayout layoutBut1 = new LinearLayout(this);
            layoutBut1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1));
            layoutBut1.setOrientation(LinearLayout.HORIZONTAL);
            Button Button=new Button(this);
            Button.setGravity(View.TEXT_ALIGNMENT_CENTER);
            Button.setTypeface(Button.getTypeface() , Typeface.BOLD);
            Button.setTextColor(getResources().getColor(R.color.black));
            Button.setText("Back");
            Button.setId(But+sum);
            layoutBut1.addView(Button);
            layout3.addView(layoutBut1);

            layout.addView(layout3);

           /* Button b=new Button(this);
                    b=(Button) findViewById(But);
            Button b2=new Button(this);
                    b=(Button) findViewById(But+sum);*/
            Button.setOnClickListener(this);
            Button2.setOnClickListener(this);
            MyItems.setOnItemSelectedListener(this);
            setContentView(layout);




        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent i;
        switch (id) {
            case R.id.History:
                i = new Intent(this, historyActivity.class);
                startActivity(i);
                finish();
                break;
            case R.id.Profile:
                i = new Intent(this, ProfileActivity.class);
                startActivity(i);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onClick(View v) {
        Person PerID;
        String username;
        int Itemid;
        switch (v.getId()){
            case But:
                ArrayList<Object> e=db.searchTest();
                username=e.get(1).toString();
                PerID=db.searchPersonID(username);


                Itemid=db.GetItemId(NameItem);

                Order ord=db.Searchorder2(-1,PerID.getUid(),FALSE,-1);
                if(ord.getOid()!=-1){
                    db.deleteOrder(ord.getOid());
                }

                ord=new Order(-1,Itemid,PerID.getUid());
                ord.setState(TRUE);
                ord.setNumberOfitem(-1);
                db.insertOrder(ord);
                Toast.makeText(this,"Saved Successfully", Toast.LENGTH_SHORT).show();
                break;
            case But+sum:
                finish();
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        NameItem=MyItems.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
