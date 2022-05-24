package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.Task;
import ua.edu.sumdu.j2se.tokarenko.tasks.view.TaskActionsView;

import java.time.LocalDateTime;
import java.util.UUID;

public class TaskActionsController extends BaseController {
    protected static final Logger logger = Logger.getLogger(TaskActionsController.class);

    private Task bufferedTask;
    protected BaseController savingController = new SavingController();
    protected TaskActionsView taskActionsView = new TaskActionsView();

    protected LocalDateTime start;
    protected LocalDateTime end;
    protected Integer interval;
    protected String title;
    protected UUID userId;
    protected Boolean isActive;

    /**
     * Метод створення буферизованої задачі.
     */
    public void createNewTask() {
        bufferedTask = new Task();
        logger.debug("Created new buffered task");
    }

    /**
     * Метод обирання буферизованої задачі.
     *
     * @param taskList колекція задач.
     * @param idx      індекс задачі для обирання.
     * @throws CloneNotSupportedException при помилці клонування задачі.
     */
    public void selectedTask(AbstractTaskList taskList, int idx) throws CloneNotSupportedException {
        bufferedTask = taskList.getTask(idx).clone();
        taskList.remove(taskList.getTask(idx));
        logger.debug("Buffered task selected: " + bufferedTask.toString());
    }

    /**
     * Метод перевірки буферизованої задачі на null.
     */
    public boolean isTaskNull() {
        logger.debug("Checked buffered task for null: " + bufferedTask);
        if (bufferedTask.getStartTime() == null || bufferedTask.getTitle() == null) {
            logger.debug("Buffered task is null");
            return true;
        } else {
            logger.debug("Buffered task is not null");
            return false;
        }
    }

    /**
     * Метод редагування назви буферизованої задачі.
     */
    public void editTitle() {
        bufferedTask.setTitle(title);
        logger.debug("Set buffered task title: '" + title + "'");
    }

    /**
     * Метод редагування активності буферизованої задачі.
     */
    public void editIsActive() {
        bufferedTask.setActive(isActive);
        logger.debug("Set buffered task activity: " + isActive);
    }

    /**
     * Метод редагування дати неповторюваної буферизованої задачі.
     */
    public void editTimeNotRepeating() {
        bufferedTask.setTime(start);
        logger.debug("Set buffered task start time: " + start);
    }

    /**
     * Метод редагування дати повторюваної буферизованої задачі.
     */
    public void editTimeRepeating() {
        bufferedTask.setTime(start, end, interval);
        logger.debug("Set buffered task time: " + start + " | " + end + " | " + interval);
    }

    /**
     * Метод редагування ідентифікатору користувача буферизованої задачі.
     */
    public void setUserId() {
        bufferedTask.setUserId(userId);
        logger.debug("Set buffered task userId: '" + userId + "'");
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
     * Метод очищення буферизованої задачі.
     */
    public void clearBuffer() {
        bufferedTask = null;
        logger.debug("Buffered task cleared");
    }
}
