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

    @Override
    public AbstractTaskList readFileProcess() {
        AbstractTaskList tasks = TaskListFactory.createTaskList(ListTypes.types.ARRAY);

        try {
            TaskIO.readText(tasks, file);
        } catch (Exception e) {
            System.out.println("Reading error. File not found");
        }
        logger.debug("Reading tasks from file: " + file.getAbsolutePath());
        return tasks;
    }

    @Override
    public void writeFileProcess(AbstractTaskList listToSave) {

        try {
            TaskIO.writeText(listToSave, file);
        } catch (Exception e) {
            System.out.println("Writing error. File not found");
        }
        logger.debug("Writing tasks to file: " + file.getAbsolutePath());
    }
}
