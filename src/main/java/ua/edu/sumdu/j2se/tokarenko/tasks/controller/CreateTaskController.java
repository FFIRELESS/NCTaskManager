package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.User;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.ProgramModes;

import static ua.edu.sumdu.j2se.tokarenko.tasks.utils.Exceptions.unknownModeException;

public class CreateTaskController extends TaskActionsController {
    /**
     * Метод контролю створення задач.
     *
     * @param taskList колекція задач.
     * @param mode     режим програми.
     * @param user     поточний користувач.
     * @return наступний(обраний) режим програми.
     * @throws NullPointerException якщо режим програми невірний.
     */
    @Override
    public ProgramModes process(AbstractTaskList taskList, ProgramModes mode, User user) {
        if (getBufferedTask() == null) {
            createNewTask();
            userId = user.getUserId();
            setUserId();
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
                start = ConsoleInputController.nextDate();
                taskActionsView.setTaskEndTime();
                end = ConsoleInputController.nextEndDate(start);
                taskActionsView.setTaskNewInterval();
                interval = ConsoleInputController.nextInt();
                editTimeRepeating();
                break;

            case SET_NON_REPEATING_TIME:
                taskActionsView.setTaskStartTime();
                start = ConsoleInputController.nextDate();
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
