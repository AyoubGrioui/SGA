package com.sga.helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SGAUtil {
    public static LocalDate StringToLocalDate( String date ) {
        if ( date != null ) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd" );
            return LocalDate.parse( date, formatter );
        }

        return null;
    }

    public static double StringToDouble( String number ) {

        try {
            double result = Double.parseDouble( number );
            return result;
        } catch ( NumberFormatException e ) {
            return 0;
        }

    }
}
