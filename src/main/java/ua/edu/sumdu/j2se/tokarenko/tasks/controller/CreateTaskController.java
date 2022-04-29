package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.ProgramModes;

import static ua.edu.sumdu.j2se.tokarenko.tasks.utils.Exceptions.unknownModeException;

public class CreateTaskController extends TaskActionsController {
    @Override
    public ProgramModes process(AbstractTaskList taskList, ProgramModes mode) {
        if (getBufferedTask() == null) {
            createNewTask();
        }

        switch (mode) {
            case SET_TITLE:
                taskActionsView.setTaskTitle();
                title = ConsoleInputController.nextString();
                editTitle();
                break;

            case SET_ACTIVE:
                taskActionsView.setTaskIsActive();
                isActive = ConsoleInputController.nextBoolean();
                editIsActive();
                break;

            case SET_REPEATING_TIME:
                taskActionsView.setTaskStartTime();
                start = ConsoleInputController.nextTime();
                taskActionsView.setTaskEndTime();
                end = ConsoleInputController.nextEndDate(start);
                taskActionsView.setTaskNewInterval();
                interval = ConsoleInputController.nextInt();
                editTimeRepeating();
                break;

            case SET_NON_REPEATING_TIME:
                taskActionsView.setTaskStartTime();
                start = ConsoleInputController.nextTime();
                editTimeNotRepeating();
                break;

            case SAVE:
                if (!isTaskNull()) {
                    savingController.process(taskList, this);
                    taskActionsView.createTask();
                } else {
                    taskActionsView.creatingErrorTask();
                    clearBuffer();
                }
                return ProgramModes.MAIN_MENU;

            case MAIN_MENU:
                clearBuffer();
                return ProgramModes.MAIN_MENU;

            default:
                logger.fatal("Mode is incorrect: " + mode);
                logger.error("Failed creating the task");
                throw unknownModeException;
        }
        return ProgramModes.ADD;
    }
}
