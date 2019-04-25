package com.example.omarshalaby.androidproject;

public class Categories {
    private int Catid;
    private String Categoury,Cname;

    public Categories(){

    }

    public Categories(int Catid,String Categoury,String Cname){
        this.Categoury=Categoury;
        this.Catid=Catid;
        this.Cname=Cname;
    }
    public Categories(String Categoury,String Cname){
        this.Categoury=Categoury;
         this.Cname=Cname;
    }


    public int getCatid() {
        return Catid;
    }

    public void setCategoury(String categoury) {
        Categoury = categoury;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public String getCategoury() {
        return Categoury;
    }

    public void setCatid(int catid) {
        Catid = catid;
    }

}
