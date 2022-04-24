package ua.edu.sumdu.j2se.tokarenko.tasks.model;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Task implements Cloneable, Serializable {
    private static final Logger logger = Logger.getLogger(Task.class);

    private String title;

    private int interval;
    private LocalDateTime start;
    private LocalDateTime end;

    private boolean isActive;
    private boolean isRepeating;

    public Task() {
    }

    public Task(String title, LocalDateTime time) {
        setTitle(title);
        setTime(time);
    }

    public Task(String title, LocalDateTime start, LocalDateTime end, int interval) {
        setTitle(title);
        setTime(start, end, interval);
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        logger.debug("Saved new title of task: " + this);

        this.title = title;
    }

    public LocalDateTime getTime() {
        return this.start;
    }

    public void setTime(LocalDateTime time) {
        if (time == null) {
            throw new IllegalArgumentException("Parameter has null value");
        }
        this.start = time;

        if (this.isRepeating) {
            this.isRepeating = false;
        }

        logger.debug("Saved new start time of task: " + this);
    }

    public void setTime(LocalDateTime start, LocalDateTime end, int interval) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Some parameter has null value!");
        }

        if (start.isAfter(end) || start.equals(end)) {
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

    public LocalDateTime getStartTime() {
        return this.start;
    }

    public LocalDateTime getEndTime() {
        if (this.isRepeating) {
            return this.end;
        } else {
            return this.start;
        }
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(boolean active) {
        logger.debug("Saved new activity of task: " + this);

        this.isActive = active;
    }

    public boolean isRepeating() {
        return this.isRepeating;
    }

    public int getRepeatInterval() {
        if (this.isRepeating) {
            return this.interval;
        } else {
            return 0;
        }
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), isActive(), start, end, interval, isRepeating);
    }

    @Override
    public String toString() {
        return "Task {" +
                "title='" + title + "'" +
                ", isActive=" + isActive +
                ", start=" + start +
                ", end=" + end +
                ", interval=" + interval +
                ", isRepeating=" + isRepeating +
                "}";
    }

    @Override
    public Task clone() throws CloneNotSupportedException {
        return (Task) super.clone();
    }
}
