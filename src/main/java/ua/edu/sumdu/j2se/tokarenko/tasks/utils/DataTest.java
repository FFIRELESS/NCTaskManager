package ua.edu.sumdu.j2se.tokarenko.tasks.utils;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.User;

public class DataTest {
    private static final Logger logger = Logger.getLogger(DataTest.class);

    /**
     * Метод перевірки рядку на пустоту.
     *
     * @param string рядок, що перевіряється.
     * @return true - рядок пустий, false - ні.
     */
    public static boolean isEmptyString(String string) {
        logger.debug("Checking for an empty string: " + string);
        char ch;

        if (string.length() == 0) {
            logger.debug("String is empty");
            return true;
        }

        for (int i = 0; i < string.length(); ++i) {
            ch = string.charAt(i);
            if (ch != '\t' && ch != ' ') {
                logger.debug("String is not empty");
                return false;
            }
        }
        logger.debug("String is empty");
        return true;
    }

    /**
     * Метод перевірки колекції на пустоту.
     *
     * @param list колекція, що перевіряється.
     * @return true - колекція пуста, false - ні.
     */
    public static boolean isEmptyList(AbstractTaskList list) {
        logger.debug("Checking for an empty list: " + list.getClass());
        return list.size() != 0;
    }

    /**
     * Метод перевірки колекції задач на вміст задач вказаного користувача.
     *
     * @param list колекція, що перевіряється.
     * @param user користувач.
     * @return true - колекція не пуста, false - пуста.
     */
    public static boolean isNotEmptyUserTaskList(AbstractTaskList list, User user) {
        logger.debug("Checking for an empty user task list: " + list.getClass());

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.getTask(i).getUserId().equals(user.getUserId())) {
                    return true;
                }
            }
        }
        return false;
    }
}

