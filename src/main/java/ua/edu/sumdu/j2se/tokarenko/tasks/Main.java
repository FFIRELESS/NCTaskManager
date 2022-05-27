package ua.edu.sumdu.j2se.tokarenko.tasks;

import ua.edu.sumdu.j2se.tokarenko.tasks.controller.MainController;

/**
 * NCTaskManager - програма, що є контролером повсякденних
 * задач користувачів протягом життєвого таймлайну.
 *
 * @author F.FIRELESS
 * @version 1.2.4 beta
 * @since 2022-05-27
 */

public class Main {
    public static void main(String[] args) {
        MainController mainController = new MainController();
        mainController.process();
    }
}
