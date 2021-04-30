package com.spring.pro30;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class dateTest {
    @Test
    public void dateTest() {
        Calendar cal = Calendar.getInstance();
        Date d = new Date();
        cal.setTime(d);
        System.out.println("getTIme: " + cal.getTime());
        cal.add(Calendar.MINUTE, 30);
        System.out.println(cal.getTime());
        Calendar cal_ = Calendar.getInstance();
        System.out.println("Before: " + cal_.before(cal));
    }
}
