package ua.edu.sumdu.j2se.tokarenko.tasks;

public class Task {
    private String title;

    private int time;
    private int interval;
    private int start;
    private int end;

    private boolean isActive;
    private boolean isRepeating;

    public Task() {
    }

    public Task(String title, int time) {
        this.title = title;
        this.time = time;
    }

    public Task(String title, int start, int end, int interval) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTime() {
        if (this.isRepeating) {
            return this.start;
        }
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;

        if (this.isRepeating) {
            this.isRepeating = false;
        }
    }

    public void setTime(int start, int end, int interval) {
        this.start = start;
        this.end = end;
        this.interval = interval;

        if (!this.isRepeating) {
            this.isRepeating = true;
        }
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getStartTime() {
        if (!this.isRepeating) {
            return this.time;
        } else {
            return this.start;
        }
    }

    public int getEndTime() {
        if (!this.isRepeating) {
            return this.time;
        } else {
            return this.end;
        }
    }

//    public void setEnd(int end) {
//        this.end = end;
//    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public boolean isRepeated() {
        return this.isRepeating;
    }

//    public void setRepeating(boolean repeating) {
//        isRepeating = repeating;
//    }

    public int getRepeatInterval() {
        if (this.isRepeating) {
            return this.interval;
        } else {
            return 0;
        }
    }

    public int nextTimeAfter(int current) {

        if (this.isActive) {
            if (time != 0 && current < time) {
                return time;
            }
            if (time != 0 && current < start) {
                return start;
            }
            if (current >= start && start != 0) {
                int nextTime = start;

                while (current >= nextTime) {
                    if (nextTime + interval <= end) {
                        nextTime += interval;
                    } else {
                        return -1;
                    }
                }
                return nextTime;
            }
        }
        return -1;
    }
}
