package com.example.omarshalaby.androidproject;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static java.lang.Boolean.TRUE;

public class historyActivity extends AppCompatActivity {

    int RowNumber=0;
    ArrayList<EditText> mainEdit=new ArrayList<>();
    final int TextID=0x23135;
    final int EditID=0x31145;
    final int ButtonID=0x312322;
    final int ButtonID2=0x31235;
    final int tempID=0x33;
    int tempID2 =0x45;

    int sport=-1;

    Person PerID;
    @SuppressLint("ResourceAsColor")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Database db = new Database(this);
        ArrayList<Object> Per = db.searchTest();
        PerID = db.searchPersonID(Per.get(1).toString());

        ArrayList<Order> e = db.searchOrder(TRUE, PerID.getUid());


        LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setWeightSum(100);

        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(getResources().getColor(R.color.colorback));


        TextView text10 = new TextView(this);
        text10.setGravity(View.TEXT_ALIGNMENT_CENTER);
        text10.setTypeface(text10.getTypeface(), Typeface.BOLD);
        text10.setTextColor(getResources().getColor(R.color.black));
        text10.setTextSize(20);
        text10.setText("Your Saved Successfully Orders ");

        layout.addView(text10);
        if (e.get(0).getCid() != -1) {

            int count =e.size();
            ArrayList<LinearLayout> mainlayout = new ArrayList<>();
            int x = 0;
            for (int i = 0; i <= e.size(); i++) {

                if(i==e.size()){
                    Toast.makeText(this, " items= " + i, Toast.LENGTH_SHORT).show();

                    if(sport==-1){
                        break;}
                    LinearLayout layout3 = new LinearLayout(this);
                    layout3.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,25f));
                    layout3.setOrientation(LinearLayout.HORIZONTAL);
                    layout3.setWeightSum(100);





                    if (x == 0) {
                        layout3.setBackgroundColor(Color.parseColor("#ADD8E6"));
                        // layoutButton1.setBackgroundColor(R.color.colorCat);
                        // layoutButton2.setBackgroundColor(R.color.colorCat);
                        //  layoutEdite.setBackgroundColor(R.color.colorCat);
                        //  layoutText.setBackgroundColor(R.color.colorCat);
                    } else if (x == 1) {
                        layout3.setBackgroundColor(Color.parseColor("#4682B4"));
                        // layoutButton1.setBackgroundColor(R.color.colorCat1);
                        // layoutButton2.setBackgroundColor(R.color.colorCat1);
                        // layoutEdite.setBackgroundColor(R.color.colorCat1);
                        // layoutText.setBackgroundColor(R.color.colorCat1);
                    } else {
                        x = 0;
                        layout3.setBackgroundColor(Color.parseColor("#FF7F50"));
                        //layoutButton1.setBackgroundColor(R.color.colorCat2);
                        //layoutButton2.setBackgroundColor(R.color.colorCat2);
                        //layoutEdite.setBackgroundColor(R.color.colorCat2);
                        //layoutText.setBackgroundColor(R.color.colorCat2);
                    }

                    LinearLayout layoutText = new LinearLayout(this);
                    layoutText.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 50));
                    layoutText.setOrientation(LinearLayout.HORIZONTAL);


                    TextView text1 = new TextView(this);
                    text1.setGravity(View.TEXT_ALIGNMENT_CENTER);
                    text1.setTypeface(text1.getTypeface(), Typeface.BOLD);
                    text1.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    text1.setTextSize(20);
                    text1.setText(db.GetItemName(e.get(sport).getCid()));
                    text1.setId(TextID + tempID2);

                    layoutText.addView(text1);

                    LinearLayout layoutEdite = new LinearLayout(this);
                    layoutEdite.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 50));
                    layoutEdite.setOrientation(LinearLayout.HORIZONTAL);


                    EditText Edit1 = new EditText(this);
                    Edit1.setGravity(View.TEXT_ALIGNMENT_CENTER);
                    Edit1.setTypeface(Edit1.getTypeface(), Typeface.BOLD);
                    text1.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                    Edit1.setText("Sport Type");
                    Edit1.setId(EditID + tempID2);
                    Edit1.setTag(Edit1.getKeyListener());
                    Edit1.setKeyListener(null);

                    layoutEdite.addView(Edit1);


                    layout3.addView(layoutText);
                    layout3.addView(layoutEdite);


                    mainlayout.add(layout3);

                    tempID2 = tempID + tempID2;
                    x++;

                }

                else if (e.get(i).isState()==TRUE) {


                    if(e.get(i).getNumberOfitem()==-1) {
                        sport=i;
                        continue;
                    }

                    LinearLayout layout2 = new LinearLayout(this);
                    layout2.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    layout2.setOrientation(LinearLayout.HORIZONTAL);
                    layout2.setWeightSum(7);

                    LinearLayout layoutText = new LinearLayout(this);
                    layoutText.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
                    layoutText.setOrientation(LinearLayout.HORIZONTAL);

                    LinearLayout layoutButton1 = new LinearLayout(this);
                    layoutButton1.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 2));
                    layoutButton1.setOrientation(LinearLayout.HORIZONTAL);

                    LinearLayout layoutButton2 = new LinearLayout(this);
                    layoutButton2.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 2));
                    layoutButton2.setOrientation(LinearLayout.HORIZONTAL);

                    LinearLayout layoutEdite = new LinearLayout(this);
                    layoutEdite.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 2));
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
                    text1.setText(db.GetItemName(e.get(i).getCid()));
                    text1.setId(TextID + tempID2);

                    layoutText.addView(text1);


                    EditText Edit1 = new EditText(this);
                    Edit1.setGravity(View.TEXT_ALIGNMENT_CENTER);
                    Edit1.setTypeface(Edit1.getTypeface(), Typeface.BOLD);
                    text1.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                    Edit1.setInputType(InputType.TYPE_CLASS_NUMBER);
                    Edit1.setText(Integer.toString(e.get(i).getNumberOfitem()));
                    Edit1.setId(EditID + tempID2);
                    Edit1.setTag(Edit1.getKeyListener());
                    Edit1.setKeyListener(null);

                    layoutEdite.addView(Edit1);


                    layout2.addView(layoutText);
                    layout2.addView(layoutButton1);
                    layout2.addView(layoutEdite);
                    layout2.addView(layoutButton2);


                    mainlayout.add(layout2);

                    tempID2 = tempID + tempID2;
                    x++;
                }
            }
            tempID2 = 0x45;

            RowNumber = e.size();

            for (int i = 0; i < mainlayout.size(); i++) {
                layout.addView(mainlayout.get(i));
            }




            scroll.addView(layout);
            setContentView(scroll);


/**********************************************************************/


        }
        else {
            Toast.makeText(this, "No history yet please select some items first !" , Toast.LENGTH_SHORT).show();

        }

    }

    }

