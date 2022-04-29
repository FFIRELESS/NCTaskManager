package ua.edu.sumdu.j2se.tokarenko.tasks.view;

import java.io.IOException;

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

    public static final String ANSI_ITALIC = "\033[3m";

    public static void newEmptyLine() {
        System.out.println();
    }

    public static void printTitle(String title) {
        System.out.println(ANSI_YELLOW_BACKGROUND + ANSI_BLACK + title + ANSI_RESET);
    }

    public static void printWarning(String text) {
        System.out.println(ANSI_RED_BACKGROUND + ANSI_BLACK + text + ANSI_RESET);
    }

    public static void printParagraph(String text) {
        System.out.println(ANSI_GREEN_BACKGROUND + ANSI_BLACK + text + ANSI_RESET);
    }

    public static void printTableHeading(String text) {
        System.out.println(ANSI_YELLOW + text + ANSI_RESET);
    }

    public static void printChooser(String text) {
        System.out.println(ANSI_BLUE_BACKGROUND + ANSI_BLACK + text + ANSI_RESET);
    }

    public static void printNote(String text) {
        System.out.println(ANSI_YELLOW + ANSI_ITALIC + text + ANSI_RESET);
    }

    public static void cls() throws IOException, InterruptedException {
        final String os = System.getProperty("os.name");
        if (os.contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            Runtime.getRuntime().exec("clear");
    }
}
