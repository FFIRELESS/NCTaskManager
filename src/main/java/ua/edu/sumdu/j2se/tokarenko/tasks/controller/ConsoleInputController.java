package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.DataTest;
import ua.edu.sumdu.j2se.tokarenko.tasks.view.ConsoleView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class ConsoleInputController extends BaseController {
    private static final Logger logger = Logger.getLogger(ConsoleInputController.class);

    private static boolean error = false;
    private static boolean boolValue = false;
    private static LocalDateTime timeValue = null;
    private static String strValue;
    private static int intValue = 0;

    private static void checkInt() {
        if (DataTest.isEmptyString(strValue)) {
            logger.debug("String input error");
            error = true;
        }

        try {
            intValue = Integer.parseInt(strValue);
        } catch (NumberFormatException e) {
            logger.debug(e);
            error = true;
        }
    }

    private static void checkDate() {
        if (DataTest.isEmptyString(strValue)) {
            logger.debug("String input error");
            error = true;
        }

        try {
            timeValue = LocalDateTime.parse(strValue, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (DateTimeParseException e) {
            logger.debug(e);
            error = true;
        }
    }

    public static String nextString() {
        do {
            error = false;
            strValue = in.nextLine();

            if (DataTest.isEmptyString(strValue)) {
                logger.debug("String input error");
                error = true;
            }

            if (error) {
                System.out.print("Невірні дані. Спробуйте ще раз: ");
            }
        } while (error);
        return strValue;
    }

    public static int nextInt() {
        do {
            error = false;
            strValue = in.nextLine();

            checkInt();

            if (intValue <= 0) {
                logger.debug("Integer input error");
                error = true;
            }

            if (error) {
                System.out.print("Невірні дані. Спробуйте ще раз: ");
            }
        } while (error);
        return intValue;
    }

    public static int nextIntInRange(int from, int to) {
        do {
            error = false;
            strValue = in.nextLine();

            checkInt();

            if (intValue < from || intValue > to) {
                logger.debug("Integer input error");
                error = true;
            }

            if (error) {
                System.out.print("Невірні дані. Спробуйте ще раз: ");
            }
        } while (error);
        return intValue;
    }

    public static boolean nextBoolean() {
        do {
            error = false;
            strValue = in.nextLine();

            if (DataTest.isEmptyString(strValue)) {
                logger.debug("String input error");
                error = true;
            }

            if (strValue.toLowerCase(Locale.ROOT).equals("true") || strValue.equals("1")) {
                boolValue = true;
            } else if (strValue.toLowerCase(Locale.ROOT).equals("false") || strValue.equals("0")) {
                boolValue = false;
            } else {
                error = true;
            }

            if (error) {
                System.out.print("Невірні дані. Спробуйте ще раз: ");
            }
        } while (error);
        return boolValue;
    }

    public static LocalDateTime nextTime() {
        do {
            error = false;
            strValue = in.nextLine();

            checkDate();

            if (timeValue != null && timeValue.isBefore(LocalDateTime.now())) {
                logger.debug("Time input error");
                error = true;
            }

            if (error) {
                System.out.print("Невірні дані. Спробуйте ще раз: ");
            }
        } while (error);
        return timeValue;
    }

    public static LocalDateTime nextCalendarDate() {
        do {
            error = false;
            strValue = in.nextLine();

            checkDate();

            if (timeValue == null) {
                logger.debug("Time input error");
                error = true;
            }

            if (error) {
                System.out.print("Невірні дані. Спробуйте ще раз: ");
            }
        } while (error);
        return timeValue;
    }

    public static LocalDateTime nextEndDate(LocalDateTime start) {
        do {
            error = false;
            strValue = in.nextLine();

            checkDate();

            if (timeValue == null) {
                logger.debug("Time input error");
                error = true;
            }

            if (timeValue.equals(start) || timeValue.isBefore(start)) {
                logger.debug("End date <= start date");
                error = true;
            }

            if (error) {
                System.out.print("Кінцевий час <= стартовому! Спробуйте ще раз: ");
            }
        } while (error);
        return timeValue;
    }

    // перевірка на українця
    public static boolean nextUkrainian() {
        int attempts = 3;

        do {
            error = false;

            ConsoleView.newEmptyLine();
            ConsoleView.printParagraph("Відповідь: ");
            strValue = in.nextLine();

            if (!strValue.toLowerCase(Locale.ROOT).contains("героям слава")) {
                error = true;
                attempts--;
            } else {
                ConsoleView.newEmptyLine();
                ConsoleView.printChooser("Ласкаво просимо, козаче!");
                error = false;
            }

            if (error && attempts <= 0) {
                ConsoleView.newEmptyLine();
                ConsoleView.printWarning("ПІШОВ Н@ХYЙ, МОСКАЛЬ ПРОКЛЯТИЙ!!!");
                return false;
            }

            if (error) {
                ConsoleView.printNote("В тебе ще " + attempts + " спроб");
            }
        } while (error);
        return true;
    }
}
