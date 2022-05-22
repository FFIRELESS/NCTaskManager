package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.User;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.DataTest;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.ProgramModes;
import ua.edu.sumdu.j2se.tokarenko.tasks.view.ConsoleView;
import ua.edu.sumdu.j2se.tokarenko.tasks.view.PrintTasksView;

public class PrintTasksController extends BaseController {
    protected static final Logger logger = Logger.getLogger(PrintTasksController.class);

    private final PrintTasksView showAllTasksView = new PrintTasksView();

    /**
     * Метод контролю виведення повного списку всіх існуючих задач.
     *
     * @param taskList колекція задач.
     * @param user     поточний користувач.
     * @return наступний(обраний) режим програми.
     */
    @Override
    public ProgramModes process(AbstractTaskList taskList, User user) {
        if (DataTest.isNotEmptyUserTaskList(taskList, user)) {
            showAllTasksView.printAllTasks(taskList, user);
            logger.debug("Showing full tasks list");

        } else {
            ConsoleView.newEmptyLine();
            ConsoleView.printWarning("Задачі не знайдено");
            ConsoleView.newEmptyLine();
            logger.debug("Tasks list is empty");
        }
        return ProgramModes.MAIN_MENU;
    }
}
