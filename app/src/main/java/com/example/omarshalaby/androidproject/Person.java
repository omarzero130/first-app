package com.example.omarshalaby.androidproject;

public class Person {
    String Username , Pass , Email ,photo;
    int Age , Uid;

    public Person(){

    }
    public Person(String username , String pass , String email , int age)
    {
        this.Username = username;
        this.Pass = pass;
        this.Email = email;
        this.Age = age;
    }


    public Person(String username , String pass , String email , int age,int uid)
    {
        this.Username = username;
        this.Pass = pass;
        this.Email = email;
        this.Age = age;
        this.Uid= uid;

    }

    public String getUsername()
    {
        return Username;
    }
    public String getPass()
    {
        return Pass;
    }
    public String getEmail()
    {
        return Email;
    }

    public int getAge()
    {
        return Age;
    }
    public int getUid(){
        return Uid;
    }

    public void setUid(int uid) {
        Uid = uid;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }
}
