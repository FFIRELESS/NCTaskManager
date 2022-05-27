package ua.edu.sumdu.j2se.tokarenko.tasks.view;

import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.Task;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.Prettier;

import java.time.LocalDateTime;

public class PrintTasksView extends ConsoleView {
    /**
     * Метод, що виводить шапку скороченої таблиці задач.
     */
    public void printTableShortHeader() {
        printTableHeading(String.valueOf(
                Prettier.prettyText("Задача", 33)
                        .append(Prettier.prettyText("Активна", 10))
                        .append(Prettier.prettyText("Дата та час", 11))));
    }

    /**
     * Метод, що виводить шапку розширеної таблиці задач.
     */
    public void printTableFullHeader() {
        printTableHeading(String.valueOf(
                Prettier.prettyText("Задача", 31)
                        .append(Prettier.prettyText("Активна", 9))
                        .append(Prettier.prettyText("Повтор", 9))
                        .append(Prettier.prettyText("Інтервал", 14))
                        .append(Prettier.prettyText("Час початку", 31))
                        .append(Prettier.prettyText("Час завершення", 14))));
    }

    /**
     * Метод, що виводить шапку розширеної таблиці задач з індексами.
     */
    public void printTableIndexHeader() {
        printTableHeading(String.valueOf(
                Prettier.prettyText("№", 5)
                        .append(Prettier.prettyText("Назва", 31))
                        .append(Prettier.prettyText("Активна", 9))
                        .append(Prettier.prettyText("Повтор", 9))
                        .append(Prettier.prettyText("Інтервал", 14))
                        .append(Prettier.prettyText("Час початку", 31))
                        .append(Prettier.prettyText("Час завершення", 14))));
    }

    /**
     * Метод, що виводить розширену таблицю задач.
     *
     * @param taskList колекція задач.
     */
    public void printAllTasks(AbstractTaskList taskList) {
        newEmptyLine();
        printTitle("Повний список задач:");
        printTableFullHeader();

        for (Task task : taskList) {
            printFullTaskInfo(task, -1);
        }
        newEmptyLine();
    }

    /**
     * Метод, що виводить розширену таблицю задач з індексами.
     *
     * @param taskList колекція задач.
     */
    public void printAllTasksWithIndex(AbstractTaskList taskList) {
        newEmptyLine();
        printTitle("Ваші задачі:");
        printTableIndexHeader();

        for (int i = 0; i < taskList.size(); i++) {
            printFullTaskInfo(taskList.getTask(i), i);
        }
        newEmptyLine();
    }

    /**
     * Метод, що виводить скорочену інформацію про задачу в задану дату.
     *
     * @param task задача.
     * @param time задана дата.
     */
    public void printShortTaskInfo(Task task, LocalDateTime time) {
        StringBuilder printTask = new StringBuilder();

        printTask.append(ANSI_YELLOW + "* " + ANSI_RESET);
        printTask.append(Prettier.prettyText(task.getTitle(), 30)).append(" ");

        if (task.isActive()) {
            printTask.append(Prettier.prettyText("✓ так", 10));
        } else {
            printTask.append(Prettier.prettyText("× ні", 10));
        }
        printTask.append(Prettier.prettyDate(time));
        System.out.println(printTask);
    }

    /**
     * Метод, що виводить розширену інформацію про задачу (та її індекс).
     *
     * @param task задача.
     * @param idx  індекс задачі.
     */
    public void printFullTaskInfo(Task task, int idx) {
        StringBuilder printTask = new StringBuilder();

        if (idx > -1) {
            printTask.append(Prettier.prettyText(String.valueOf(idx), 5));
        }

        printTask.append(Prettier.prettyText(task.getTitle(), 30)).append(" ");

        if (task.isActive()) {
            printTask.append(Prettier.prettyText("✓ так", 9));
        } else {
            printTask.append(Prettier.prettyText("× ні", 9));
        }

        if (task.isRepeating()) {
            printTask.append(Prettier.prettyText("✓ так", 9));
        } else {
            printTask.append(Prettier.prettyText("× ні", 9));
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
