package ua.edu.sumdu.j2se.tokarenko.tasks.view;

import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.Task;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.Prettier;

import java.time.LocalDateTime;

public class PrintTasksView extends ConsoleView {
    public void printAllTasks(AbstractTaskList taskList) {
        lineSeparator();
        printTitle("Full tasks list: ");

        for (Task task : taskList) {
            printFullTaskInfo(task);

        }
        lineSeparator();
        newEmptyLine();
    }

    public void printAllTasksWithIndex(AbstractTaskList taskList) {
        lineSeparator();
        printTitle("Tasks that you now have");

        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + " ");
            printFullTaskInfo(taskList.getTask(i));

        }
        lineSeparator();
        lineSeparator();
    }

    public void printShortTaskInfo(Task task, LocalDateTime time) {
        StringBuilder printTask = new StringBuilder();

        printTask.append("* ");
        printTask.append(Prettier.prettyTitle(task.getTitle(),30));

        if (task.isActive()) {
            printTask.append(Prettier.prettyTitle("✓ active",10));
        } else {
            printTask.append(Prettier.prettyTitle("× not active",10));
        }

        printTask.append('|');
        printTask.append(Prettier.prettyDate(time));

        System.out.println(printTask);

    }

    public void printFullTaskInfo(Task task) {
        StringBuilder printTask = new StringBuilder();

        printTask.append("-> ");
        printTask.append(Prettier.prettyTitle(task.getTitle(),30));

        if (task.isActive()) {
            printTask.append(Prettier.prettyTitle("✓ active",13));
        } else {
            printTask.append(Prettier.prettyTitle("× not active",13));
        }

        printTask.append('|');

        if (task.isRepeating()) {
            printTask.append(Prettier.prettyTitle("repeated",12));
        } else {
            printTask.append(Prettier.prettyTitle("not repeated",12));
        }
        printTask.append('|');
        printTask.append(Prettier.prettyTitle(String.valueOf(task.getRepeatInterval()/60000),5));
        printTask.append('|');
        printTask.append(Prettier.prettyDate(task.getStartTime()));
        printTask.append('|');
        printTask.append(Prettier.prettyDate(task.getEndTime()));
        printTask.append('|');

        System.out.println(printTask);
    }
}
