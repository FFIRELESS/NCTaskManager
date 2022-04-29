package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.DataTest;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.ProgramModes;
import ua.edu.sumdu.j2se.tokarenko.tasks.view.ConsoleView;
import ua.edu.sumdu.j2se.tokarenko.tasks.view.PrintTasksView;

import static ua.edu.sumdu.j2se.tokarenko.tasks.utils.Exceptions.unknownModeException;

public class EditTaskController extends TaskActionsController {
    @Override
    public ProgramModes process(AbstractTaskList taskList, ProgramModes mode) {
        if (getBufferedTask() == null) {
            if (DataTest.isEmptyList(taskList)) {
                PrintTasksView allTasksView = new PrintTasksView();

                ConsoleView.newEmptyLine();
                ConsoleView.printParagraph("Оберіть задачу для редагування з таблиці нижче");
                allTasksView.printAllTasksWithIndex(taskList);

                taskActionsView.setTaskNumber();

                try {
                    selectedTask(taskList, ConsoleInputController.nextIntInRange(0, taskList.size() - 1));
                    return ProgramModes.EDIT;
                } catch (CloneNotSupportedException e) {
                    logger.error("Exception: " + e);
                    return ProgramModes.MAIN_MENU;
                }
            } else {
                logger.error("List is empty: " + taskList);

                ConsoleView.printWarning("Задачі не знайдено");
                return ProgramModes.MAIN_MENU;
            }
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
                end = ConsoleInputController.nextTime();
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
                savingController.process(taskList, this);
                return ProgramModes.MAIN_MENU;

            case REMOVE:
                clearBuffer();
                taskActionsView.removeTask();
                return ProgramModes.MAIN_MENU;

            case SKIP:
                return ProgramModes.EDIT;

            default:
                logger.fatal("Mode is incorrect: " + mode);
                logger.error("Failed editing the task");
                throw unknownModeException;
        }
        return ProgramModes.EDIT;
    }
}
