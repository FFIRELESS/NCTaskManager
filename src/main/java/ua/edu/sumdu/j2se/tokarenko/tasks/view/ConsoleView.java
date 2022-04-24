package ua.edu.sumdu.j2se.tokarenko.tasks.view;

public class ConsoleView {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void lineSeparator() {
        System.out.println(ANSI_BLACK + "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" + ANSI_RESET);
    }

    public static void newEmptyLine() {
        System.out.println();
    }

    public static void printTitle(String title) {
        System.out.println(ANSI_YELLOW_BACKGROUND + ANSI_BLACK + title + ANSI_RESET);
    }
    public static void printChooser(String text) {
        System.out.println(ANSI_GREEN_BACKGROUND + text + ANSI_RESET);
    }

    public static void cls() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
}
