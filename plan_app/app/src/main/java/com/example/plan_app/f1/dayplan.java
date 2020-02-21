package com.example.plan_app.f1;

public class dayplan {
    private String name;
    private String realwork;
    private String realtime;
    private String planwork;
    private String plantime;

    public dayplan(){};
    public dayplan(String name,String realwork,String realtime,String planwork,String plantime){
        this.name = name;
        this.realwork=realwork;
        this.realtime=realtime;
        this.planwork=planwork;
        this.plantime=plantime;
    }
    public String getName() { return name; }
    public void setName(String name){this.name =name;}

    public String getRealwrok() { return realwork; }
    public void setRealwork(String realwork){this.realwork =realwork;}

    public String getRealtime() { return realtime; }
    public void setRealtime(String name){this.realtime =name;}

    public String getPlanwork() { return planwork; }
    public void setPlanwork(String name){this.planwork =name;}

    public String getPlantime() { return plantime; }
    public void setPlantime(String name){this.plantime =name;}
}
