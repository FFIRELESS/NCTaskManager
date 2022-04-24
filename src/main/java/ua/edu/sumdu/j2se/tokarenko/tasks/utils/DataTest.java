package ua.edu.sumdu.j2se.tokarenko.tasks.utils;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;


public class DataTest {
    private static final Logger logger = Logger.getLogger(DataTest.class);

    public static boolean isEmptyString(String string) {
        logger.debug("Checking for an empty string: " + string);
        char ch;

        if (string.length() == 0) {
            return true;
        }

        for (int i = 0; i < string.length(); ++i) {
            ch = string.charAt(i);
            if (ch != '\t' && ch != ' ') {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmptyList(AbstractTaskList list) {
        logger.debug("Checking for an empty collection: " + list.getClass());
        return list.size() != 0;
    }
}

