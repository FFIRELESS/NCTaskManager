package ua.edu.sumdu.j2se.tokarenko.tasks.view;

public class TaskActionsView extends PrintTasksView {

    public void printRequestNewTitle() {
        lineSeparator();
        printTitle("Enter a new task title");
        System.out.print("-->");

    }

    public void printRequestNewActivity() {
        lineSeparator();
        printTitle("Enter a new task activity");
        System.out.print("-->");

    }

    public void printRequestNewStartTime() {
        lineSeparator();
        printTitle("Enter a new task start time");
        System.out.print("-->");

    }

    public void printRequestNewEndTime() {
        lineSeparator();
        printTitle("Enter a new task end time");
        System.out.print("-->");

    }

    public void printRequestNewInterval() {
        lineSeparator();
        printTitle("Enter a new task interval");
        System.out.print("-->");

    }

    public void printTaskSelection() {
        lineSeparator();
        printTitle("Enter the number of the task you want to edit");
        System.out.print("-->");

    }
}
