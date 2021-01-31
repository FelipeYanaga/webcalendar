package com.example.servingwebcontent;

public class Event {
    private String name;
    private String endTime;
    private String startTime;
    private String location;

    public String getName(){
        return name;
    }

    public String getEndTime(){
        return endTime;
    }

    public String getStartTime(){
        return startTime;
    }

    public String getLocation(){
        return location;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEndTime(String time){
        this.endTime = time;
    }

    public void setStartTime(String time){
        this.startTime = time;
    }

    public void setLocation(String location){
        this.location = location;
    }
}
