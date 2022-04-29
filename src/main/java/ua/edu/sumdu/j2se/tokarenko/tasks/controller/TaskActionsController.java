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

    public void createNewTask() {
        bufferedTask = new Task();
    }

    public void selectedTask(AbstractTaskList taskList, int idx) throws CloneNotSupportedException {
        bufferedTask = taskList.getTask(idx).clone();
        taskList.remove(taskList.getTask(idx));

        logger.debug("Task selected: " + bufferedTask.toString());
    }

    public boolean isTaskNull() {
        return start == null || title == null;
    }

    public void editTitle() {
        bufferedTask.setTitle(title);
    }

    public void editIsActive() {
        bufferedTask.setActive(isActive);
    }

    public void editTimeRepeating() {
        bufferedTask.setTime(start, end, interval);
    }

    public void editTimeNotRepeating() {
        bufferedTask.setTime(start);
    }

    public Task getBufferedTask() {
        return bufferedTask;
    }

    public void clearBuffer() {
        bufferedTask = null;
        logger.debug("Task buffer cleared");
    }
}
