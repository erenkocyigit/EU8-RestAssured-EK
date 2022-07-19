package com.cydeo.Day5;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersIntro {

    @Test
    public void simpleTest1(){

        assertThat(5+5, is(10));
        assertThat(5+5,equalTo(10));

        assertThat(5+5,not(9));
        assertThat(5+5,is(not(9)));
        assertThat(5+5,is(not(equalTo(9))));

        //number comparison
//        greaterThan()
//        greaterThanOrEqualTo()
//        lessThan()
//        lessThanOrEqualTo()
        assertThat(5+5,is(greaterThan(9)));



    }
    @DisplayName("Assertion with String")
    @Test
    public void stringHamcrest(){
        String text = "EU8 is learning Hamcrest";

        assertThat(text,is("EU8 is learning Hamcrest"));
        assertThat(text,equalTo("EU8 is learning Hamcrest"));
        assertThat(text,is(equalTo("EU8 is learning Hamcrest")));

        //startsWith, endsWith
        assertThat(text,startsWith("EU8"));
        assertThat(text,startsWithIgnoringCase("eu8"));
        assertThat(text,endsWith("rest"));
        assertThat(text,endsWithIgnoringCase("ResT"));

        assertThat(text,containsString("learning"));
        assertThat(text,containsStringIgnoringCase("leArnInG"));

        String str = "";
        //blank
        assertThat(str,blankString());
        //trimmed str is empty string
        assertThat(str.trim(),emptyString());


    }

    @DisplayName("Hamcrest for Collection")
    @Test
    public void testCollection(){
        List<Integer> listOfNumbers = Arrays.asList(1,4,5,6,32,54,55,66,23);

        //hasSize
        assertThat(listOfNumbers,hasSize(9));
        //hasItem
        assertThat(listOfNumbers,hasItem(66));
        //hasItems
        assertThat(listOfNumbers,hasItems(55,66,23));

        //all numbers everyItem
        assertThat(listOfNumbers,everyItem(lessThan(100)));


    }

}
