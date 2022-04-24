package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.ProgramModes;

public class SavingController extends BaseController {
    @Override
    public ProgramModes process(AbstractTaskList taskList, TaskActionsController taskController) {
        taskList.add(taskController.getTempTask());
        taskController.removeTempTask();
        return ProgramModes.MAIN_MENU;
    }
}
