package com.JGG.WeeklyScheduler.model;



import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class Model {
    private final static Model instance = new Model();
    public int viewing_year;
    public int viewing_week;
    public LocalDate selectedLocalDate;
    public LocalDate mondayOfTheWeek;
    public LocalDate lastDayOfMonth;
    public final String [] weekDaysNames = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};


    public static Model getInstance(){
        return instance;
    }

    public void setMondayDate() {
        if(selectedLocalDate.getDayOfWeek().equals(DayOfWeek.MONDAY)) mondayOfTheWeek=selectedLocalDate;
        else mondayOfTheWeek = selectedLocalDate.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
    }

    public void setLastDayOfMonth(){
        lastDayOfMonth = selectedLocalDate.with(TemporalAdjusters.lastDayOfMonth());
    }


}
