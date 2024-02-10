package com.meesho.notificationProducer.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class ConvertStringTimeToDates {
    public static String convertStringTimestampToDate(String timeStamp) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a", Locale.ENGLISH);

        LocalDateTime dateTime = LocalDateTime.parse(timeStamp, formatter);

        DateTimeFormatter responseTime = DateTimeFormatter.ISO_DATE_TIME;

        return dateTime.format(responseTime);

    }
}
