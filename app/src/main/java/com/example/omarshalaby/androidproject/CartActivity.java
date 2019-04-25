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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class CartActivity extends AppCompatActivity implements View.OnClickListener{
    int RowNumber=0;
    ArrayList<Button> mainButton =new ArrayList<Button>();
    ArrayList<EditText> mainEdit=new ArrayList<>();
    final int TextID=0x23135;
    final int EditID=0x31145;
    final int ButtonID=0x312322;
    final int ButtonID2=0x31235;
    final int tempID=0x33;
    int tempID2 =0x45;

    Person PerID;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Database db=new Database(this);
        ArrayList<Object> Per=db.searchTest();
        PerID=db.searchPersonID(Per.get(1).toString());

        ArrayList<Order> e=db.searchOrder(FALSE,PerID.getUid());


        LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
        layout.setOrientation(LinearLayout.VERTICAL);

        ScrollView scroll = new ScrollView(this);




        TextView text10=new TextView(this);
        text10.setGravity(View.TEXT_ALIGNMENT_CENTER);
        text10.setTypeface(text10.getTypeface() , Typeface.BOLD);

        text10.setTextSize(20);
        text10.setText("Please Edit And Save Orders Or Delete them ");

        layout.addView(text10);



        if(e.get(0).getCid()!=-1) {


            ArrayList<LinearLayout> mainlayout = new ArrayList<>();
            int x=0;
            for(int i=0;i<e.size();i++) {

                LinearLayout layout2 = new LinearLayout(this);
                layout2.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                layout2.setOrientation(LinearLayout.HORIZONTAL);
                layout2.setWeightSum(7);

                LinearLayout layoutText = new LinearLayout(this);
                layoutText.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1));
                layoutText.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout layoutButton1 = new LinearLayout(this);
                layoutButton1.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,2));
                layoutButton1.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout layoutButton2 = new LinearLayout(this);
                layoutButton2.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,2));
                layoutButton2.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout layoutEdite = new LinearLayout(this);
                layoutEdite.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,2));
                layoutEdite.setOrientation(LinearLayout.HORIZONTAL);


                if(x==0){
                    layout2.setBackgroundColor(Color.parseColor("#ff1a1a"));
                    // layoutButton1.setBackgroundColor(R.color.colorCat);
                    // layoutButton2.setBackgroundColor(R.color.colorCat);
                    //  layoutEdite.setBackgroundColor(R.color.colorCat);
                    //  layoutText.setBackgroundColor(R.color.colorCat);
                }
                else if(x==1) {
                    layout2.setBackgroundColor(Color.parseColor("#0040ff"));
                    // layoutButton1.setBackgroundColor(R.color.colorCat1);
                    // layoutButton2.setBackgroundColor(R.color.colorCat1);
                    // layoutEdite.setBackgroundColor(R.color.colorCat1);
                    // layoutText.setBackgroundColor(R.color.colorCat1);
                }
                else{
                    x=0;
                    layout2.setBackgroundColor(Color.parseColor("#66ccff"));
                    //layoutButton1.setBackgroundColor(R.color.colorCat2);
                    //layoutButton2.setBackgroundColor(R.color.colorCat2);
                    //layoutEdite.setBackgroundColor(R.color.colorCat2);
                    //layoutText.setBackgroundColor(R.color.colorCat2);
                }

                TextView text1=new TextView(this);
                text1.setGravity(View.TEXT_ALIGNMENT_CENTER);
                text1.setTypeface(text1.getTypeface() , Typeface.BOLD);

                text1.setTextSize(20);
                text1.setText(db.GetItemName(e.get(i).getCid()));
                text1.setId(TextID+ tempID2);

                layoutText.addView(text1);

                Button Button1=new Button(this);
                Button1.setGravity(View.TEXT_ALIGNMENT_CENTER);
                Button1.setTypeface(Button1.getTypeface() , Typeface.BOLD);

                Button1.setText("+");
                Button1.setId(ButtonID+ tempID2);

                layoutButton1.addView(Button1);


                EditText Edit1=new EditText(this);
                Edit1.setGravity(View.TEXT_ALIGNMENT_CENTER);
                Edit1.setTypeface(Edit1.getTypeface() , Typeface.BOLD);

                Edit1.setInputType(InputType.TYPE_CLASS_NUMBER);
                Edit1.setText(Integer.toString(e.get(i).getNumberOfitem()));
                Edit1.setId(EditID+ tempID2);

                layoutEdite.addView(Edit1);


                Button Button2=new Button(this);
                Button2.setGravity(View.TEXT_ALIGNMENT_CENTER);
                Button2.setTypeface(Button2.getTypeface() , Typeface.BOLD);

                Button2.setText("-");
                Button2.setId(ButtonID2+ tempID2);

                layoutButton2.addView(Button2);
                layout2.addView(layoutText);
                layout2.addView(layoutButton1);
                layout2.addView(layoutEdite);
                layout2.addView(layoutButton2);


                mainlayout.add(layout2);

                tempID2 =tempID+ tempID2;
                x++;
            }
            tempID2 =0x45;

            RowNumber=e.size();

            for (int i=0;i<mainlayout.size();i++) {
                layout.addView(mainlayout.get(i));
            }
            LinearLayout layout3 = new LinearLayout(this);
            layout3.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            layout3.setOrientation(LinearLayout.HORIZONTAL);
            layout3.setWeightSum(2);


            LinearLayout layoutBut = new LinearLayout(this);
            layoutBut.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1));
            layoutBut.setOrientation(LinearLayout.HORIZONTAL);
            Button Button2=new Button(this);
            Button2.setGravity(View.TEXT_ALIGNMENT_CENTER);
            Button2.setTypeface(Button2.getTypeface() , Typeface.BOLD);

            Button2.setText("Save Orders");
            Button2.setId(R.id.Savecart);
            layoutBut.addView(Button2);
            layout3.addView(layoutBut);


            LinearLayout layoutBut1 = new LinearLayout(this);
            layoutBut1.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1));
            layoutBut1.setOrientation(LinearLayout.HORIZONTAL);
            Button Button=new Button(this);
            Button.setGravity(View.TEXT_ALIGNMENT_CENTER);
            Button.setTypeface(Button.getTypeface() , Typeface.BOLD);

            Button.setText("Delete Orders");
            Button.setId(R.id.DeleteMe);
            layoutBut1.addView(Button);
            layout3.addView(layoutBut1);

            layout.addView(layout3);

            scroll.addView(layout);
            setContentView(scroll);





            for(int i=0;i<RowNumber;i++){

                mainButton.add((Button) findViewById(ButtonID2+ tempID2));
                mainButton.add((Button) findViewById(ButtonID+ tempID2));
                mainEdit.add((EditText) findViewById(EditID+ tempID2));
                tempID2 = tempID2 +tempID;

            }

            mainButton.add((Button) findViewById(R.id.Savecart));
            mainButton.add((Button) findViewById(R.id.DeleteMe));


            for(int i=0;i<mainButton.size();i++){
                mainButton.get(i).setOnClickListener(this);

            }


        }
        else{
            Toast.makeText(this,"Cart is Empty Please pre an orders" ,Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onClick(View v) {
        int x;
        Database db=new Database(this);
        tempID2=0x45;
        if(v.getId()==R.id.Savecart){
            String Cname;
            for(int i=0;i<RowNumber;i++){
                x=Integer.parseInt(mainEdit.get(i).getText().toString());
                if(x!=0){

                    int CID;
                    Person temp1;
                    TextView t=(TextView) findViewById(TextID+tempID2);
                    Cname=t.getText().toString();
                    CID=db.GetItemId(Cname);
                    if(PerID.getUid()!=-1) {

                        if (CID != 0) {
                            Order e=db.searchDeleteorder(CID,PerID.getUid(),FALSE);
                            if(!e.isState()){
                                if(e.getCid()!=-1){
                                    db.deleteOrder(e.getOid());
                                    e=new Order(Integer.parseInt(mainEdit.get(i).getText().toString()),CID,PerID.getUid());
                                    e.setState(TRUE);
                                    db.insertOrder(e);
                                }
                            }
                        }
                    }
                }
                else{
                    TextView t=(TextView) findViewById(TextID+tempID2);
                    Toast.makeText(this,"Can't Save Item "+ t.getText().toString()+"Due to Entered 0 NumberOf This Item",Toast.LENGTH_SHORT).show();
                }
                tempID2=tempID+tempID2;

            }
            Toast.makeText(this,"Order Have Been Saved Successfully",Toast.LENGTH_SHORT).show();

            finish();

        }
        else if(v.getId()==R.id.DeleteMe){

            String Cname,DeleteName="",numDelete="";


            for(int i=0;i<RowNumber;i++){
                x=Integer.parseInt(mainEdit.get(i).getText().toString());

                int CID;
                Person temp1;
                TextView t=(TextView) findViewById(TextID+tempID2);
                Cname=t.getText().toString();
                CID=db.GetItemId(Cname);
                if(PerID.getUid()!=-1) {

                    if (CID != 0) {
                        Order e=db.searchDeleteorder(CID,PerID.getUid(),FALSE);
                        if(!e.isState()){
                            if(e.getCid()!=-1){
                                DeleteName=DeleteName+t.getText().toString()+"  ";
                                numDelete=numDelete+e.getNumberOfitem()+"  ";
                                db.deleteOrder(e.getOid());

                            }
                        }
                    }
                }

                tempID2=tempID+tempID2;

            }
            Toast.makeText(this,"The Items Have Been Deleted is ",Toast.LENGTH_SHORT).show();
            Toast.makeText(this,DeleteName+"with NumberItems = \n "+numDelete,Toast.LENGTH_SHORT).show();

            finish();
        }
        else{
            for(int i=0;i<RowNumber;i++){

                if(v.getId()==ButtonID2+tempID2){

                    x= Integer.parseInt(mainEdit.get(i).getText().toString());
                    if(x==0){
                        mainEdit.get(i).setText("0");
                    }

                    else{
                        x= Integer.parseInt(mainEdit.get(i).getText().toString());
                        --x;
                        mainEdit.get(i).setText(x+"");
                    }

                }
                else if(v.getId()==ButtonID+tempID2){

                    x= Integer.parseInt(mainEdit.get(i).getText().toString());
                    ++x;
                    mainEdit.get(i).setText(x+"");

                }


                tempID2=tempID+tempID2;
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
        int id=item.getItemId();
        Intent i;
        switch (id){
            case R.id.History:
                i=new Intent(this,historyActivity.class);
                startActivity(i);
                finish();
                break;
            case R.id.Profile:
                i=new Intent(this,ProfileActivity.class);
                startActivity(i);
                finish();
                break;
        }
        return true;
    }
}
