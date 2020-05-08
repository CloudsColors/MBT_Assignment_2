package com.myshit.assignment_2;

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.graphwalker.core.condition.EdgeCoverage;
import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.core.generator.RandomPath;
import org.graphwalker.java.annotation.GraphWalker;
import org.graphwalker.java.test.TestBuilder;

import org.junit.Test;

@GraphWalker(value = "random(edge_coverage(100))", start="e_Start")
public class JodaModel extends ExecutionContext implements ExampleModel {

    public final static Path MODEL_PATH = Paths.get("com/myshit/assignment_2/ExampleModel.graphml");

    private Adapter adpt;
    private String dayOfWeekText;
    private int dayOfWeekInt;
    private int nrOfDaysAddedOrSubtracted;
    private int nrOfDaysBetweenNowAndObject;

    private enum DayStringFromInt{
        Monday,
        Tuesday,
        Wednesday,
        Thursday,
        Friday,
        Saturday,
        Sunday
    }

    /* Vertexes */

    @Override
    public void v_DateTimeObject(){
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
    public void e_GetDayAsString(){
        System.out.println("Running: e_GetDayAsString");
        dayOfWeekText = adpt.getDayOfWeekAsString();
    }

    @Override
    public void e_GetDayAsInt(){
        System.out.println("Running: e_GetDayAsInt");
        dayOfWeekInt = adpt.getDayOfWeekAsInt();
    }

    @Override
    public void e_CheckCorrectIntAndString(){
        System.out.println("Running: e_CheckCorrectIntAndString");
        assertEquals(DayStringFromInt.values()[dayOfWeekInt-1].toString(), dayOfWeekText.toString());
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
        //System.out.println("Number of days between NAO:"+nrOfDaysBetweenNowAndObject+" Number of days AOS"+nrOfDaysAddedOrSubtracted);
        assertEquals(nrOfDaysBetweenNowAndObject, nrOfDaysAddedOrSubtracted);
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
        nrOfDaysAddedOrSubtracted++;
        adpt.addDay();
    }

    @Override
    public void e_AddMonth(){
        System.out.println("Running: e_AddMonth");
        nrOfDaysAddedOrSubtracted++;
        adpt.addDay();
    }

    @Override
    public void e_SubtractWeek(){
        System.out.println("Running: e_SubtractWeek");
        nrOfDaysAddedOrSubtracted--;
        adpt.subtractDay();
    }

    @Override
    public void e_SubstractMonth(){
        System.out.println("Running: e_SubtractMonth");
        nrOfDaysAddedOrSubtracted--;
        adpt.subtractDay();
    }

    /* Tests */

    @Test
    public void functionalTest() {
        new TestBuilder()
                .addModel(MODEL_PATH, new RandomPath(new EdgeCoverage(100)), "e_Start")
                .execute();
    }
}