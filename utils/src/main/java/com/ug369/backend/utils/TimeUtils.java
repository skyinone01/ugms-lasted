package com.ug369.backend.utils;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by roy on 2017/3/8.
 */

@Component
public class TimeUtils {

    public static final Long FIVE_MIN = 5 * 60 * 1000l;

    public static final Long FIVE_SECOND = 5 * 1000l;

    public static final Long ONE_WEEK = 10080 * 60 * 1000l;

    public static Instant atStartOfDay(LocalDate date) {
        return date.atStartOfDay(ZoneId.systemDefault()).toInstant();
    }

    public static Date startOfDay(LocalDate date) {
        return Date.from(atStartOfDay(date));
    }

    public static int dateToInt(LocalDate date) {
        return Integer.parseInt(date.format(DateTimeFormatter.BASIC_ISO_DATE));
    }


    /**
     * 获取年月日
     * @param type
     * @return
     */
    public static int getCurrentDate(int type){
    	Calendar c = Calendar.getInstance();
    	int date = 0;
    	switch (type) {
		case 1:
			date = c.get(Calendar.YEAR);
			break;
		case 2:
			date = c.get(Calendar.MONTH) + 1;
			break;
		case 3:
			date = c.get(Calendar.DAY_OF_MONTH);
			break;

		default:
			break;
		}
    	System.out.println(date);
    	return date;
    }
    
    public static void main(String[] args) {
    	getCurrentDate(2);
	}
}
