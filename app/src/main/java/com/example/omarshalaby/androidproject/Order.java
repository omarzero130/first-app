package com.example.omarshalaby.androidproject;

import static java.lang.Boolean.FALSE;

public class Order {
    private int NumberOfitem,Cid,Pid,Oid;
    private boolean state;

    public Order(){
        state=FALSE;
    }


    public Order(int numberOfitem, int cid, int pid, int oid) {
        NumberOfitem = numberOfitem;
        Cid = cid;
        Pid = pid;
        Oid = oid;

    }

    public Order(int numberOfitem, int cid, int pid) {
        NumberOfitem = numberOfitem;
        Cid = cid;
        Pid = pid;

    }

    public int getCid() {
        return Cid;
    }

    public boolean isState() {
        return this.state;
    }

    public int getNumberOfitem() {
        return NumberOfitem;
    }

    public int getOid() {
        return Oid;
    }

    public int getPid() {
        return Pid;
    }

    public void setCid(int cid) {
        Cid = cid;
    }

    public void setNumberOfitem(int numberOfitem) {
        NumberOfitem = numberOfitem;
    }

    public void setOid(int oid) {
        Oid = oid;
    }

    public void setPid(int pid) {
        Pid = pid;
    }

    public void setState(boolean state) {
        this.state = state;
    }

}
