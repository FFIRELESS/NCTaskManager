package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.Task;
import ua.edu.sumdu.j2se.tokarenko.tasks.view.TaskActionsView;

import java.time.LocalDateTime;

public class TaskActionsController extends BaseController {
    protected static final Logger logger = Logger.getLogger(TaskActionsController.class);

    private Task bufferedTask;
    protected BaseController savingController = new SavingController();
    protected TaskActionsView taskActionsView = new TaskActionsView();

    protected LocalDateTime start;
    protected LocalDateTime end;
    protected Integer interval;
    protected String title;
    protected Boolean isActive;

    /**
     * Метод створення задачі.
     */
    public void createNewTask() {
        bufferedTask = new Task();
    }

    /**
     * Метод обирання даної задачі.
     *
     * @param taskList колекція задач.
     * @param idx      індекс задачі для обирання.
     * @throws CloneNotSupportedException при помилці клонування задачі.
     */
    public void selectedTask(AbstractTaskList taskList, int idx) throws CloneNotSupportedException {
        bufferedTask = taskList.getTask(idx).clone();
        taskList.remove(taskList.getTask(idx));

        logger.debug("Task selected: " + bufferedTask.toString());
    }

    /**
     * Метод перевірки задачі на null.
     */
    public boolean isTaskNull() {
        return start == null || title == null;
    }

    /**
     * Метод редагування назви задачі.
     */
    public void editTitle() {
        bufferedTask.setTitle(title);
    }

    /**
     * Метод редагування активності задачі.
     */
    public void editIsActive() {
        bufferedTask.setActive(isActive);
    }

    /**
     * Метод редагування дати неповторюваної задачі.
     */
    public void editTimeNotRepeating() {
        bufferedTask.setTime(start);
    }

    /**
     * Метод редагування дати повторюваної задачі.
     */
    public void editTimeRepeating() {
        bufferedTask.setTime(start, end, interval);
    }

    /**
     * Метод отримання буферизованої задачі.
     *
     * @return буферизовану задачу.
     */
    public Task getBufferedTask() {
        return bufferedTask;
    }

    /**
     * Метод очищення буферу задач.
     */
    public void clearBuffer() {
        bufferedTask = null;
        logger.debug("Task buffer cleared");
    }
}
