package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.ProgramModes;

public class CreateTaskController extends TaskActionsController {

    /**
     * Method that controls the process of adding a new task
     * @param taskList collection for tasks
     * @param status constant that indicates the action to be performed for the created task
     * @param controller controller to restart the notification thread
     * @return constant indicating which next action to perform in the program
     */
    @Override
    public ProgramModes process(AbstractTaskList taskList,
                                ProgramModes status,
                                AlertsController controller) {
        if (getTempTask() == null) {
            createNewTask();
        }

        if (status.equals(ProgramModes.SET_TITLE)) {
            view.printRequestNewTitle();
            title = ConsoleInputController.nextString();

            editTitle();

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

        return ProgramModes.ADD;
    }
}
