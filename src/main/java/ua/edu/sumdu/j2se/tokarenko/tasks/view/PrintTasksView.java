package ua.edu.sumdu.j2se.tokarenko.tasks.view;

import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.Task;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.Prettier;

import java.time.LocalDateTime;

public class PrintTasksView extends ConsoleView {
    public void printTableShortHeader() {
        printTableHeading(String.valueOf(
                Prettier.prettyTitle("Задача", 32)
                        .append(Prettier.prettyTitle("Активна", 10))
                        .append(Prettier.prettyTitle("Дата та час", 11))));
    }

    public void printTableFullHeader() {
        printTableHeading(String.valueOf(
                Prettier.prettyTitle("Задача", 30)
                        .append(Prettier.prettyTitle("Активна", 9))
                        .append(Prettier.prettyTitle("Повтор", 9))
                        .append(Prettier.prettyTitle("Інтервал", 14))
                        .append(Prettier.prettyTitle("Час початку", 31))
                        .append(Prettier.prettyTitle("Час завершення", 14))));
    }

    public void printTableIndexHeader() {
        printTableHeading(String.valueOf(
                Prettier.prettyTitle("№", 5)
                        .append(Prettier.prettyTitle("Назва", 30))
                        .append(Prettier.prettyTitle("Активна", 9))
                        .append(Prettier.prettyTitle("Повтор", 9))
                        .append(Prettier.prettyTitle("Інтервал", 14))
                        .append(Prettier.prettyTitle("Час початку", 31))
                        .append(Prettier.prettyTitle("Час завершення", 14))));
    }

    public void printAllTasks(AbstractTaskList taskList) {
        newEmptyLine();
        printTitle("Повний список задач: ");
        printTableFullHeader();

        for (Task task : taskList) {
            printFullTaskInfo(task, -1);
        }
        newEmptyLine();
    }

    public void printAllTasksWithIndex(AbstractTaskList taskList) {
        newEmptyLine();
        printTitle("Ваші задачі: ");
        printTableIndexHeader();

        for (int i = 0; i < taskList.size(); i++) {
            printFullTaskInfo(taskList.getTask(i), i);
        }
        newEmptyLine();
    }

    public void printShortTaskInfo(Task task, LocalDateTime time) {
        StringBuilder printTask = new StringBuilder();

        printTask.append(ANSI_YELLOW + "* " + ANSI_RESET);
        printTask.append(Prettier.prettyTitle(task.getTitle(), 30));

        if (task.isActive()) {
            printTask.append(Prettier.prettyTitle("✓ так", 10));
        } else {
            printTask.append(Prettier.prettyTitle("× ні", 10));
        }
        printTask.append(Prettier.prettyDate(time));
        System.out.println(printTask);
    }

    public void printFullTaskInfo(Task task, int idx) {
        StringBuilder printTask = new StringBuilder();

        if (idx > -1) {
            printTask.append(Prettier.prettyTitle(String.valueOf(idx), 5));
        }

        printTask.append(Prettier.prettyTitle(task.getTitle(), 30));

        if (task.isActive()) {
            printTask.append(Prettier.prettyTitle("✓ так", 9));
        } else {
            printTask.append(Prettier.prettyTitle("× ні", 9));
        }

        if (task.isRepeating()) {
            printTask.append(Prettier.prettyTitle("✓ так", 9));
        } else {
            printTask.append(Prettier.prettyTitle("× ні", 9));
        }
        printTask.append(Prettier.prettyInterval(task.getRepeatInterval(), task.getStartTime(), 14))
                .append(Prettier.prettyDate(task.getStartTime()))
                .append("   ");

        if (task.isRepeating()) {
            printTask.append(Prettier.prettyDate(task.getEndTime()));
        } else {
            printTask.append("-");
        }
        System.out.println(printTask);
    }
}
