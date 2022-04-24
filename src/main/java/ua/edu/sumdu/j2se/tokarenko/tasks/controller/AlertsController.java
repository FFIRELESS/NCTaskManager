package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.Alerts;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.DataTest;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.ProgramModes;

import static ua.edu.sumdu.j2se.tokarenko.tasks.utils.Exceptions.emptyTaskListException;

public class AlertsController extends BaseController {
    /**
     * Notified thread
     */
    public Alerts notified;

    /**
     * Method that controls the process of creating and launching a notified thread
     * @param taskList collection with tasks
     * @return constant indicating which next action to perform in the program4
     */
    @Override
    public ProgramModes process(AbstractTaskList taskList) {
        if (DataTest.isEmptyList(taskList)) {
            notified = new Alerts("Tasks for the near future:", taskList);
        } else {
            throw emptyTaskListException;
        }

        return ProgramModes.MAIN_MENU;
    }
}
