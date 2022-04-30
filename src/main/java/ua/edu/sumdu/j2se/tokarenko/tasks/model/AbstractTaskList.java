package ua.edu.sumdu.j2se.tokarenko.tasks.model;

import ua.edu.sumdu.j2se.tokarenko.tasks.utils.ListTypes;

import java.io.Serializable;
import java.util.Iterator;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task>, Serializable {
    protected int taskAmount;
    protected static ListTypes.types type;

    /**
     * Метод для додавання задачі в колекцію.
     *
     * @param task - задача, що додається в колекцію.
     * @throws NullPointerException якщо задача не задана.
     */
    public abstract void add(Task task);

    /**
     * Метод для видалення задачі з колекції.
     *
     * @param task - задача, що видаляється з колекції.
     * @return true якщо задачу було видалено, false - задачу не знайдено в колекції.
     * @throws NullPointerException якщо задача не задана.
     */
    public abstract boolean remove(Task task);

    /**
     * Метод для пошуку задачі по індексу.
     *
     * @param index - індекс шуканої задачі.
     * @return шукану задачу.
     * @throws IndexOutOfBoundsException якщо індекс за межами розміру колекції.
     */
    public abstract Task getTask(int index);

    /**
     * Метод, що повертає потік колекції.
     *
     * @return потік колекції.
     */
    public abstract Stream<Task> getStream();

    /**
     * Метод, що повертає розмір колекції.
     *
     * @return розмір колекції.
     */
    public int size() {
        return taskAmount;
    }

    /**
     * Метод порівняння об'єктів колекції.
     *
     * @param object задача, яка буде порівнюватись.
     * @return true якщо задачі однакові, інакше - false.
     */
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

    /**
     * Метод хеш-кодування об'єктів колекції.
     *
     * @return хеш-код об'єкту даної колекції.
     */
    @Override
    public int hashCode() {
        int hash = taskAmount;

        for (Task task : this) {
            hash ^= task.hashCode();
        }

        hash >>= taskAmount;
        hash <<= type.hashCode();

        if (type == ListTypes.types.LINKED) {
            hash = ~hash;
        }
        return hash;
    }

    /**
     * Метод приведення об'єкту колекції до текстового формату.
     *
     * @return рядок, що вміщує значення всіх полів об'єкту колекції.
     */
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
