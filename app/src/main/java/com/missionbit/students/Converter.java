package com.missionbit.students;

import android.widget.EditText;

import androidx.databinding.InverseMethod;

import java.util.Date;

public class Converter {
    @InverseMethod("stringToDate")
    public static String dateToString(Date value) {
        return "";
    }

    public static Date stringToDate(String value) {
        // Converts String to long.
        return new Date();
    }

    @InverseMethod("stringToInt")
    public static String intToString(int value) {
        return "";
    }

    public static int stringToInt(String value) {
        // Converts String to long.
        return 0;
    }
}
