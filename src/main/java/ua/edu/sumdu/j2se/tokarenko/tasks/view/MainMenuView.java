package ua.edu.sumdu.j2se.tokarenko.tasks.view;

public class MainMenuView extends PrintTasksView {
    /**
     * Метод, що виводить привітання.
     */
    public static void printHello() {
        ConsoleView.newEmptyLine();
        ConsoleView.printChooser("Привіт!       ");
        ConsoleView.printTitle("Слава Україні!");
    }

    /**
     * Метод, що виводить прощання.
     */
    public static void printBye() {
        ConsoleView.newEmptyLine();
        ConsoleView.printChooser("Гарного дня :)                                          ");
        ConsoleView.printTitle("Слава Україні, слава нації і пиZда російській федерації!");
    }

    /**
     * Метод, що виводить головне меню.
     */
    public void printMainMenu() {
        newEmptyLine();
        printTitle("ГОЛОВНЕ МЕНЮ");
        printTitle("1. Створити задачу");
        printTitle("2. Редагувати/видалити задачу");
        printTitle("3. Переглянути задачі за період");
        printTitle("4. Переглянути всі задачі");
        printTitle("5. Зберегти та вийти");
        newEmptyLine();
        printChooser("Оберіть режим: ");
        // cls();
    }

    /**
     * Метод, що виводить меню створення задачі.
     */
    public void printAddMenu() {
        newEmptyLine();
        printTitle("СТВОРЕННЯ НОВОЇ ЗАДАЧІ");
        printTitle("1. Встановити назву");
        printTitle("2. Активувати/деактивувати задачу");
        printTitle("3. Встановити час та інтервал повторюваної задачі");
        printTitle("4. Встановити час для неповторюваної задачі");
        printTitle("5. Зберегти та повернутися");
        printTitle("6. Повернутися");
        newEmptyLine();
        printChooser("Оберіть режим: ");
        // cls();
    }

    /**
     * Метод, що виводить меню редагування задачі.
     */
    public void printEditMenu() {
        newEmptyLine();
        printTitle("РЕДАГУВАННЯ ЗАДАЧІ");
        printTitle("0. Видалити задачу");
        printTitle("1. Редагувати назву");
        printTitle("2. Редагувати активність");
        printTitle("3. Встановити час та інтервал повторюваної задачі");
        printTitle("4. Встановити час для неповторюваної задачі");
        printTitle("5. Зберегти та повернутися");
        newEmptyLine();
        printChooser("Оберіть режим: ");
        // cls();
    }
}

