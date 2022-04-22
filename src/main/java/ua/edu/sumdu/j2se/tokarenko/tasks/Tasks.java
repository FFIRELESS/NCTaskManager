package ua.edu.sumdu.j2se.tokarenko.tasks;

import java.time.LocalDateTime;
import java.util.*;

public class Tasks {
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

    static public SortedMap<LocalDateTime, Set<Task>>
    calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        TreeMap<LocalDateTime, Set<Task>> newCalendar = new TreeMap<>();
        LocalDateTime begin;
        Set<Task> pointerSet;

        tasks = incoming(tasks, start, end);

        for (Task task : tasks) {
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
        return newCalendar;
    }
}
