package ua.edu.sumdu.j2se.tokarenko.tasks.utils;

import java.time.LocalDateTime;

import static ua.edu.sumdu.j2se.tokarenko.tasks.utils.Exceptions.nullArgumentException;

public class Prettier {
    static public StringBuilder prettyDate(LocalDateTime date) {
        StringBuilder prettyDate = new StringBuilder();

        if (date == null) {
            throw nullArgumentException;
        }

        prettyDate.append("Date: ");
        prettyDate.append(date.getDayOfMonth());
        prettyDate.append("/");
        prettyDate.append(date.getMonth());
        prettyDate.append("/");
        prettyDate.append(date.getYear());

        for (int i = 0; i < 26; i++) {
            if (i > prettyDate.length()) {
                prettyDate.append(" ");
            }
        }
        prettyDate.append("Time: ");
        prettyDate.append(date.getHour());
        prettyDate.append(":");
        prettyDate.append(date.getMinute());

        return prettyDate;
    }

    static public StringBuilder prettyTitle(String title, int fieldLength) {
        StringBuilder formattedTitle = new StringBuilder();

        if (DataTest.isEmptyString(title)) {
            throw nullArgumentException;
        }

        for (int i = 0; i < fieldLength; ++i) {
            if (i < title.length()) {
                formattedTitle.append(title.charAt(i));
            }
            else {
                formattedTitle.append(" ");
            }
        }
        return formattedTitle;
    }
}
