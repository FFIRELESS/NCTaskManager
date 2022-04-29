package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.ProgramModes;
import ua.edu.sumdu.j2se.tokarenko.tasks.view.ConsoleView;

public class SavingController extends BaseController {
    @Override
    public ProgramModes process(AbstractTaskList taskList, TaskActionsController taskController) {
        taskList.add(taskController.getBufferedTask());
        taskController.clearBuffer();
        return ProgramModes.MAIN_MENU;
    }
}
