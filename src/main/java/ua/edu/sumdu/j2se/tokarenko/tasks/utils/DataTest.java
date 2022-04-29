package ua.edu.sumdu.j2se.tokarenko.tasks.utils;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;


public class DataTest {
    private static final Logger logger = Logger.getLogger(DataTest.class);

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

    public static boolean isEmptyList(AbstractTaskList list) {
        logger.debug("Checking for an empty list: " + list.getClass());
        return list.size() != 0;
    }
}

