package ua.edu.sumdu.j2se.tokarenko.tasks.view;

import ua.edu.sumdu.j2se.tokarenko.tasks.model.Task;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.Prettier;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

public class PrintCalendarView extends PrintTasksView {
    public void printChoiceStartTime() {
        lineSeparator();
        printTitle("Enter the start date of the period");
        System.out.println("-->");

    }

    public void printChoiceEndTime() {
        lineSeparator();
        printTitle("Enter the end date of the period");
        System.out.println("-->");

    }

    public void printCalendarTasks(SortedMap<LocalDateTime, Set<Task>> taskMap) {
        lineSeparator();

        printTitle(Prettier.prettyTitle("The name of the task ", 30) + " Task execution time");

        for (LocalDateTime time : taskMap.keySet()) {
            for (Task task : taskMap.get(time)) {
                printShortTaskInfo(task, time);
            }
        }

        lineSeparator();
        lineSeparator();
    }
}
