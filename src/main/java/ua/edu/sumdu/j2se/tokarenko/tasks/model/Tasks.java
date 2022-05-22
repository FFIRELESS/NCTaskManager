package ua.edu.sumdu.j2se.tokarenko.tasks.model;

import java.time.LocalDateTime;
import java.util.*;

public class Tasks {
    /**
     * Метод пошуку задач в заданому періоді.
     *
     * @param tasks колекція задач.
     * @param start початковий час періоду.
     * @param end   кінцевий час періоду.
     * @return колекцію задач в заданому періоді.
     * @throws NullPointerException     якщо часовий аргумент = null.
     * @throws IllegalArgumentException якщо початковий час >= кінцевому.
     */
    static public Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        if (tasks == null) {
            throw new IllegalArgumentException("Iterable<Task> parameter is null");
        }

        if (start == null || end == null) {
            throw new IllegalArgumentException("Some parameter is null");
        }

        if (start.isAfter(end)) {
            throw new IllegalArgumentException("\"From\" parameter >= \"to\" parameter!");
        }

        List<Task> finalArray = new LinkedList<>();
        LocalDateTime nextTime;

        for (Task task : tasks) {
            nextTime = task.nextTimeAfter(start);

            if (nextTime != null && !nextTime.isAfter(end)) {
                finalArray.add(task);
            }
        }
        return finalArray;
    }

    /**
     * Метод, що повертає сортований календар із задачами за заданий період.
     *
     * @param tasks колекція задач.
     * @param user  поточний користувач.
     * @param start початковий час періоду.
     * @param end   кінцевий час періоду.
     * @return колекція-календар задач.
     * @throws NullPointerException     якщо часовий аргумент = null.
     * @throws IllegalArgumentException якщо початковий час >= кінцевому.
     */
    static public SortedMap<LocalDateTime, Set<Task>>
    calendar(Iterable<Task> tasks, User user, LocalDateTime start, LocalDateTime end) {
        TreeMap<LocalDateTime, Set<Task>> newCalendar = new TreeMap<>();
        LocalDateTime begin;
        Set<Task> pointerSet;

        tasks = incoming(tasks, start, end);

        for (Task task : tasks) {
            if (task.getUserId().equals(user.getUserId())) {
                begin = start;

                while (true) {
                    begin = task.nextTimeAfter(begin);

                    if (begin == null || begin.isAfter(end)) {
                        break;
                    }

                    if (newCalendar.containsKey(begin)) {
                        pointerSet = newCalendar.get(begin);
                    } else {
                        pointerSet = new HashSet<>();
                        newCalendar.put(begin, pointerSet);
                    }
                    pointerSet.add(task);
                }
            }
        }
        return newCalendar;
    }
}
