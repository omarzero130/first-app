package com.example.omarshalaby.androidproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.TRUE;

/**
 * Created by omar shalaby on 9/24/2018.
 */

public class Database extends SQLiteOpenHelper {
    public Database(Context e){
        super(e,"storage",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {



        String Pquery = "create Table Person(PersonID integer primary key autoincrement,Username varchar(100) not null unique,Password varchar(100) not null" +
                ",Email varchar(100) not null unique,Age integer(3),Photo varchar(100));";
        db.execSQL(Pquery);

        String Cquery = "create Table Catigries(CatigoryID integer primary key autoincrement,Catigoryname varchar(100) not null" +
                ",Catigory varchar(100) not null);";
        db.execSQL(Cquery);


        String testquery = "create Table test(testid integer primary key autoincrement , loginuser varchar(100) , flag integer)" ;
        db.execSQL(testquery);


        String Oquery = "create Table Orders(PID integer(3) ,CID integer(3)" +
                ",State varchar(100)" +
                ",NumberOfOreder varchar(100)" +
                ",OID integer(3) " +
                ",PRIMARY KEY (PID,CID,OID));";
        db.execSQL(Oquery);


       /* Categories x=new Categories("Food","Chicken");
        insertCat(x);
        x=new Categories("Food","Rise");
        insertCat(x);
        x=new Categories("Food","Meet");
        insertCat(x);
        x=new Categories("Sport","Football");
        insertCat(x);
        x=new Categories("Sport","Basketball");
        insertCat(x);
        x=new Categories("Sport","Vollyball");
        insertCat(x);

*/    }


    public void BigInsert (){

        Categories x=new Categories("Food","Chicken");
        insertCat(x);
        x=new Categories("Food","Rise");
        insertCat(x);
        x=new Categories("Food","Meet");
        insertCat(x);
        x=new Categories("Sport","Football");
        insertCat(x);
        x=new Categories("Sport","Basketball");
        insertCat(x);
        x=new Categories("Sport","Vollyball");
        insertCat(x);
    }


    public ArrayList<Object> searchTest(){

        SQLiteDatabase db = getWritableDatabase();
        String[] cols;
        cols = new String[]{"MAX(testid),loginuser,flag"};
        Cursor c = db.query("test", cols ,null, null, null, null, null);
        int userDidx = c.getColumnIndex("loginuser");
        int flagDidx = c.getColumnIndex("flag");
        ArrayList<Object> e=new ArrayList<Object>();

        if (!c.isAfterLast()) {
            c.moveToNext();
            int flag = c.getInt(flagDidx);
            String user=c.getString(userDidx);
            e.add(flag);
            e.add(user);
            return e;
        } else {
            e.add(0);
            return e;
        }

    }

    public void inserttest(String user,int flag){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("flag", flag);
        cv.put("loginuser", user);
        db.insert("test", null, cv);


    }


    public void insertPerson(Person s,String ...photo) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Username", s.getUsername());
        cv.put("Age", s.getAge());
        cv.put("Password", s.getPass());
        cv.put("Email", s.getEmail());
        if(photo.length==1){
            cv.put("Photo",photo[0]);
        }
        else
            cv.put("Photo","no");
        db.insert("Person", null, cv);
    }

    public void insertCat(Categories s) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Catigory", s.getCategoury());
        cv.put("Catigoryname", s.getCname());
        db.insert("Catigries", null, cv);
    }

    public void insertOrder(Order s) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        int m;
        m = autogentration();



