package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.Tasks;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.ProgramModes;
import ua.edu.sumdu.j2se.tokarenko.tasks.view.ConsoleView;

import java.time.LocalDateTime;

public class TodayTasksController extends PrintCalendarController {

    /**
     * Method that controls the creation and display the to-do list for today
     * @param taskList collection with tasks
     * @return constant indicating which next action to perform in the program
     */
    @Override
    public ProgramModes process(AbstractTaskList taskList) {
        setStart(LocalDateTime.now());
        setEnd(LocalDateTime.now().plusHours(24 - LocalDateTime.now().getHour()));

        taskMap = Tasks.calendar(taskList, start, end);

        ConsoleView.lineSeparator();
        ConsoleView.lineSeparator();

        if (taskMap.isEmpty()) {
            ConsoleView.printTitle("They are no tasks for today");
            logger.debug("Collection is empty " + taskMap);

        } else {
            ConsoleView.printTitle("Your tasks for today:");
            showCalendarView.printCalendarTasks(taskMap);
            logger.debug("Display the to-do list for today");

        }
        return ProgramModes.MAIN_MENU;
    }
}
