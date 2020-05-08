package com.myshit.assignment_2;

import org.joda.time.LocalDate;
import org.joda.time.Months;
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

    public int daysInMonth(){
        return date.dayOfMonth().getMaximumValue();    
    }

    public void subtractMonth(){
        date = date.minusMonths(1);
    }

    public int numberOfDaysBetweenNowAndObject(){
        System.out.println("now: "+now.toString()+" date: "+date.toString());
        Days days = Days.daysBetween(now, date);
        return days.getDays();
    }
}