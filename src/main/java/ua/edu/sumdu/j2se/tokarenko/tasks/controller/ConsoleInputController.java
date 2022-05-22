package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.User;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.DataTest;
import ua.edu.sumdu.j2se.tokarenko.tasks.view.ConsoleView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Locale;

public class ConsoleInputController extends BaseController {
    private static final Logger logger = Logger.getLogger(ConsoleInputController.class);

    private static boolean error = false;
    private static boolean boolValue = false;
    private static LocalDateTime timeValue = null;
    private static String strValue;
    private static int intValue = 0;

    /**
     * Метод перевірки правильності введення числа.
     */
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

    /**
     * Метод перевірки правильності введення дати.
     */
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

    /**
     * Метод перевірки правильності введення рядку.
     *
     * @return введений користувачем рядок.
     */
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

    /**
     * Метод перевірки правильності введення імені користувача.
     *
     * @return введений користувачем рядок.
     */
    public static String nextUsername() {
        do {
            error = false;
            strValue = in.nextLine();

            if (DataTest.isEmptyString(strValue)) {
                logger.debug("String input error");
                error = true;
            }

            if (!strValue.matches("^[a-zA-Z][a-zA-Z0-9-_\\.]{6,20}$")) {
                System.out.println("Ім'я користувача повинне починатися латинською літерою");
                System.out.println("і мати довжину 6-20 символів. ");
                error = true;
            }

            if (error) {
                System.out.print("Невірні дані. Спробуйте ще раз: ");
            }
        } while (error);
        return strValue;
    }

    /**
     * Метод перевірки правильності введення паролю користувача.
     *
     * @return введений користувачем рядок.
     */
    public static String nextPassword() {
        do {
            error = false;
            strValue = in.nextLine();

            if (DataTest.isEmptyString(strValue)) {
                logger.debug("String input error");
                error = true;
            }

            if (!strValue.matches("(?=^.{8,64}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$")) {
                System.out.println("Пароль повинен містити число або символ, велику і малу латинські літери");
                System.out.println("та мати довжину 8-64 символів. ");
                error = true;
            }

            if (error) {
                System.out.print("Невірні дані. Спробуйте ще раз: ");
            }
        } while (error);
        return strValue;
    }

    /**
     * Метод перевірки числа на негативність або 0.
     *
     * @return введене користувачем число.
     */
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

    /**
     * Метод перевірки числа в заданому інтервалі.
     *
     * @param from - початкове значення інтервалу.
     * @param to   - кінцеве значення інтервалу.
     * @return введене користувачем число.
     */
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

    /**
     * Метод перевірки правильності введення даних типу boolean.
     *
     * @return введене користувачем значення.
     */
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

    /**
     * Метод перевірки дати, яка не повинна перевищувати поточну.
     *
     * @return введена користувачем дата.
     */
    public static LocalDateTime nextDate() {
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

    /**
     * Метод перевірки календарної дати.
     *
     * @return введена користувачем дата.
     */
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

    /**
     * Метод перевірки правильності введення кінцевої дати відносно початкової.
     *
     * @param start - початкова дата.
     * @return введена користувачем кінцева дата.
     */
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

    /**
     * Метод перевірки користувача на українське походження.
     *
     * @return true - якщо користувач - справжній козак, false - ...співчуваю, йому дуже не пощастило)))
     */
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
                ConsoleView.printWarning("ЙДИ ЗА РУСЬКИМ КОРАБЛЕМ, МОСКАЛЬ ПРОКЛЯТИЙ!!!");
                return false;
            }

            if (error) {
                ConsoleView.printNote("В тебе ще " + attempts + " спроб");
            }
        } while (error);
        return true;
    }
}
