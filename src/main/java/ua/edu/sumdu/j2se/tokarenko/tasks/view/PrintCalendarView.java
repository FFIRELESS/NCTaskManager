package ua.edu.sumdu.j2se.tokarenko.tasks.view;

import ua.edu.sumdu.j2se.tokarenko.tasks.model.Task;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

public class PrintCalendarView extends PrintTasksView {
    public void printCalendarTasks(SortedMap<LocalDateTime, Set<Task>> taskMap) {
        printTableShortHeader();

        for (LocalDateTime time : taskMap.keySet()) {
            for (Task task : taskMap.get(time)) {
                printShortTaskInfo(task, time);
            }
        }
        newEmptyLine();
    }
}
