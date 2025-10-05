package com.posfiap.utils;

import com.posfiap.exception.FormatoDataInvalidoException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Converter {

    public static LocalDate stringToLocalDate(String data) {
        try {
            return LocalDate.parse(data);
        } catch (DateTimeParseException exception) {
            throw new FormatoDataInvalidoException(data);
        }
    }
}
