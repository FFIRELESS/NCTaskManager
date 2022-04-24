package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.DataTest;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.ProgramModes;
import ua.edu.sumdu.j2se.tokarenko.tasks.view.ConsoleView;
import ua.edu.sumdu.j2se.tokarenko.tasks.view.PrintTasksView;

public class PrintTasksController extends BaseController {
    protected static final Logger logger =
            Logger.getLogger(PrintTasksController.class);

    /**
     * Cass to display information in the console
     */
    private final PrintTasksView showAllTasksView = new PrintTasksView();

    /**
     * Method that manages the creation and display of all existing tasks
     * @param taskList collection with tasks
     * @return constant indicating which next action to perform in the program
     */
    @Override
    public ProgramModes process(AbstractTaskList taskList) {
        if (DataTest.isEmptyList(taskList)) {
            logger.debug("Display of all existing tasks " +taskList);
            showAllTasksView.printAllTasks(taskList);

        } else {
            logger.debug("Collection is empty " +taskList);
            ConsoleView.printTitle("You do not have any tasks. You must create them to view tasks.");

        }

        return ProgramModes.MAIN_MENU;
    }
}
