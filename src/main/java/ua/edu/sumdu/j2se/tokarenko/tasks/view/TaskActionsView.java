package ua.edu.sumdu.j2se.tokarenko.tasks.view;

public class TaskActionsView extends PrintTasksView {

    /**
     * Метод виведення поля редагування назви задачі.
     */
    public void setTaskTitle() {
        newEmptyLine();
        printChooser("Введіть назву: ");
    }

    /**
     * Метод виведення поля редагування активності задачі.
     */
    public void setTaskIsActive() {
        newEmptyLine();
        printChooser("Активувати задачу? \"true\"(\"1\") - так, \"false\"(\"0\") - ні: ");
    }

    /**
     * Метод виведення поля редагування початкового часу задачі.
     */
    public void setTaskStartTime() {
        newEmptyLine();
        printChooser("Введіть час початку в форматі (латинською) \"2022-01-01T13:32:00\": ");
    }

    /**
     * Метод виведення поля редагування кінцевого часу задачі.
     */
    public void setTaskEndTime() {
        newEmptyLine();
        printChooser("Введіть час закінчення в форматі (латинською) \"2022-01-01T13:32:00\": ");
    }

    /**
     * Метод виведення поля редагування інтервалу повторення задачі.
     */
    public void setTaskNewInterval() {
        newEmptyLine();
        printChooser("Введіть інтервал повторення в секундах: ");
    }

    /**
     * Метод виведення поля введення номеру(індексу) обраної задачі.
     */
    public void setTaskNumber() {
        newEmptyLine();
        printChooser("Введіть номер задачі: ");
    }

    /**
     * Метод виведення поля встановлення початкового часу періоду.
     */
    public void setPeriodStartTime() {
        newEmptyLine();
        printChooser("Введіть час початку в форматі (латинською) \"2022-01-01T13:32:00\": ");
    }

    /**
     * Метод виведення поля встановлення кінцевого часу періоду.
     */
    public void setPeriodEndTime() {
        newEmptyLine();
        printChooser("Введіть час закінчення в форматі (латинською) \"2022-01-01T13:32:00\": ");
    }

    /**
     * Метод виведення підтвердження видалення задачі.
     */
    public void removeTask() {
        newEmptyLine();
        printWarning("Задачу видалено");
        newEmptyLine();
    }

    /**
     * Метод виведення підтвердження створення задачі.
     */
    public void createTask() {
        newEmptyLine();
        printParagraph("Задачу створено");
    }

    /**
     * Метод виведення помилки створення задачі.
     */
    public void creatingErrorTask() {
        newEmptyLine();
        printWarning("Задачу не було створено");
    }
}
