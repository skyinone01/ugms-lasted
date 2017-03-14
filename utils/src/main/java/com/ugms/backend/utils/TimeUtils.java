package com.ugms.backend.utils;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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


}
