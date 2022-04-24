package ua.edu.sumdu.j2se.tokarenko.tasks.view;

public class MainMenuView extends PrintTasksView {
    public void printMainMenu() {
        newEmptyLine();
        printTitle("MAIN MENU");
        printTitle("1. Create task");
        printTitle("2. Edit the task");
        printTitle("3. See tasks for the period");
        printTitle("4. See all tasks");
        printTitle("5. Save and quit");
        newEmptyLine();
        printChooser("Choose mode: ");
        // cls();
    }

    public void printAddMenu() {
        newEmptyLine();
        printTitle("CREATE NEW TASK");
        printTitle("1. Set task title");
        printTitle("2. Make task active");
        printTitle("3. Set time for repeating task");
        printTitle("4. Set time for not repeating task");
        printTitle("5. Create task and back");
        newEmptyLine();
        printChooser("Choose mode: ");
        // cls();
    }

    public void printChangeMenu() {
        newEmptyLine();
        printTitle("You are on the changing menu. Select one of the program functions: ");
        printTitle("1. Set name");
        printTitle("2. Activate task");
        printTitle("3. Set time for repeated task");
        printTitle("4. Set time for non-repeated task");
        printTitle("5. Save and exit to main menu");
        newEmptyLine();
        printChooser("Choose mode: ");
        // cls();
    }
}

