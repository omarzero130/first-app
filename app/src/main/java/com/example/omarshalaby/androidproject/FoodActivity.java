package com.example.omarshalaby.androidproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.lang.Boolean.FALSE;


public class FoodActivity extends AppCompatActivity  implements View.OnClickListener{
    int RowNumber=0;
    ArrayList<Button> mainButton =new ArrayList<>();
    ArrayList<EditText> mainEdit=new ArrayList<>();

    final int TextID=0x23131;
    final int EditID=0x31123;
    final int ButtonID=0x31230;
    final int ButtonID2=0x31232;
    final int tempID=0x3;
    int tempID2=0x45;
    @SuppressLint("ResourceAsColor")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Database db = new Database(this);
        ArrayList<Categories> e = db.GetItems("Food");

        LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setWeightSum(90);

        if (e.get(0).getCatid() != -1) {


            ArrayList<LinearLayout> mainlayout = new ArrayList<>();
            int x = 0;
            for (int i = 0; i < e.size(); i++) {

                LinearLayout layout2 = new LinearLayout(this);
                layout2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,30f));
                layout2.setOrientation(LinearLayout.HORIZONTAL);
                layout2.setWeightSum(7);

                LinearLayout layoutText = new LinearLayout(this);
                layoutText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
                layoutText.setOrientation(LinearLayout.HORIZONTAL);


                LinearLayout layoutButton1 = new LinearLayout(this);
                layoutButton1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 2));
                layoutButton1.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout layoutButton2 = new LinearLayout(this);
                layoutButton2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 2));
                layoutButton2.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout layoutEdite = new LinearLayout(this);
                layoutEdite.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 2));
                layoutEdite.setOrientation(LinearLayout.HORIZONTAL);


                if (x == 0) {
                    layout2.setBackgroundColor(Color.parseColor("#ADD8E6"));

                    // layoutButton1.setBackgroundColor(R.color.colorCat);
                    // layoutButton2.setBackgroundColor(R.color.colorCat);
                    //  layoutEdite.setBackgroundColor(R.color.colorCat);
                    //  layoutText.setBackgroundColor(R.color.colorCat);
                } else if (x == 1) {
                    layout2.setBackgroundColor(Color.parseColor("#4682B4"));
                    // layoutButton1.setBackgroundColor(R.color.colorCat1);
                    // layoutButton2.setBackgroundColor(R.color.colorCat1);
                    // layoutEdite.setBackgroundColor(R.color.colorCat1);
                    // layoutText.setBackgroundColor(R.color.colorCat1);
                } else {
                    x = 0;
                    layout2.setBackgroundColor(Color.parseColor("#FF7F50"));
                    //layoutButton1.setBackgroundColor(R.color.colorCat2);
                    //layoutButton2.setBackgroundColor(R.color.colorCat2);
                    //layoutEdite.setBackgroundColor(R.color.colorCat2);
                    //layoutText.setBackgroundColor(R.color.colorCat2);
                }

                TextView text1 = new TextView(this);
                text1.setGravity(View.TEXT_ALIGNMENT_CENTER);
                text1.setTypeface(text1.getTypeface(), Typeface.BOLD);
                text1.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                text1.setTextSize(20);
                text1.setText(e.get(i).getCname());
                text1.setId(TextID + tempID2);

                layoutText.addView(text1);

                Button Button1 = new Button(this);
                Button1.setGravity(View.TEXT_ALIGNMENT_CENTER);
                text1.setTypeface(text1.getTypeface(), Typeface.BOLD);
                text1.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                Button1.setText("+");
                Button1.setId(ButtonID + tempID2);

                layoutButton1.addView(Button1);


                EditText Edit1 = new EditText(this);
                Edit1.setGravity(View.TEXT_ALIGNMENT_CENTER);
                Edit1.setTypeface(Edit1.getTypeface(), Typeface.BOLD);
                text1.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                Edit1.setInputType(InputType.TYPE_CLASS_NUMBER);
                Edit1.setText("0");
                Edit1.setId(EditID + tempID2);

                layoutEdite.addView(Edit1);


                Button Button2 = new Button(this);
                Button2.setGravity(View.TEXT_ALIGNMENT_CENTER);
                Button2.setTypeface(Button2.getTypeface(), Typeface.BOLD);
                text1.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                Button2.setText("-");
                Button2.setId(ButtonID2 + tempID2);

                layoutButton2.addView(Button2);
                layout2.addView(layoutText);
                layout2.addView(layoutButton1);
                layout2.addView(layoutEdite);
                layout2.addView(layoutButton2);


                mainlayout.add(layout2);

                tempID2 = tempID + tempID2;
                x++;
            }
            tempID2 = 0x45;

            RowNumber = e.size();

            for (int i = 0; i < mainlayout.size(); i++)
                layout.addView(mainlayout.get(i));

            LinearLayout layout3 = new LinearLayout(this);
            layout3.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            layout3.setOrientation(LinearLayout.HORIZONTAL);
            layout3.setWeightSum(2);


            LinearLayout layoutBut = new LinearLayout(this);
            layoutBut.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            layoutBut.setOrientation(LinearLayout.HORIZONTAL);
            Button Button2 = new Button(this);
            Button2.setGravity(View.TEXT_ALIGNMENT_CENTER);
            Button2.setTypeface(Button2.getTypeface(), Typeface.BOLD);
            Button2.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            Button2.setText("SaveToCart");
            Button2.setBackground(getResources().getDrawable(R.drawable.shape1));
            Button2.setId(R.id.SaveMe);
            layoutBut.addView(Button2);
            layout3.addView(layoutBut);


            LinearLayout layoutBut1 = new LinearLayout(this);
            layoutBut1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            layoutBut1.setOrientation(LinearLayout.HORIZONTAL);
            Button Button = new Button(this);
            Button.setGravity(View.TEXT_ALIGNMENT_CENTER);
            Button.setTypeface(Button.getTypeface(), Typeface.BOLD);
            Button.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            Button.setText("Back");
            Button.setBackground(getResources().getDrawable(R.drawable.shape1));
            Button.setId(R.id.Cancle);
            layoutBut1.addView(Button);
            layout3.addView(layoutBut1);

            layout.addView(layout3);

            setContentView(layout);


