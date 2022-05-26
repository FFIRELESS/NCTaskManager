package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.Task;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.Tasks;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.User;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.DataTest;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.ProgramModes;
import ua.edu.sumdu.j2se.tokarenko.tasks.view.ConsoleView;
import ua.edu.sumdu.j2se.tokarenko.tasks.view.PrintCalendarView;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

public class AlertsController extends BaseController {
    protected static final Logger logger = Logger.getLogger(AlertsController.class);

    private final PrintCalendarView view = new PrintCalendarView();

    /**
     * Метод визначення та контролю термінових задач.
     *
     * @param taskList колекція задач.
     * @return наступний(обраний) режим програми.
     */
    @Override
    public ProgramModes process(AbstractTaskList taskList) {
        if (DataTest.isEmptyList(taskList)) {
            SortedMap<LocalDateTime, Set<Task>> taskMap = Tasks.calendar(taskList,
                    LocalDateTime.now(), LocalDateTime.now().plusMinutes(5));

            if (!taskMap.isEmpty()) {
                ConsoleView.newEmptyLine();
                ConsoleView.printWarning("Термінові задачі:");
                view.printCalendarTasks(taskMap);

                logger.debug("Found urgent tasks");
            }
        }
        logger.debug("No urgent tasks found");
        return ProgramModes.MAIN_MENU;
    }
}
