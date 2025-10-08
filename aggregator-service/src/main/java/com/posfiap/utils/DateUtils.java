package com.posfiap.utils;

import com.posfiap.exception.DataInvalidaException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public final class DateUtils {

    private DateUtils() {}

    public static LocalDate stringToLocalDate(String data) {
        try {
            return LocalDate.parse(data);
        } catch (DateTimeParseException exception) {
            throw new DataInvalidaException(data);
        }
    }
}
