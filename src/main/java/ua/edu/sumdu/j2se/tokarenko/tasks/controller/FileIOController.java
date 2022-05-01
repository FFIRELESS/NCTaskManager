package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.TaskIO;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.ListTypes;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.TaskListFactory;

import java.io.File;

public class FileIOController extends BaseController {
    protected static final Logger logger = Logger.getLogger(FileIOController.class);

    private final File file = new File("savedTasks.json");

    /**
     * Метод зчитування задач з файлу json.
     *
     * @return колекція задач.
     */
    @Override
    public AbstractTaskList readFileProcess() {
        AbstractTaskList tasks = TaskListFactory.createTaskList(ListTypes.types.ARRAY);

        try {
            TaskIO.readText(tasks, file);
        } catch (Exception e) {
            System.out.println("Помилка читання з файлу");
        }
        logger.debug("Reading tasks from file: " + file.getAbsolutePath());
        return tasks;
    }

    /**
     * Метод запису задач до файлу json.
     *
     * @param taskList колекція задач.
     */
    @Override
    public void writeFileProcess(AbstractTaskList taskList) {
        try {
            TaskIO.writeText(taskList, file);
        } catch (Exception e) {
            System.out.println("Помилка запису до файлу");
        }
        logger.debug("Writing tasks to file: " + file.getAbsolutePath());
    }
}
