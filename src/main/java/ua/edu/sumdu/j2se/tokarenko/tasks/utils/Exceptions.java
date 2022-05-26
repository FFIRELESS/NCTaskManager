package ua.edu.sumdu.j2se.tokarenko.tasks.utils;

public class Exceptions {
    public static final NullPointerException programModeException =
            new NullPointerException("Program mode configuration error");
    public static final NullPointerException emptyTaskListException =
            new NullPointerException("TaskList is empty");
    public static final IllegalArgumentException nullArgumentException =
            new IllegalArgumentException("Function argument is null");
    public static final IllegalStateException unknownModeException =
            new IllegalStateException("Mode is incorrect");
}
