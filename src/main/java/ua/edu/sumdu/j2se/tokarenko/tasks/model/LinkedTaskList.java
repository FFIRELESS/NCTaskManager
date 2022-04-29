package ua.edu.sumdu.j2se.tokarenko.tasks.model;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.ListTypes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class LinkedTaskList extends AbstractTaskList {
    private static final Logger logger = Logger.getLogger(LinkedTaskList.class);

    static class LinkedListPointer {
        private Task storedTask;
        private LinkedListPointer next;
    }

    static {
        type = ListTypes.types.LINKED;
    }

    public int taskAmount;
    private LinkedListPointer first;

    public LinkedTaskList() {
        first = new LinkedListPointer();
    }

    public int size() {
        return taskAmount;
    }

    public void add(Task task) {
        if (task == null) {
            logger.error("Task object is null");
            throw new NullPointerException("Task object parameter has null value!");
        }

        LinkedListPointer tempPointer = first;
        first.storedTask = task;
        first = new LinkedListPointer();
        first.next = tempPointer;
        taskAmount++;

        logger.debug("Task added: " + task);
    }

    public boolean remove(Task task) {
        if (task == null) {
            logger.error("Task object is null");
            throw new NullPointerException("Task object parameter has null value!");
        }

        LinkedListPointer searchPointer = first;

        if (taskAmount == 0) {
            return false;
        }

        while (searchPointer.next != null) {
            if (searchPointer.next.storedTask.equals(task)) {
                searchPointer.next = searchPointer.next.next;
                taskAmount--;

                logger.debug("Task removed: " + task);

                return true;
            }
            searchPointer = searchPointer.next;
        }
        return false;
    }

    public Task getTask(int index) {
        if (index < 0 || index >= taskAmount) {
            logger.error("Index of task is out of bound");
            throw new IndexOutOfBoundsException("Invalid index parameter!");
        }

        index++;
        LinkedListPointer searchPointer = first;

        for (int counter = taskAmount; counter > taskAmount - index; counter--) {
            searchPointer = searchPointer.next;
        }
        return searchPointer.storedTask;
    }

    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {
            private LinkedListPointer pointer = first.next;
            private LinkedListPointer delPointer = first;

            @Override
            public boolean hasNext() {
                return pointer != null;
            }

            @Override
            public Task next() {
                if (pointer == null) {
                    throw new NoSuchElementException("An iterator reached the end of the list");
                }

                if (delPointer.next.next == pointer) {
                    delPointer = delPointer.next;
                }
                pointer = pointer.next;

                return delPointer.next.storedTask;
            }

            @Override
            public void remove() {
                if (pointer == first.next) {
                    throw new IllegalStateException("Iterator method next() wasn't called");
                }
                delPointer.next = pointer;
                taskAmount--;
            }
        };
    }

    @Override
    public LinkedTaskList clone() {
        LinkedTaskList finalObject = new LinkedTaskList();
        LinkedListPointer newPointer = first.next;

        while (newPointer != null) {
            finalObject.add(newPointer.storedTask);
            newPointer = newPointer.next;
        }
        return finalObject;
    }

    @Override
    public Stream<Task> getStream() {
        ArrayList<Task> stream = new ArrayList<>(taskAmount);
        LinkedListPointer thisTask = first.next;

        while (thisTask != null) {
            stream.add(thisTask.storedTask);
            thisTask = thisTask.next;
        }
        return stream.stream();
    }
}
