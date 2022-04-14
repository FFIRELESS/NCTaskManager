package ua.edu.sumdu.j2se.tokarenko.tasks;

public class LinkedTaskList extends AbstractTaskList {
    static class LinkedListPointer {
        private Task storedTask;
        private LinkedListPointer next;
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
            throw new NullPointerException("Task object parameter has null value!");
        }

        LinkedListPointer tempPointer = first;
        first.storedTask = task;
        first = new LinkedListPointer();
        first.next = tempPointer;
        taskAmount++;
    }

    public boolean remove(Task task) {
        if (task == null) {
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
                return true;
            }
            searchPointer = searchPointer.next;
        }
        return false;
    }

    public Task getTask(int index) {
        if (index < 0 || index >= taskAmount) {
            throw new IndexOutOfBoundsException("Invalid index parameter!");
        }

        index++;
        LinkedListPointer searchPointer = first;

        for (int counter = taskAmount; counter > taskAmount - index; counter--) {
            searchPointer = searchPointer.next;
        }
        return searchPointer.storedTask;
    }

    public LinkedTaskList incoming(int from, int to) {
        if (from < 0 || to < 0) {
            throw new IllegalArgumentException("Parameters are negative!");
        }

        if (from >= to) {
            throw new IllegalArgumentException("Parameter value error!");
        }

        int nextTime;
        LinkedListPointer searchPointer = first;
        LinkedTaskList newList = new LinkedTaskList();

        while (searchPointer.next == null) {
            searchPointer = searchPointer.next;
            nextTime = searchPointer.storedTask.nextTimeAfter(from);

            if (nextTime != -1 && nextTime < to) {
                newList.add(searchPointer.storedTask);
            }
        }
        return newList;
    }
}
