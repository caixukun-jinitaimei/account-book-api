package com.example.accountbook1;

import static com.example.accountbook1.utils.SimpleDateFormatUtils.sdf;

import org.junit.Test;

import java.util.Date;

public class DateFormatTest {
    @Test
    public void testDate(){
        Date date = new Date();
        System.out.println(sdf.format(date));
        System.out.println();
    }
}
