package com.example.servingwebcontent;

import java.util.List;

public class Day {
    private int thisDay;
    private List<Event> events;
    private boolean isCurrent;


    public Day(int day, boolean current){
        this.thisDay = day;
        this.isCurrent = current;
    }

    public int getThisDay(){
        return thisDay;
    }

    public boolean getIsCurrent(){
        return isCurrent;
    }

    public List<Event> getEvents(){
        return events;
    }

    public void setThisDay(int day){
        thisDay = day;
    }

    public void addEvent(Event event){
        events.add(event);
    }

}
