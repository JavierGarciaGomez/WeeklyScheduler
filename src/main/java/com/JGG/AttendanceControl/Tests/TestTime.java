package com.JGG.AttendanceControl.Tests;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class TestTime {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now(); // today
        Locale locale = Locale.getDefault(); // Get the locale values of default (region where is executed)
        WeekFields weekFields = WeekFields.ISO.of(locale); // Get a WeekField standar
        int weekNumber = date.get(weekFields.weekOfWeekBasedYear()); // Get the number of week by the standard

        System.out.println("The week number is"+ weekNumber);

        DayOfWeek firstDayOfWeek = WeekFields.of(locale).getFirstDayOfWeek();

        System.out.println("The first day of the week is: "+firstDayOfWeek);
        LocalDate thisMondayDate = date.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
        System.out.println("The date of monday was "+thisMondayDate);

        // Trying to get the first day of another week
        LocalDate dateNextWeek = LocalDate.now().plusDays(7); // today
        weekNumber = dateNextWeek.get(weekFields.weekOfWeekBasedYear()); // Get the number of week by the standard
        System.out.println("The week number is"+ weekNumber);
        firstDayOfWeek = weekFields.of(locale).getFirstDayOfWeek();
        System.out.println("The first day of the week is: "+firstDayOfWeek);
        LocalDate nextMondayDate = dateNextWeek.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
        System.out.println("The date of monday was "+nextMondayDate);




    }
}
