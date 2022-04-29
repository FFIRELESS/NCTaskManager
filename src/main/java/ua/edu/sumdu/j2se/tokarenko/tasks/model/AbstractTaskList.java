package ua.edu.sumdu.j2se.tokarenko.tasks.model;

import ua.edu.sumdu.j2se.tokarenko.tasks.utils.ListTypes;

import java.io.Serializable;
import java.util.Iterator;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task>, Serializable {
    protected int taskAmount;
    protected static ListTypes.types type;

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public abstract Task getTask(int index);

    public abstract Stream<Task> getStream();

    public int size() {
        return taskAmount;
    }

    @Override
    public boolean equals(Object object) {
        AbstractTaskList tasks = (AbstractTaskList) object;

        if (this == object) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass() || taskAmount != tasks.taskAmount) {
            return false;
        }

        Iterator<Task> iterator = tasks.iterator();

        for (Task task : this) {
            if (!(task.equals(iterator.next()))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = taskAmount;

        for (Task task : this) {
            hash ^= task.hashCode();
        }

        if (type == ListTypes.types.ARRAY) {
            hash = ~hash;
        }
        return hash;
    }

    @Override
    public String toString() {
        Iterator<Task> strIterator = this.iterator();
        StringBuilder finalString = new StringBuilder();
        int number = 0;

        if (type == ListTypes.types.ARRAY) {
            finalString.append("ArrayTaskList.class | ");
        } else {
            finalString.append("LinkedTaskList.class | ");
        }
        finalString.append(taskAmount);

        while (strIterator.hasNext()) {
            finalString.append(" | Object");
            finalString.append(number);
            finalString.append(" | ");
            finalString.append(strIterator.next().toString());
            number++;
        }
        return new String(finalString);
    }
}
