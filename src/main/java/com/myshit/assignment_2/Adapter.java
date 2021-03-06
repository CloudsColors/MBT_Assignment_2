package com.myshit.assignment_2;

import org.joda.time.LocalDate;
import org.joda.time.Months;

import java.time.Period;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.joda.time.Days;

public class Adapter {

    private LocalDate now;
    private LocalDate date;
   
    public void start(){
        now = new LocalDate();
        date = new LocalDate();
    }


    public void addDay(){
        date = date.plusDays(1);
    }


    public int getDayOfWeekAsInt(){
        return date.getDayOfWeek();
    }


    public String getDayOfWeekAsString(){
        return date.dayOfWeek().getAsText();
    }

    public int getMonthOfYearAsInt(){
        return date.monthOfYear().get();
    }

    public String getMonthOfYearAsString(){
        return date.monthOfYear().getAsText();
    }

    public void subtractDay(){
        date = date.minusDays(1);
    }
    public void addWeek(){
        date = date.plusWeeks(1);
    }

    public void subtractWeek(){
        date = date.minusWeeks(1);
    }

    public void addMonth(){
        date = date.plusMonths(1);
    }

    public int previousDaysInMonth(){
        LocalDate previousMonth = date.minusMonths(1);
        return previousMonth.dayOfMonth().getMaximumValue();   
    }

    public int nextDaysInMonth(){
        LocalDate nextMonth = date.plusMonths(1);
        return nextMonth.dayOfMonth().getMaximumValue();   
    }

    public int daysInMonth(){
        return date.dayOfMonth().getMaximumValue();    
    }

    public int dayOfMonth(){
        return date.dayOfMonth().get();
    }

    public void subtractMonth(){
        date = date.minusMonths(1);
    }

    public int numberOfDaysBetweenNowAndObject(){
        System.out.println("now: "+now.toString()+" date: "+date.toString());
        Days days = Days.daysBetween(now, date);
        return days.getDays();
    }

    public boolean isLeap(){
        return date.year().isLeap();
    }

    public int getNumOfDaysInYear(){
        LocalDate firstDayOfYear = new LocalDate(date.year().get(), 1, 1);
        LocalDate oneYearLater = firstDayOfYear.plusYears(1);
        return Days.daysBetween(firstDayOfYear, oneYearLater).getDays();
    }
}