package ua.edu.sumdu.j2se.tokarenko.tasks.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static ua.edu.sumdu.j2se.tokarenko.tasks.utils.Exceptions.nullArgumentException;

public class Prettier {
    /**
     * Метод форматування часу в двоцифровий.
     *
     * @param time час, що форматується.
     * @return форматований час.
     */
    static public StringBuilder prettyTwoDigitTime(long time) {
        StringBuilder prettyTime = new StringBuilder();

        if (String.valueOf(time).length() == 1) {
            prettyTime.append('0').append(time);
        } else {
            prettyTime.append(time);
        }
        return prettyTime;
    }

    /**
     * Метод форматування дати.
     *
     * @param date дата, що форматується.
     * @return форматована дата.
     */
    static public StringBuilder prettyDate(LocalDateTime date) {
        StringBuilder prettyDate = new StringBuilder();

        if (date == null) {
            throw nullArgumentException;
        }

        prettyDate.append(date.getDayOfMonth()).append("/");
        prettyDate.append(date.getMonth());
        prettyDate.append("/");
        prettyDate.append(date.getYear());

        for (int i = 0; i < 21; i++) {
            if (i > prettyDate.length()) {
                prettyDate.append(" ");
            }
        }

        if (String.valueOf(date.getHour()).length() == 1) {
            prettyDate.append('0').append(date.getHour());
        } else {
            prettyDate.append(date.getHour());
        }
        prettyDate.append(":");

        if (String.valueOf(date.getMinute()).length() == 1) {
            prettyDate.append('0').append(date.getMinute());
        } else {
            prettyDate.append(date.getMinute());
        }
        prettyDate.append(":");

        if (String.valueOf(date.getSecond()).length() == 1) {
            prettyDate.append('0').append(date.getSecond());
        } else {
            prettyDate.append(date.getSecond());
        }
        return prettyDate;
    }

    /**
     * Метод форматування тексту в межах заданої довжини поля.
     *
     * @param text        текст, що форматується.
     * @param fieldLength довжина поля.
     * @return форматований текст.
     * @throws IllegalArgumentException - якщо рядок пустий.
     */
    static public StringBuilder prettyText(String text, int fieldLength) {
        StringBuilder formattedTitle = new StringBuilder();

        if (DataTest.isEmptyString(text)) {
            throw nullArgumentException;
        }

        for (int i = 0; i < fieldLength; ++i) {
            if (i < text.length()) {
                formattedTitle.append(text.charAt(i));
            } else {
                formattedTitle.append(" ");
            }
        }
        return formattedTitle;
    }

    /**
     * Метод форматування інтервалу дат в межах заданої довжини поля.
     *
     * @param interval    інтервал в секундах.
     * @param start       початкова дата інтервалу.
     * @param fieldLength довжина поля.
     * @return форматований інтервал.
     */
    static public StringBuilder prettyInterval(int interval, LocalDateTime start, int fieldLength) {
        LocalDateTime endInt = start.plusSeconds(interval);
        StringBuilder formattedInterval = new StringBuilder();
        StringBuilder strInterval = new StringBuilder();

        if (interval == 0) {
            strInterval.append("-");
        } else {
            LocalDateTime tempDateTime = LocalDateTime.from(start);

            long days = tempDateTime.until(endInt, ChronoUnit.DAYS);
            tempDateTime = tempDateTime.plusDays(days);

            long hours = tempDateTime.until(endInt, ChronoUnit.HOURS);
            tempDateTime = tempDateTime.plusHours(hours);

            long minutes = tempDateTime.until(endInt, ChronoUnit.MINUTES);
            tempDateTime = tempDateTime.plusMinutes(minutes);

            long seconds = tempDateTime.until(endInt, ChronoUnit.SECONDS);

            if (days != 0) {
                strInterval.append(days).append("д ");
                strInterval.append(prettyTwoDigitTime(hours)).append(":");
                strInterval.append(prettyTwoDigitTime(minutes)).append(":");
                strInterval.append(prettyTwoDigitTime(seconds));
            } else {
                if (hours != 0) {
                    strInterval.append(hours).append("г ");
                }
                if (minutes != 0) {
                    strInterval.append(minutes).append("хв ");
                }
                if (seconds != 0) {
                    strInterval.append(seconds).append("c");
                }
            }
        }

        for (int i = 0; i < fieldLength; ++i) {
            if (i < strInterval.length()) {
                formattedInterval.append(strInterval.charAt(i));
            } else {
                formattedInterval.append(" ");
            }
        }
        return formattedInterval;
    }
}
