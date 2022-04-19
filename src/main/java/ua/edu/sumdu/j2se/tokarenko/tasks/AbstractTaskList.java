package ua.edu.sumdu.j2se.tokarenko.tasks;

import java.util.Iterator;
import java.util.Objects;

public abstract class AbstractTaskList implements Iterable<Task> {
    protected int size;
    static protected ListTypes.types type;

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public abstract int size();

    public abstract Task getTask(int index);

    public AbstractTaskList incoming(int from, int to) {
        if (from < 0 || to < 0) {
            throw new IllegalArgumentException("One parameter are negative!");
        }

        if (from >= to) {
            throw new IllegalArgumentException("\"From\" parameter >= \"to\" parameter!");
        }

        int nextTime;
        AbstractTaskList finalArr = TaskListFactory.createTaskList(type);

        for (int i = 0; i < size; i++) {
            nextTime = this.getTask(i).nextTimeAfter(from);

            if (nextTime != -1 && nextTime < to) {
                finalArr.add(this.getTask(i));
            }
        }
        return finalArr;
    }

    @Override
    public boolean equals(Object object) {
        AbstractTaskList tasks = (AbstractTaskList) object;

        if (this == object) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass() || size != tasks.size) {
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
        return Objects.hash(size);
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
        finalString.append(size);

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
