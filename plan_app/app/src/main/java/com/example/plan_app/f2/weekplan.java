package com.example.plan_app.f2;

public class weekplan {
    private String name;
    private String time;
    private String work;
    private String pro;
    private String reward;
    private String order;

    public weekplan(){};
    public weekplan(String name,String time,String work,String pro,String reward,String order){
        this.name = name;
        this.time=time;
        this.work=work;
        this.pro=pro;
        this.reward=reward;
        this.order=order;
    }
    public String getName() { return name; }
    public void setName(String name){this.name =name;}

    public String getTime() { return time; }
    public void setTime(String realwork){this.time =realwork;}

    public String getWork() { return work; }
    public void setWork(String name){this.work =name;}

    public String getPro() { return pro; }
    public void setPro(String name){this.pro =name;}

    public String getOrder() { return order; }
    public void setOrder(String name){this.order =name;}

    public String getReward() { return reward; }
    public void setReward(String name){this.reward =name;}

}
