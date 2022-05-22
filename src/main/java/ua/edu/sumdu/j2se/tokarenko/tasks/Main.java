package ua.edu.sumdu.j2se.tokarenko.tasks;

import ua.edu.sumdu.j2se.tokarenko.tasks.controller.MainController;

/**
 * NCTaskManager - програма, що є контролером задач
 * користувача протягом життєвого таймлайну.
 *
 * @author F.FIRELESS
 * @version 1.2 beta
 * @since 2022-05-22
 */

public class Main {
    public static void main(String[] args) {
        MainController mainController = new MainController();
        mainController.process();
    }
}
