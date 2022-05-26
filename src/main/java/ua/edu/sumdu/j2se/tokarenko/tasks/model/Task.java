package ua.edu.sumdu.j2se.tokarenko.tasks.model;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Task implements Cloneable, Serializable {
    private static final Logger logger = Logger.getLogger(Task.class);

    private String title;

    UUID userId;

    private int interval;
    private LocalDateTime start;
    private LocalDateTime end;

    private boolean isActive;
    private boolean isRepeating;

    /**
     * Конструктор порожніх задач.
     */
    public Task() {
        logger.debug("Created new task: " + this);
    }

    /**
     * Конструктор неповторюваних задач.
     *
     * @param title  назва задачі.
     * @param userId ідентифікатор користувача.
     * @param time   час виконання задачі.
     * @throws IllegalArgumentException якщо час = null.
     */
    public Task(String title, UUID userId, LocalDateTime time) {
        setTitle(title);
        setUserId(userId);
        setTime(time);
        logger.debug("Created not repeating task: " + this);
    }

    /**
     * Конструктор повторюваних задач.
     *
     * @param title    назва задачі.
     * @param userId   ідентифікатор користувача.
     * @param start    час початку задачі.
     * @param end      час закінчення задачі.
     * @param interval інтервал повторювання задачі.
     * @throws IllegalArgumentException якщо початковий час >= кінцевому, або при null-значенні дат.
     */
    public Task(String title, UUID userId, LocalDateTime start, LocalDateTime end, int interval) {
        setTitle(title);
        setUserId(userId);
        setTime(start, end, interval);
        logger.debug("Created repeating task: " + this);
    }

    /**
     * Getter назви задачі.
     *
     * @return назву задачі.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Setter назви задачі.
     *
     * @param title назва задачі.
     */
    public void setTitle(String title) {
        this.title = title;
        logger.debug("Saved title of task: " + this);
    }

    /**
     * Getter ідентифікатора користувача.
     *
     * @return ідентифікатор користувача.
     */
    public UUID getUserId() {
        return userId;
    }

    /**
     * Setter ідентифікатора користувача.
     *
     * @param userId ідентифікатор користувача.
     */
    public void setUserId(UUID userId) {
        this.userId = userId;
        logger.debug("Saved new userId of task: " + this);
    }

    /**
     * Getter початкового або часу виконання задачі.
     *
     * @return початковий або час виконання задачі.
     */
    public LocalDateTime getTime() {
        return this.start;
    }

    /**
     * Setter часу виконання або початкового часу задачі.
     *
     * @param time час виконання неповторюваної задачі.
     * @throws IllegalArgumentException якщо параметр часу = null.
     */
    public void setTime(LocalDateTime time) {
        if (time == null) {
            logger.error("Task object is null");
            throw new IllegalArgumentException("Parameter has null value");
        }
        this.start = time;

        if (this.isRepeating) {
            this.isRepeating = false;
        }
        logger.debug("Saved start time of task: " + this);
    }

    /**
     * Setter часу повторюваної задачі.
     *
     * @param start    початковий час задачі.
     * @param end      кінцевий час задачі.
     * @param interval інтервал повторювання задачі в межах заданого періоду.
     * @throws IllegalArgumentException якщо початковий або кінцевий час = null,
     *                                  якщо початковий час >= кінцевому,
     *                                  якщо інтервал задачі <= 0.
     */
    public void setTime(LocalDateTime start, LocalDateTime end, int interval) {
        if (start == null || end == null) {
            logger.error("Task object is null");
            throw new IllegalArgumentException("Some parameter has null value!");
        }

        if (start.isAfter(end) || start.equals(end)) {
            logger.error("Start time >= end time");
            throw new IllegalArgumentException("\"Start\" parameter >= \"end\" parameter");
        }

        if (interval <= 0) {
            throw new IllegalArgumentException("Interval <= 0");
        }

        this.start = start;
        this.end = end;
        this.interval = interval;

        if (!this.isRepeating) {
            this.isRepeating = true;
        }
        logger.debug("Saved new start time, end time and interval of task: " + this);
    }

    /**
     * Getter початкового часу повторюваної задачі.
     *
     * @return початковий час повторюваної задачі.
     */
    public LocalDateTime getStartTime() {
        return this.start;
    }

    /**
     * Getter часу виконання повторюваної задачі.
     *
     * @return кінцевий час неповторюваної або повторюваної задачі.
     */
    public LocalDateTime getEndTime() {
        if (this.isRepeating) {
            return this.end;
        } else {
            return this.start;
        }
    }

    /**
     * Getter активності задачі.
     *
     * @return чи активна задача (true/false).
     */
    public boolean isActive() {
        return this.isActive;
    }

    /**
     * Setter активності задачі.
     *
     * @param active встановлює активність задачі.
     */
    public void setActive(boolean active) {
        this.isActive = active;
        logger.debug("Saved new activity of task: " + this);
    }

    /**
     * Getter повторюваності задачі.
     *
     * @return чи повторюється задача (true/false).
     */
    public boolean isRepeating() {
        return this.isRepeating;
    }

    /**
     * Getter інтервалу повторюваної задачі.
     *
     * @return інтервал повторюваної задачі, інакше "0".
     */
    public int getRepeatInterval() {
        if (this.isRepeating) {
            return this.interval;
        } else {
            return 0;
        }
    }

    /**
     * Метод, що повертає наступний час виконнання повторюваної задачі після вказаного часу.
     *
     * @param current час, після якого шукається наступний час виконання задачі.
     * @return наступний час виконання задачі або "0", якщо задача неактивна або не має наступного часу виконання.
     * @throws IllegalArgumentException якщо вхідний параметр = null.
     */
    public LocalDateTime nextTimeAfter(LocalDateTime current) {
        if (current == null) {
            throw new IllegalArgumentException("Current parameter is null");
        }

        if (!this.isActive) {
            return null;
        }

        if (this.isRepeating) {
            LocalDateTime nextTime = this.start;

            while (!current.isBefore(nextTime)) {
                if (nextTime.plusSeconds(this.interval).isAfter(this.end)) {
                    return null;
                } else {
                    nextTime = nextTime.plusSeconds(this.interval);
                }
            }
            return nextTime;
        } else {
            if (current.isBefore(this.start)) {
                return this.start;
            } else {
                return null;
            }
        }
    }

    /**
     * Метод порівняння об'єктів класу Task.
     *
     * @param object об'єкт, з яким буде порівняно даний об'єкт.
     * @return true якщо задачі однакові, інакше - false.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }

        Task task = (Task) object;
        return isActive() == task.isActive()
                && start == task.start
                && end == task.end
                && interval == task.interval
                && isRepeating == task.isRepeating
                && getTitle().equals(task.getTitle());
    }

    /**
     * Метод хеш-кодування об'єктів колекції.
     *
     * @return хеш-код об'єкту даної колекції.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), isActive(), start, end, interval, isRepeating);
    }

    /**
     * Метод приведення об'єкту колекції до текстового формату.
     *
     * @return рядок, що вміщує значення всіх полів об'єкту колекції.
     */
    @Override
    public String toString() {
        return "Task {" +
                "userId='" + userId + "'" +
                ", title='" + title + "'" +
                ", isActive=" + isActive +
                ", start=" + start +
                ", end=" + end +
                ", interval=" + interval +
                ", isRepeating=" + isRepeating +
                "}";
    }

    /**
     * Метод клонування об'єктів класу Task.
     *
     * @return вказівник на клонований об'єкт.
     * @throws CloneNotSupportedException якщо клас не наслідує інтерфейс Cloneable.
     */
    @Override
    public Task clone() throws CloneNotSupportedException {
        return (Task) super.clone();
    }
}
