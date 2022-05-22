package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.Task;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.Tasks;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.User;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.ProgramModes;
import ua.edu.sumdu.j2se.tokarenko.tasks.view.ConsoleView;
import ua.edu.sumdu.j2se.tokarenko.tasks.view.PrintCalendarView;
import ua.edu.sumdu.j2se.tokarenko.tasks.view.TaskActionsView;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

import static ua.edu.sumdu.j2se.tokarenko.tasks.view.ConsoleView.*;

public class PrintCalendarController extends BaseController {
    protected static final Logger logger = Logger.getLogger(PrintCalendarController.class);

    protected SortedMap<LocalDateTime, Set<Task>> calendarTasks;
    protected PrintCalendarView printCalendarView = new PrintCalendarView();
    protected TaskActionsView taskActionsView = new TaskActionsView();
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Метод встановлення початкової дати шуканого періоду.
     *
     * @param start початкова дата періоду.
     */
    public void setStartPeriod(LocalDateTime start) {
        this.start = start;
    }

    /**
     * Метод встановлення кінцевої дати шуканого періоду.
     *
     * @param end кінцева дата періоду.
     */
    public void setEndPeriod(LocalDateTime end) {
        this.end = end;
    }

    /**
     * Метод створення та виведення календаря задач за заданий період.
     *
     * @param taskList колекція задач.
     * @param user     поточний користувач.
     * @return наступний(обраний) режим програми.
     */
    @Override
    public ProgramModes process(AbstractTaskList taskList, User user) {
        taskActionsView.setPeriodStartTime();
        setStartPeriod(ConsoleInputController.nextCalendarDate());
        taskActionsView.setPeriodEndTime();
        setEndPeriod(ConsoleInputController.nextCalendarDate());

        calendarTasks = Tasks.calendar(taskList, user, start, end);

        if (calendarTasks.isEmpty()) {
            newEmptyLine();
            ConsoleView.printWarning("Задачі не знайдено");
            newEmptyLine();

            logger.debug("Task list is empty: " + calendarTasks);
        } else {
            newEmptyLine();
            ConsoleView.printTitle("Задачі в період: " + start + " - " + end + ": ");
            printCalendarView.printCalendarTasks(calendarTasks);
            ConsoleView.printNote("*показуються тільки активні задачі");
            ConsoleView.newEmptyLine();
        }
        return ProgramModes.MAIN_MENU;
    }
}
