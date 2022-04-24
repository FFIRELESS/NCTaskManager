package ua.edu.sumdu.j2se.tokarenko.tasks.model;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.tokarenko.tasks.view.ConsoleView;
import ua.edu.sumdu.j2se.tokarenko.tasks.view.PrintCalendarView;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

public class Alerts extends Thread {
    private static final Logger logger = Logger.getLogger(Alerts.class);

    private final String alertTitle;

    private final PrintCalendarView view = new PrintCalendarView();

    private final AbstractTaskList tasks;

    public Alerts(String alertTitle, AbstractTaskList tasks) {
        super(alertTitle);
        this.tasks = tasks;
        this.alertTitle = alertTitle;
        setPriority(8);
        setDaemon(true);
        logger.debug("New alert is creating and launching " + alertTitle + " " + this);
        start();
    }

    /**
     * Order of actions to be performed by the thread
     */
    @Override
    public void run() {
        while (true) {
            SortedMap<LocalDateTime, Set<Task>> taskMap = Tasks.calendar(tasks, LocalDateTime.now(), LocalDateTime.now().plusMinutes(5));

            if (!taskMap.isEmpty()) {
                System.out.println();
                ConsoleView.lineSeparator();
                ConsoleView.printTitle(alertTitle);
                view.printCalendarTasks(taskMap);
                ConsoleView.lineSeparator();
                System.out.print("Your choice - ");
                logger.debug("Thread woke up and found tasks for the near future " + alertTitle);
            }
            try {
                sleep(300000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.debug("Thread woke up and found no tasks for the near future " + alertTitle);
        }
    }
}

