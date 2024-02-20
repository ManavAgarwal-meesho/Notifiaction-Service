package com.meesho.notificationProducer.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ConvertStringTimeToDates {
    public static String convertStringTimestampToDate(String timeStamp) {

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a", Locale.ENGLISH);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        LocalDateTime dateTime = LocalDateTime.parse(timeStamp, inputFormatter);

        return dateTime.format(outputFormatter);

    }
}
