package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.User;

public class GetUserTasksController extends BaseController {
    /**
     * Метод, обирає задачі користувача із колекції всіх задач.
     *
     * @param tasks колекція задач.
     * @param user  поточний користувач.
     * @return колекція задач користувача.
     */
    @Override
    public ArrayTaskList process(AbstractTaskList tasks, User user) {
        ArrayTaskList userTasks = new ArrayTaskList();

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.getTask(i).getUserId().equals(user.getUserId())) {
                userTasks.add(tasks.getTask(i));
                tasks.remove(tasks.getTask(i));
                i--;
            }
        }
        return userTasks;
    }
}
