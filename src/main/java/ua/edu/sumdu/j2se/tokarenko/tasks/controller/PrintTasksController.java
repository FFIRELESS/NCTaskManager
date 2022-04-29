package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.DataTest;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.ProgramModes;
import ua.edu.sumdu.j2se.tokarenko.tasks.view.ConsoleView;
import ua.edu.sumdu.j2se.tokarenko.tasks.view.PrintTasksView;

public class PrintTasksController extends BaseController {
    protected static final Logger logger = Logger.getLogger(PrintTasksController.class);

    private final PrintTasksView showAllTasksView = new PrintTasksView();

    @Override
    public ProgramModes process(AbstractTaskList taskList) {
        if (DataTest.isEmptyList(taskList)) {
            showAllTasksView.printAllTasks(taskList);
            logger.debug("Showing full tasks list");

        } else {
            ConsoleView.printWarning("Задачі не знайдено");
            logger.debug("Tasks list is empty");
        }
        return ProgramModes.MAIN_MENU;
    }
}
