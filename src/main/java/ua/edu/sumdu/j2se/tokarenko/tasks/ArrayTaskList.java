package ua.edu.sumdu.j2se.tokarenko.tasks;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayTaskList extends AbstractTaskList {
    private Task[] tasks;
    public int taskAmount;
    private final int INTERVAL = 5;

    static {
        type = ListTypes.types.ARRAY;
    }

    public ArrayTaskList() {
        tasks = new Task[INTERVAL];
    }

    public int size() {
        return taskAmount;
    }

    public void add(Task task) {
        if (task == null) {
            throw new NullPointerException("Task object parameter has null value!");
        }
        if (tasks.length == taskAmount) {
            Task[] tempArr = new Task[taskAmount + INTERVAL];

            System.arraycopy(tasks, 0, tempArr, 0, taskAmount);
            tasks = tempArr;
        }
        tasks[taskAmount++] = task;
    }

    public boolean remove(Task task) {
        if (task == null) {
            throw new NullPointerException("Task object parameter has null value!");
        }

        boolean status = false;
        int index_to_del;

        for (index_to_del = 0; index_to_del < taskAmount; index_to_del++) {
            if (tasks[index_to_del].equals(task)) {
                status = true;
                break;
            }
        }

        if (!status) {
            return false;
        }

        tasks[index_to_del] = null;
        taskAmount--;

        if (index_to_del != taskAmount) {
            System.arraycopy(tasks, index_to_del + 1, tasks, index_to_del, taskAmount - index_to_del);
        }

        if (tasks.length - INTERVAL == taskAmount && taskAmount != 0) {
            Task[] tempArray = new Task[taskAmount];
            System.arraycopy(tasks, 0, tempArray, 0, taskAmount);
            tasks = tempArray;
        }
        return true;
    }

    public Task getTask(int index) {
        if (index < 0 || index >= taskAmount) {
            throw new IndexOutOfBoundsException("Invalid task index!");
        }
        return tasks[index];
    }

    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < taskAmount;
            }

            @Override
            public Task next() {
                if (index == taskAmount) {
                    throw new NoSuchElementException(
                            "An iterator reached the end of the list"
                    );
                }
                return tasks[index++];
            }

            @Override
            public void remove() {
                if (index == 0) {
                    throw new IllegalStateException(
                            "Iterator method next() wasn't called"
                    );
                }

                index--;
                tasks[index] = null;
                taskAmount--;

                if (index != taskAmount) {
                    System.arraycopy(tasks, index + 1, tasks, index, taskAmount - index);
                }

                if (tasks.length - INTERVAL == taskAmount && taskAmount != 0) {
                    Task[] tempList = new Task[taskAmount];
                    System.arraycopy(tasks, 0, tempList, 0, taskAmount);
                    tasks = tempList;
                }
            }
        };
    }

    @Override
    public ArrayTaskList clone() {
        ArrayTaskList finalObject = new ArrayTaskList();
        for (int cnt = 0; cnt < taskAmount; cnt++) {
            finalObject.add(tasks[cnt]);
        }
        return finalObject;
    }
}
