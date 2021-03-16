package com.example.currencyconverter.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
    public static final String DATE_PATTERN = "dd-MM-YYYY";

    private DateFormat() {
    }

    public static String dateFormat(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
        return simpleDateFormat.format(date);
    }
}
