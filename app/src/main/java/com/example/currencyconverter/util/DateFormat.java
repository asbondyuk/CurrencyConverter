package com.example.currencyconverter.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormat {
    public static final String DATE_PATTERN = "dd-MM-yyyy";

    private DateFormat() {
    }

    public static String dateFormatToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN, Locale.ENGLISH);
        return simpleDateFormat.format(date);
    }
}
