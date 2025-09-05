package com.bookstore.entity.utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//не получается форматировать дату в нужную из-за возвращаемого стринга
public final class DateFormatter {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd - MMMM - yyyy / HH:mm:");

}
