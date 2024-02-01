package com.meesho.notificationProducer.utils;

import com.meesho.notificationProducer.constants.Constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator {
    public static boolean isPhoneNumberValid(String phoneNumber) {
        Pattern pattern = Pattern.compile(Constants.PHONE_NUMBER_REGEX);
        Matcher match = pattern.matcher(phoneNumber);

        return (match.find() && match.group().equals(phoneNumber));
    }
}