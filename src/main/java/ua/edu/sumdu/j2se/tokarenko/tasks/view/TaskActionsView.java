package ua.edu.sumdu.j2se.tokarenko.tasks.view;

public class TaskActionsView extends PrintTasksView {

    public void setTaskTitle() {
        newEmptyLine();
        printChooser("Введіть назву: ");
    }

    public void setTaskIsActive() {
        newEmptyLine();
        printChooser("Активувати задачу? \"true\"(\"1\") - так, \"false\"(\"0\") - ні: ");
    }

    public void setTaskStartTime() {
        newEmptyLine();
        printChooser("Введіть час початку в форматі (латинською) \"2022-01-01T13:32:00\": ");
    }

    public void setTaskEndTime() {
        newEmptyLine();
        printChooser("Введіть час закінчення в форматі (латинською) \"2022-01-01T13:32:00\": ");
    }

    public void setTaskNewInterval() {
        newEmptyLine();
        printChooser("Введіть інтервал повторення в секундах: ");
    }

    public void setTaskNumber() {
        newEmptyLine();
        printChooser("Введіть номер задачі: ");
    }

    public void setPeriodStartTime() {
        newEmptyLine();
        printChooser("Введіть час початку в форматі (латинською) \"2022-01-01T13:32:00\": ");
    }

    public void setPeriodEndTime() {
        newEmptyLine();
        printChooser("Введіть час закінчення в форматі (латинською) \"2022-01-01T13:32:00\": ");
    }

    public void removeTask() {
        newEmptyLine();
        printWarning("Задачу видалено");
        newEmptyLine();
    }

    public void createTask() {
        newEmptyLine();
        printParagraph("Задачу створено");
    }

    public void creatingErrorTask() {
        newEmptyLine();
        printWarning("Задачу не було створено");
    }
}
