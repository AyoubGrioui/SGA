package com.sga.helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SGAUtil
{
    public static LocalDate StringToLocalDate(String date)
    {
        if(date != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            return LocalDate.parse(date, formatter);
        }

        return null;
    }
}
