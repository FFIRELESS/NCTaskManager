package ua.edu.sumdu.j2se.tokarenko.tasks.utils;

import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.LinkedTaskList;

public class TaskListFactory {
    /**
     * Метод створення об'єктів обраного типу класу.
     *
     * @param type тип класу.
     * @return об'єкт класу.
     */
    static public AbstractTaskList createTaskList(ListTypes.types type) {
        if (type == ListTypes.types.ARRAY) {
            return new ArrayTaskList();
        } else {
            return new LinkedTaskList();
        }
    }
}