        cv.put("CID", s.getCid());
        cv.put("PID", s.getPid());
        cv.put("NumberOfOreder",s.getNumberOfitem());
        cv.put("State",""+s.isState());
        if (m != -1) {
            cv.put("OID", m);
            db.insert("Orders", null, cv);
        } else {
            cv.put("OID", 1);
            db.insert("Orders", null, cv);

        }
    }


    public void deletePerson(int id) {
        SQLiteDatabase db = getWritableDatabase();
        //the same but without using the fourth paramter,because it is optional ,it is  ecessary for security only
        db.delete("Person", "PersonID=" + id, null);
    }

    public void deleteCat(int id) {
        SQLiteDatabase db = getWritableDatabase();
        //the same but without using the fourth paramter,because it is optional ,it is  ecessary for security only
        db.delete("Catigries", "CatigoryID=" + id, null);
    }

    public void deleteOrder(int id) {
        SQLiteDatabase db = getWritableDatabase();
        //the same but without using the fourth paramter,because it is optional ,it is  ecessary for security only
        db.delete("Orders", "OID=" + id, null);
    }

    public void deleteOrderState() {
        SQLiteDatabase db = getWritableDatabase();
        String d[]={"FALSE"};
        //the same but without using the fourth paramter,because it is optional ,it is  ecessary for security only
        db.delete("Orders", "State LIKE" + "?", d);
    }

    public ArrayList<Order> searchOrder(boolean check, int... id) {
        SQLiteDatabase db = getWritableDatabase();
        String[] cols = {"OID,PID,CID,NumberOfOreder,State"};
        ArrayList<Order> orders = new ArrayList<Order>();

        if (id.length == 1) {
            int id1 = id[0];
            String[] d={id1+"",Boolean.toString(check)};
            Cursor c = db.query("Orders", cols, "PID=" + "?" + "and State LIKE " + "?", d, null, null, null);
            int PIDidx = c.getColumnIndex("PID");
            int CIDidx = c.getColumnIndex("CID");
            int OLDidx = c.getColumnIndex("OID");
            int Numberidx = c.getColumnIndex("NumberOfOreder");
            int Stateidx = c.getColumnIndex("State");
            if (!c.isAfterLast()) {
                for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                    int PID = c.getInt(PIDidx);
                    int CID = c.getInt(CIDidx);
                    int OID = c.getInt(OLDidx);
                    int NumberID = c.getInt(Numberidx);
                    String statee=c.getString(Stateidx);
                    Order s = new Order(NumberID,CID, PID, OID);
                    s.setState(Boolean.parseBoolean(statee));
                    orders.add(s);
                }
                return orders;
            } else {
                orders.add(new Order(-1, -1, -1, -1));
                return orders;
            }
        }
        //******************************//
        else if (id.length == 2) {
            int id1 = id[0];
            int id2 = id[1];
            List<Order> students = new ArrayList<Order>();
            Cursor c = db.query("Orders", cols, "PID=" + id1 + "and OID=" + id2 + "and State LIKE FALSE", null, null, null, null);
            int PIDidx = c.getColumnIndex("PID");
            int CIDidx = c.getColumnIndex("CID");
            int OLDidx = c.getColumnIndex("OID");
            int Numberidx = c.getColumnIndex("NumberOfOreder");
            if (!c.isAfterLast()) {
                for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                    c.moveToFirst();
                    int PID = c.getInt(PIDidx);
                    int CID = c.getInt(CIDidx);
                    int OID = c.getInt(OLDidx);
                    int NumberID = c.getInt(Numberidx);
                    Order s = new Order(OID, CID, PID, NumberID);
                    orders.add(s);

                }
                return orders;
            } else {
                orders.add(new Order(-1, -1, -1, -1));
                return orders;
            }
        } else {
            orders.add(new Order(-1, -1, -1, -1));
            return orders;
        }

    }

    public int autogentration() {
        SQLiteDatabase db = getWritableDatabase();
        String[] cols;
        cols = new String[]{"MAX(OID)"};
        Cursor c = db.query("Orders", cols, null, null, null, null, null);
        int OLDidx = c.getColumnIndex("MAX(OID)");
        if (!c.isAfterLast()) {
            c.moveToNext();
            int OID = c.getInt(OLDidx);
            ++OID;
            return OID;
        } else {
            return -1;
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public Person searchPersonID(String username,int ...id) {
        SQLiteDatabase db = getWritableDatabase();
        String[] cols = {"PersonID,Username,Password,Email,Age,Photo"};

        Person myPerson;
//            int id1 = id[0];
        String[] d={username};
        Cursor c = db.query("Person", cols, "Username LIKE ?", d, null, null, null);
        int PIDidx = c.getColumnIndex("PersonID");
        int USEidx = c.getColumnIndex("Username");
        int Pasidx = c.getColumnIndex("Password");
        int Emailidx = c.getColumnIndex("Email");
        int Ageidx = c.getColumnIndex("Age");
        int Photoidx = c.getColumnIndex("Photo");
        if (!c.isAfterLast()) {
            c.moveToFirst();
            int PID = c.getInt(PIDidx);
            String User = c.getString(USEidx);
            String pass = c.getString(Pasidx);
            String Email = c.getString(Emailidx);
            int age=c.getInt(Ageidx);
            String Photo=c.getString(Photoidx);
            myPerson=new Person(User,pass,Email,age,PID);
            myPerson.setPhoto(Photo);
            return myPerson;
        } else {
            myPerson=new Person("","","",-1,-1);
            myPerson.setPhoto("no");
            return myPerson;
        }
    }
    public void updatePeson(Person s){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Photo" , s.getPhoto());
        String[] arr = {""+s.getUid()};
        //this function take 4 parameters..the atble witch going to be updated,the new colons,the condtion of updating:like id=4,
        //the fourth paramter is the id witch will updated,but must write the third like that:(id=?),must write (?) and put the value in the fourth parameter
        db.update("Person" , cv , "PersonID=?" ,arr);

    }

    public int Login(String username , String Password){

        SQLiteDatabase db = getWritableDatabase();
        String[] cols = {"Username,Password"};
        String[] d={username,Password};
        Cursor c = db.query("Person", cols, "Username LIKE " +"?"+ "and Password LIKE " + "?" , d, null, null, null);
        if(!c.isAfterLast()){

            return 1 ;

        }
        else{

            return -1 ;

        }

    }


    public ArrayList<Categories> GetItems(String NameItem){

        SQLiteDatabase db = getWritableDatabase();
        String[] cols = {"CatigoryID,Catigoryname,Catigory"};
        String[] d={NameItem};
        ArrayList<Categories> MyItems=new ArrayList<>();

        Cursor c = db.query("Catigries", cols, "Catigory LIKE ?", d, null, null, null);

        int CatIDidx = c.getColumnIndex("CatigoryID");
        int ItemNameidx = c.getColumnIndex("Catigoryname");
        int Catidx = c.getColumnIndex("Catigory");
        if (!c.isAfterLast()) {
            for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
                int CatID = c.getInt(CatIDidx);
                String Item = c.getString(ItemNameidx);
                String Cat = c.getString(Catidx);
                MyItems.add(new Categories(CatID,Cat,Item));
            }
            return MyItems;
        } else {
            MyItems.add(new Categories(-1,"",""));
            return MyItems;
        }


    }

    public int GetItemId(String name){

        SQLiteDatabase db = getWritableDatabase();
        String[] d={name};
        String[] cols = {"Catigoryname,CatigoryID"};
        Cursor c = db.query("Catigries", cols, "Catigoryname LIKE ?" ,d, null, null, null);
        if(!c.isAfterLast()){
            c.moveToFirst();
            int CIDindx=c.getColumnIndex("CatigoryID");

            return c.getInt(CIDindx);

        }
        else{

            return -1 ;

        }

    }


    public String GetItemName(int id){

        SQLiteDatabase db = getWritableDatabase();
        String[] cols = {"Catigoryname,CatigoryID"};
        Cursor c = db.query("Catigries", cols, "CatigoryID="+id ,null, null, null, null);
        if(!c.isAfterLast()){
            c.moveToFirst();
            int CNameindx=c.getColumnIndex("Catigoryname");

            return c.getString(CNameindx);

        }
        else{

            return "a" ;

        }

    }


    public Order Searchorder2(int Cid,int Pid,Boolean t, int ...NumberOfItem){
        SQLiteDatabase db = getWritableDatabase();
        String[] cols = {"PID,CID,NumberOfOreder,OID,State"};
        if(t==TRUE){
            String d[]={Pid+"",Cid+""};

            Cursor c = db.query("Orders", cols, "PID=? "+" and CID=? " ,d, null, null, null);
            if(!c.isAfterLast()){
                c.moveToFirst();
                int CIDindx=c.getColumnIndex("CID");
                int PIDindx=c.getColumnIndex("PID");
                int Numindx=c.getColumnIndex("NumberOfOreder");
                int OIDindx=c.getColumnIndex("OID");
                int Stateindx=c.getColumnIndex("State");


                Order o= new Order(c.getInt(Numindx),c.getInt(CIDindx),c.getInt(PIDindx),c.getInt(OIDindx));
                o.setState((Boolean.parseBoolean( c.getString(Stateindx))));
                return o;
            }
            else{

                return new Order(-1,-1,-1,-1) ;

            }

        }
        else{
            Cursor c;
            if(Cid!=-1) {
                String d[] = { Pid+"", Cid+"",  NumberOfItem[0]+""};
                c = db.query("Orders", cols, "PID=? "+" and CID=? "+ "and NumberOfOreder=? " ,d, null, null, null);

            }
            else{
                String d[] = {Pid+"",NumberOfItem[0]+""};
                c = db.query("Orders", cols, "PID=? "+"and NumberOfOreder=? " ,d, null, null, null);
            }
            if(!c.isAfterLast()){
                c.moveToFirst();
                int CIDindx=c.getColumnIndex("CID");
                int PIDindx=c.getColumnIndex("PID");
                int Numindx=c.getColumnIndex("NumberOfOreder");
                int OIDindx=c.getColumnIndex("OID");
                int Stateindx=c.getColumnIndex("State");

                Order e=new Order(c.getInt(Numindx),c.getInt(CIDindx),c.getInt(PIDindx),c.getInt(OIDindx));
                e.setState(Boolean.parseBoolean(c.getString(Stateindx)));
                return e;

            }
            else{

                return new Order(-1,-1,-1,-1) ;

            }


        }

    }

    public Order searchDeleteorder(int Cid,int Pid,Boolean t){

        SQLiteDatabase db = getWritableDatabase();
        String[] cols = {"PID,CID,NumberOfOreder,OID,State"};
        String d[]={Pid+"",Cid+"",t+""};

        Cursor c = db.query("Orders", cols, "PID=? "+" and CID=? "+"and State LIKE ? " ,d, null, null, null);
        if(!c.isAfterLast()){
            c.moveToFirst();
            int CIDindx=c.getColumnIndex("CID");
            int PIDindx=c.getColumnIndex("PID");
            int Numindx=c.getColumnIndex("NumberOfOreder");
            int OIDindx=c.getColumnIndex("OID");
            int Stateindx=c.getColumnIndex("State");


            Order o= new Order(c.getInt(Numindx),c.getInt(CIDindx),c.getInt(PIDindx),c.getInt(OIDindx));
            o.setState((Boolean.parseBoolean( c.getString(Stateindx))));
            return o;
        }
        else{

            return new Order(-1,-1,-1,-1) ;

        }

    }




}
