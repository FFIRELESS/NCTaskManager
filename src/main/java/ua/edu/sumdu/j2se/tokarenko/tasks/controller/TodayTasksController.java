package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.Tasks;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.ProgramModes;
import ua.edu.sumdu.j2se.tokarenko.tasks.view.ConsoleView;

import java.time.LocalDateTime;

public class TodayTasksController extends PrintCalendarController {
    /**
     * Метод створення та виведення календаря задач на поточну дату.
     *
     * @param taskList колекція задач.
     * @return наступний(обраний) режим програми.
     */
    @Override
    public ProgramModes process(AbstractTaskList taskList) {
        setStartPeriod(LocalDateTime.now());
        setEndPeriod(LocalDateTime.now().plusHours(24 - LocalDateTime.now().getHour()));

        calendarTasks = Tasks.calendar(taskList, start, end);

        if (calendarTasks.isEmpty()) {
            ConsoleView.newEmptyLine();
            ConsoleView.printParagraph("На сьогодні Ви не маєте жодної задачі! Відпочивайте :)");
            logger.debug("Today tasks list is empty");
        } else {
            ConsoleView.newEmptyLine();
            ConsoleView.printParagraph("Задачі на сьогодні: ");
            printCalendarView.printCalendarTasks(calendarTasks);
        }
        return ProgramModes.MAIN_MENU;
    }
}
