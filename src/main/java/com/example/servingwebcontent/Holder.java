package com.example.servingwebcontent;

import java.time.LocalDate;

public class Holder {
    private String monthString;
    private int holderYear = LocalDate.now().getYear();// Th:field doesn't work with selected, this takes preference over that.

    public String getMonthString(){
       return monthString;
    }

    public int getHolderYear(){ return holderYear; }

    public void setMonthString(String string){
        this.monthString = string;
    }

    public void setHolderYear(int integer){
        this.holderYear = integer;
    }

}
