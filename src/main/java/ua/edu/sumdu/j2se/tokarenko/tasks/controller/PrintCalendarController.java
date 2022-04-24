package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.Task;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.Tasks;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.ProgramModes;
import ua.edu.sumdu.j2se.tokarenko.tasks.view.ConsoleView;
import ua.edu.sumdu.j2se.tokarenko.tasks.view.PrintCalendarView;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

public class PrintCalendarController extends BaseController {
    protected static final Logger logger =
            Logger.getLogger(PrintCalendarController.class);

    /**
     * Collection with calendar
     */
    protected SortedMap<LocalDateTime, Set<Task>> taskMap;

    /**
     * Cass to display information in the console
     */
    protected PrintCalendarView showCalendarView = new PrintCalendarView();

    /**
     * Buffer end time for calendar
     */
    protected LocalDateTime start;

    /**
     * Buffer end time for calendar
     */
    protected LocalDateTime end;

    /**
     * Method is responsible for setting the end time
     * @param end end time
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
        logger.debug("Setting the end time for calendar " + end);
    }

    /**
     * Method is responsible for setting the start time
     * @param start start time
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
        logger.debug("Setting the start time for calendar " + start);
    }

    /**
     * Method that controls the creation and display of tasks for a certain period of time
     * @param taskList collection with tasks
     * @return constant indicating which next action to perform in the program
     */
    @Override
    public ProgramModes process(AbstractTaskList taskList) {
        showCalendarView.printChoiceStartTime();
        setStart(ConsoleInputController.nextTime());
        showCalendarView.printChoiceEndTime();
        setEnd(ConsoleInputController.nextTime());

        taskMap = Tasks.calendar(taskList, start, end);

        if (taskMap.isEmpty()) {
            ConsoleView.printTitle("They are no tasks");
            logger.debug("Collection is empty " + taskMap);

        } else {
            ConsoleView.printTitle("Your tasks from " + start + " to " + end + ":");
            showCalendarView.printCalendarTasks(taskMap);
            logger.debug("Display of tasks for a certain period of time from " + start + " to " + end + "|");

        }

        return ProgramModes.MAIN_MENU;
    }
}
