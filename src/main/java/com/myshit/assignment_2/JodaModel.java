package com.myshit.assignment_2;

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.graphwalker.core.condition.EdgeCoverage;
import org.graphwalker.core.condition.TimeDuration;
import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.core.generator.RandomPath;
import org.graphwalker.java.annotation.GraphWalker;
import org.graphwalker.java.test.TestBuilder;
import org.graphwalker.java.test.TestExecutor;

import org.junit.Test;

@GraphWalker(value = "random(edge_coverage(100))", start = "e_Start")
public class JodaModel extends ExecutionContext implements ExampleModel {

    public final static Path MODEL_PATH = Paths.get("com/myshit/assignment_2/ExampleModel.graphml");

    private Adapter adpt;
    private String dayOfWeekText;
    private int dayOfWeekInt;
    private int nrOfDaysAddedOrSubtracted;
    private int nrOfDaysBetweenNowAndObject;
    private boolean checkLeap;
    private int monthOfYear;
    private String monthOfYearText;

    private enum DayStringFromInt{
        Monday,
        Tuesday,
        Wednesday,
        Thursday,
        Friday,
        Saturday,
        Sunday
    }

    private enum MonthStringFromInt{
        January,
        February,
        March,
        April,
        May,
        June,
        July,
        August,
        September,
        October,
        November,
        December
    }

    /* Verteces */

    @Override
    public void v_LocalDateObject(){
        return;
    }

    @Override
    public void v_DayAsInt(){
        return;
    }

    @Override
    public void v_DayAsString(){
        return;
    }

    @Override
    public void v_AddedDayToObj(){
        return;
    }

    @Override
    public void v_Start(){
        return;
    }

    @Override
    public void v_DecreaseDayToObj(){
        return;
    }

    @Override
    public void v_DaysFromNow(){
        return;
    }

    //Newly Added 07/05/2020
    @Override
    public void v_AddedWeekToObj(){
        return;
    }

    @Override
    public void v_DecreaseWeekToObj(){
        return;
    }

    @Override
    public void v_DecreaseMonthToObj(){
        return;
    }

    @Override
    public void v_AddedMonthToObj(){
        return;
    }

    @Override
    public void v_LeapYearBoolean(){
        return;
    }


    /* Edges */

    @Override
    public void e_Start(){
        System.out.println("Running: e_Start");
        adpt = new Adapter();
        nrOfDaysAddedOrSubtracted = 0;
        adpt.start();
    }

    @Override
    public void e_AddDay(){
        System.out.println("Running: e_AddDay");
        nrOfDaysAddedOrSubtracted++;
        adpt.addDay();
    }

    @Override
    public void e_GetDateAsString(){
        System.out.println("Running: e_GetDayAsString");
        dayOfWeekText = adpt.getDayOfWeekAsString();
        monthOfYearText = adpt.getMonthOfYearAsString();
    }

    @Override
    public void e_GetDateAsInt(){
        System.out.println("Running: e_GetDayAsInt");
        dayOfWeekInt = adpt.getDayOfWeekAsInt();
        monthOfYear = adpt.getMonthOfYearAsInt();
    }

    @Override
    public void e_CheckCorrectIntAndString(){
        System.out.println("Running: e_CheckCorrectIntAndString");
        // Checking whether or not the int given by the object regarding the weekday, represents the correct string we get (1 = monday, 2 = tuesday etc..)
        assertEquals(DayStringFromInt.values()[dayOfWeekInt-1].toString(), dayOfWeekText.toString());
        // Checking whether or not the int given by the object regarding the month, represents the correct string we get (1 = January, 2 = February etc..)
        assertEquals(MonthStringFromInt.values()[monthOfYear-1].toString(), monthOfYearText.toString());
    }

    @Override
    public void e_SubtractDay(){
        System.out.println("Running: e_SubtractDay");
        nrOfDaysAddedOrSubtracted--;
        adpt.subtractDay();
    }

    @Override
    public void e_CheckDaysCorrect(){
        System.out.println("Running: e_CheckDaysCorrect");
        //nrOdDaysAdded.. our variable to keep tabs on the amount of days added or subtracted 
        assertEquals(nrOfDaysAddedOrSubtracted, nrOfDaysBetweenNowAndObject);
    }

    @Override
    public void e_NumberOfDays(){
        System.out.println("Running: e_NumberOfDays");
        nrOfDaysBetweenNowAndObject = adpt.numberOfDaysBetweenNowAndObject();
    }

    //Newly Add 07/05/2020
    @Override
    public void e_AddWeek(){
        System.out.println("Running: e_AddWeek");
        nrOfDaysAddedOrSubtracted += 7 ;
        adpt.addWeek();
    }

    @Override
    public void e_AddMonth(){
        System.out.println("Running: e_AddMonth");
        if(adpt.dayOfMonth() > adpt.nextDaysInMonth()){
            nrOfDaysAddedOrSubtracted += adpt.nextDaysInMonth() + (adpt.daysInMonth()-adpt.dayOfMonth());
        }else{
            nrOfDaysAddedOrSubtracted  += adpt.daysInMonth();
        }
        adpt.addMonth();
    }

    @Override
    public void e_SubtractWeek(){
        System.out.println("Running: e_SubtractWeek");
        nrOfDaysAddedOrSubtracted -= 7;
        adpt.subtractWeek();
    }

    @Override
    public void e_SubstractMonth(){
        System.out.println("Running: e_SubtractMonth");
        if(adpt.dayOfMonth() > adpt.previousDaysInMonth()){
            nrOfDaysAddedOrSubtracted -= adpt.dayOfMonth();
        }else{
            nrOfDaysAddedOrSubtracted -= adpt.previousDaysInMonth();
        }
        adpt.subtractMonth();
    }

    @Override
    public void e_IsItLeap(){
        checkLeap = adpt.isLeap();

    }

    @Override
    public void e_CheckLeap(){
        assertEquals(365 < adpt.getNumOfDaysInYear(), checkLeap);
    }

    /* Tests */

    @Test
    public void functionalTest() {
        new TestBuilder()
                .addModel(MODEL_PATH, new RandomPath(new EdgeCoverage(100)), "e_Start")
                .execute();
    }

    @Test
    public void runStabilityTest() {
        new TestBuilder()
            .addModel(MODEL_PATH, new RandomPath(new TimeDuration(30, TimeUnit.SECONDS)), "e_Start")
            .execute();
    }

}