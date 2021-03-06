package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.ArrayUserList;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.Task;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.DataTest;
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

    /**
     * Метод контролю зберігання користувачів в буфері.
     *
     * @param userList       колекція користувачів.
     * @param userController контролер для очищення буфера користувачів.
     * @return наступний(обраний) режим програми.
     */
    @Override
    public ProgramModes process(ArrayUserList userList, UserActionsController userController) {
        userList.add(UserActionsController.getBufferedUser());
        userController.clearBuffer();
        return ProgramModes.MAIN_MENU;
    }

    /**
     * Метод контролю зберігання колекції задач користувача в буфері всіх задач.
     *
     * @param tasks     колекція задач.
     * @param userTasks колекція задач користувача.
     */
    @Override
    public void process(AbstractTaskList tasks, ArrayTaskList userTasks) {
        if (DataTest.isEmptyList(userTasks)) {
            for (Task task : userTasks) {
                tasks.add(task);
            }
        }
    }
}