/**********************************************************************/


            for (int i = 0; i < RowNumber; i++) {

                mainButton.add((Button) findViewById(ButtonID2 + tempID2));
                mainButton.add((Button) findViewById(ButtonID + tempID2));
                mainEdit.add((EditText) findViewById(EditID + tempID2));
                tempID2 = tempID2 + tempID;

            }

            mainButton.add((Button) findViewById(R.id.SaveMe));
            mainButton.add((Button) findViewById(R.id.Cancle));

            for (int i = 0; i < mainButton.size(); i++) {

                mainButton.get(i).setOnClickListener(this);

            }


        } else {

        }
    }

    @Override
    public void onClick(View v) {
        int x;
        Database db = new Database(this);
        tempID2 = 0x45;
        if (v.getId() == R.id.SaveMe) {
            Order c;
            String Cname;
            for (int i = 0; i < RowNumber; i++) {
                x = Integer.parseInt(mainEdit.get(i).getText().toString());
                if (x != 0) {

                    int CID;
                    Person temp1;
                    TextView t = (TextView) findViewById(TextID + tempID2);
                    Cname = t.getText().toString();
                    CID = db.GetItemId(Cname);
                    ArrayList<Object> temp = db.searchTest();
                    temp1 = db.searchPersonID(temp.get(1).toString());
                    if (temp1.getUid() != -1) {

                        if (CID != 0) {
                            // Toast.makeText(this,Integer.parseInt(mainEdit.get(i).getText().toString())+"",Toast.LENGTH_SHORT).show();
                            c = new Order(Integer.parseInt(mainEdit.get(i).getText().toString()), CID, temp1.getUid());
                            c.setState(FALSE);
                            db.insertOrder(c);
                            mainEdit.get(i).setText("0");
                        }
                    }
                }
                tempID2 = tempID + tempID2;

            }
            Toast.makeText(this, "Added To Cart Successfully ", Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.Cancle) {
            finish();
        } else {
            for (int i = 0; i < RowNumber; i++) {

                if (v.getId() == ButtonID2 + tempID2) {

                    x = Integer.parseInt(mainEdit.get(i).getText().toString());
                    if (x == 0) {
                        mainEdit.get(i).setText("0");
                    } else {
                        x = Integer.parseInt(mainEdit.get(i).getText().toString());
                        --x;
                        mainEdit.get(i).setText(x + "");
                    }

                } else if (v.getId() == ButtonID + tempID2) {

                    x = Integer.parseInt(mainEdit.get(i).getText().toString());
                    ++x;
                    mainEdit.get(i).setText(x + "");

                }


                tempID2 = tempID + tempID2;
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
        return true;
    }
}
