package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.DataTest;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.ProgramModes;
import ua.edu.sumdu.j2se.tokarenko.tasks.view.ConsoleView;
import ua.edu.sumdu.j2se.tokarenko.tasks.view.PrintTasksView;

public class ChangeTaskController extends TaskActionsController {

    /**
     * Method that controls the process of changing the selected task
     * @param taskList collection with tasks
     * @param status constant that indicates the action to be performed for the changed task
     * @param controller controller to restart the notification thread
     * @return constant indicating which next action to perform in the program
     */
    @Override
    public ProgramModes process(AbstractTaskList taskList,
                                ProgramModes status,
                                AlertsController controller) {
        if (getTempTask() == null) {
            if (DataTest.isEmptyList(taskList)) {
                PrintTasksView allTasksView = new PrintTasksView();
                allTasksView.printAllTasksWithIndex(taskList);
                view.printTaskSelection();
                try {
                    choiceTask(taskList, Integer.parseInt(in.nextLine()));
                } catch (CloneNotSupportedException e) {
                    logger.error(e+"\n");
                    return ProgramModes.MAIN_MENU;
                }

            } else {
                logger.error("Collection is empty " + taskList+"\n");
                ConsoleView.printTitle("You do not have any tasks. You must create them to change the tasks.");
                return ProgramModes.MAIN_MENU;
            }
        }

        if (status.equals(ProgramModes.SET_TITLE)) {
            view.printRequestNewTitle();
            title = ConsoleInputController.nextString();
            editTitle();
            logger.debug("Set new task title for task " + getTempTask().toString());

        } else if (status.equals(ProgramModes.SET_ACTIVE)) {
            view.printRequestNewActivity();
            activity = ConsoleInputController.nextBoolean();
            editActivity();

        } else if (status.equals(ProgramModes.SET_REPEATING_TIME)) {
            view.printRequestNewStartTime();
            start = ConsoleInputController.nextTime();
            view.printRequestNewEndTime();
            end = ConsoleInputController.nextTime();
            view.printRequestNewInterval();
            interval = ConsoleInputController.nextInt();
            editTimeRepeatedTasks();

        } else if (status.equals(ProgramModes.SET_NON_REPEATING_TIME)) {
            view.printRequestNewStartTime();
            start = ConsoleInputController.nextTime();
            editTimeNonRepeatedTasks();

        } else if (status.equals(ProgramModes.SAVE)) {
            saveController.process(taskList, this);

            return controller.process(taskList);
        } else {
            logger.error("Program status is incorrect! status -"+status +"\n");
            throw new IllegalStateException("Program status is incorrect!");
        }

        return ProgramModes.CHANGE;
    }
}
