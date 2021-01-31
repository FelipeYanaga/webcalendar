package com.example.servingwebcontent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Calendar {
    private List<Day> days;
    private int currentYear;
    private int currentMonth;

    public static final int FIRST_OF_MONTH = 1;
    public static final int DAYS_IN_A_WEEK = 7;

    public Calendar(int year, int month) {
        this.currentYear = year;
        this.currentMonth = month;
        createMonth(year, currentMonth);
    }

    /* Creates the calendar list*/
    public void createMonth(int year, int month) throws NullPointerException{
        int firstDay = 0;
        int lengthOfPreviousMonth = LocalDate.of(year, month, FIRST_OF_MONTH).plusMonths(-1).lengthOfMonth();
        List<String> weekDayList = createWeekDayHeader(GreetingController.WEEKDAY);
        List<Day> targetList = new ArrayList<Day>();
        for (int i = 0; i < weekDayList.size(); i++) {
            if (weekDayList.get(i).equalsIgnoreCase(getWeekDayOfFirstDay(year, month))){
                firstDay = i;
            }
        }
        int j = -1;
        int k = 1;
        for (int i = 1; i <= eveningRows(firstDay + getMonthLength(year, month)); i++){
            if (i - firstDay < 1){ 
                targetList.add(new Day((lengthOfPreviousMonth - firstDay - j), false));
                j--;
            }
            else {
                if (i - firstDay <= getMonthLength(year, month)) {
                    targetList.add(new Day((i - firstDay), true));
                }
                else {
                    targetList.add(new Day((k), false));
                    k++;
                }
            }
        }
        this.days = targetList;
    }

    /* Gets the List used to create the calendar */
    public List<Day> getDays(){
        return this.days;
    }


    /* Gets the int value of the Calendar's month*/
    public int getMonth(){
        return this.currentMonth;
    }

    /* Gets the class year */
    public int getCurrentYear(){
        return this.currentYear;
    }


    /* Gets the length of the current month */
    public int getMonthLength(int year, int month){
        return LocalDate.of(year, month, FIRST_OF_MONTH).lengthOfMonth();
    }


    /** Gets the week day of the first day of the month */
    public String getWeekDayOfFirstDay(int year, int month){
        return LocalDate.of(year, month, FIRST_OF_MONTH).getDayOfWeek().toString();
    }


    /* Creates and returns and ArrayList with all 12 months in string format */
    public static List<String> getMonthsList(){ 
        List<String> monthsList = new ArrayList<String>();
        monthsList.add("JANUARY");
        monthsList.add("FEBRUARY");
        monthsList.add("MARCH");
        monthsList.add("APRIL");
        monthsList.add("MAY");
        monthsList.add("JUNE");
        monthsList.add("JULY");
        monthsList.add("AUGUST");
        monthsList.add("SEPTEMBER");
        monthsList.add("OCTOBER");
        monthsList.add("NOVEMBER");
        monthsList.add("DECEMBER");
        return monthsList;
    }

    public static List<String> getWeekdayList(){
        List<String> weekDayList = new ArrayList<String>();
        weekDayList.add("Monday");
        weekDayList.add("Tuesday");
        weekDayList.add("Wednesday");
        weekDayList.add("Thursday");
        weekDayList.add("Friday");
        weekDayList.add("Saturday");
        weekDayList.add("Sunday");
        return weekDayList;
    }


    /* Adds a month to the value */
    public List<Day> plusMonths(int monthsAdded){
        LocalDate currentDate = LocalDate.of(currentYear, currentMonth, FIRST_OF_MONTH);
        LocalDate targetDate = currentDate.plusMonths(monthsAdded);
        currentMonth = targetDate.getMonthValue();
        currentYear = targetDate.getYear();
        createMonth(currentYear, currentMonth);
        return getDays();
    }


    /* Creates and returns a list of all years ahead of the current one*/
    public static List<Integer> getYearsList(){
        List<Integer> yearsList = new ArrayList<Integer>();
        int year = LocalDate.now().getYear();
        for (int i = year; i < year + 50; i++){
            yearsList.add(i);
        }
        return yearsList;
    }

    /*
    Convert String month to an int value
     */
    public static int monthToInt(String month){
        Map<String, Integer> convertor = new HashMap<String, Integer>();
        List<String> strMonthValues = Calendar.getMonthsList();
        List<Integer> intMonthValue = new ArrayList<Integer>();
        for (int i = 0; i < strMonthValues.size(); i++){
            convertor.put(strMonthValues.get(i), i + 1);
        }
        convertor.forEach( 
            (key, value) -> {
                if (key.equalsIgnoreCase(month)) {
                    intMonthValue.add(value);
                }
            }
            );
        return intMonthValue.get(0);
    }

    /*
    Convert a month from an int value to a String value
     */
    public String monthToString(){
        List<String> monthsList = getMonthsList();
        String strMonthValue = "";
        for (int i = 0; i < monthsList.size(); i++){
            if (i == this.currentMonth - 1) {
                strMonthValue = monthsList.get(i);
            }
        }
        return strMonthValue;
    }

    /*
    @param the day of the week you want your calendar display to start with
    @return a list ordered based on the parameter that you chose
    Only applies to the header
     */
    public static List<String> createWeekDayHeader(String firstWeekDay){
        List<String> templateList = getWeekdayList();
        List<String> targetList = new ArrayList<String>();
        for (int i = 0; i < templateList.size(); i++) {
            if (templateList.get(i).equalsIgnoreCase(firstWeekDay)) {
                targetList.add(templateList.get(i));
                for (int j = i + 1; j < templateList.size(); j++){
                    targetList.add(templateList.get(j));
                }
                for (int k = 0; k < i; k++) {
                    targetList.add(templateList.get(k));
                }
            }
        }
        return targetList;
    }

    /*
    @param days in the month that is being created
    @return the "evened out" number of rows needed to display on screen
     */
    public int eveningRows(int days){
        while (days % DAYS_IN_A_WEEK != 0){
            days++;
        }
        return days;
    }
}
