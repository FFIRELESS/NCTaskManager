package ua.edu.sumdu.j2se.tokarenko.tasks;

public abstract class AbstractTaskList {
    protected int size;
    static protected ListTypes.types type;

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public abstract int size();

    public abstract Task getTask(int index);

    public AbstractTaskList incoming(int from, int to) {
        if (from < 0 || to < 0) {
            throw new IllegalArgumentException("Parameters are negative!");
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
}
