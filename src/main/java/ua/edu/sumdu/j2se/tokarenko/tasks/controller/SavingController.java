package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.ProgramModes;

public class SavingController extends BaseController {
    /**
     * Метод контролю зберігання задач в буфері.
     *
     * @param taskList       колекція задач.
     * @param taskController контролер для очищення буфера задач.
     * @return наступний(обраний) режим програми.
     */
    @Override
    public ProgramModes process(AbstractTaskList taskList, TaskActionsController taskController) {
        taskList.add(taskController.getBufferedTask());
        taskController.clearBuffer();
        return ProgramModes.MAIN_MENU;
    }
}
