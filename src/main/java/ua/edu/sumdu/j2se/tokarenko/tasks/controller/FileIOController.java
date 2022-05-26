package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.ArrayUserList;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.TaskIO;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.UserIO;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.ListTypes;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.TaskListFactory;

import java.io.File;

public class FileIOController extends BaseController {
    protected static final Logger logger = Logger.getLogger(FileIOController.class);

    private final File tasksFile = new File("tasks.json");
    private final File usersFile = new File("users.json");

    /**
     * Метод зчитування задач з файлу json.
     *
     * @return колекція задач.
     */
    @Override
    public AbstractTaskList readTasksFileProcess() {
        AbstractTaskList tasks = TaskListFactory.createTaskList(ListTypes.types.ARRAY);

        logger.debug("Reading tasks from file: " + tasksFile.getAbsolutePath());

        try {
            TaskIO.readText(tasks, tasksFile);
        } catch (Exception e) {
            logger.warn("Error reading tasks from file");
        }
        return tasks;
    }

    /**
     * Метод зчитування користувачів з файлу json.
     *
     * @return колекція користувачів.
     */
    @Override
    public ArrayUserList readUsersFileProcess() {
        ArrayUserList users = new ArrayUserList();

        logger.debug("Reading users from file: " + usersFile.getAbsolutePath());

        try {
            UserIO.readText(users, usersFile);
        } catch (Exception e) {
            logger.warn("Error reading users from file");
        }
        return users;
    }

    /**
     * Метод запису задач до файлу json.
     *
     * @param taskList колекція задач.
     */
    @Override
    public void writeTasksFileProcess(AbstractTaskList taskList) {
        logger.debug("Writing tasks to file: " + tasksFile.getAbsolutePath());

        try {
            TaskIO.writeText(taskList, tasksFile);
        } catch (Exception e) {
            logger.error("Error writing tasks to file");
        }
    }

    /**
     * Метод запису користувачів до файлу json.
     *
     * @param userList колекція користувачів.
     */
    @Override
    public void writeUsersFileProcess(ArrayUserList userList) {
        logger.debug("Writing users to file: " + usersFile.getAbsolutePath());

        try {
            UserIO.writeText(userList, usersFile);
        } catch (Exception e) {
            logger.error("Error writing users to file");
        }
    }
}
