package com.example.plan_app.f3;

public class mplan {
    private String name;
    private String m;
    private String p;
    private String t;
    private String dl;

    public mplan(){};
    public mplan(String name,String m,String p,String dl,String t){
        this.name = name;
        this.m=m;
        this.dl=dl;
        this.t=t;
        this.p=p;
    }
    public String getName() { return name; }
    public void setName(String name){this.name =name;}

    public String getm() { return m; }
    public void setm(String realwork){this.m =realwork;}

    public String getp() { return p; }
    public void setp(String name){this.p =name;}

    public String gett() { return t; }
    public void sett(String name){this.t =name;}

    public String getdl() { return dl; }
    public void setdl(String name){this.dl =name;}
}
