package ua.edu.sumdu.j2se.tokarenko.tasks.model;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.ListTypes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class LinkedTaskList extends AbstractTaskList {
    private static final Logger logger = Logger.getLogger(LinkedTaskList.class);

    public int taskAmount;
    private LinkedListPointer first;

    static class LinkedListPointer {
        private Task storedTask;
        private LinkedListPointer next;
    }

    static {
        type = ListTypes.types.LINKED;
    }

    /**
     * Конструктор LinkedTaskList.
     */
    public LinkedTaskList() {
        first = new LinkedListPointer();
    }

    /**
     * Імплементація методу батьківського класу, що повертає розмір колекції.
     *
     * @return розмір колекції.
     */
    public int size() {
        return taskAmount;
    }

    /**
     * Імплементація методу батьківського класу для додавання задачі в колекцію.
     *
     * @param task - задача, що додається в колекцію.
     * @throws NullPointerException якщо задача не задана.
     */
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

    /**
     * Імплементація методу батьківського класу для видалення задачі з колекції.
     *
     * @param task - задача, що видаляється з колекції.
     * @return true якщо задачу було видалено, false - задачу не знайдено в колекції.
     * @throws NullPointerException якщо задача не задана.
     */
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

    /**
     * Імплементація методу батьківського класу для пошуку задачі по індексу.
     *
     * @param index - індекс шуканої задачі.
     * @return шукану задачу.
     * @throws IndexOutOfBoundsException якщо індекс за межами розміру колекції.
     */
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

    /**
     * Імплементація ітератору батьківського класу.
     *
     * @return ітератор для даної колекції.
     */
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

    /**
     * Метод клонування об'єктів класу LinkedTaskList.
     *
     * @return копію об'єкту.
     */
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

    /**
     * Імплементація методу батьківського класу, що повертає потік колекції.
     *
     * @return потік колекції.
     */
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
