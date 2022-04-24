package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.ProgramModes;

import java.util.Scanner;

import static ua.edu.sumdu.j2se.tokarenko.tasks.utils.Exceptions.programModeException;

public abstract class BaseController {
    protected static Scanner in = new Scanner(System.in);

    void process() {
        throw programModeException;
    }

    ProgramModes process(AbstractTaskList tasks) {
        throw programModeException;
    }

    ProgramModes process(ProgramModes mode) {
        throw programModeException;
    }

    ProgramModes process(AbstractTaskList tasks, ProgramModes mode, AlertsController controller) {
        throw programModeException;
    }

    public ProgramModes process(AbstractTaskList tasks, TaskActionsController controller) {
        throw programModeException;
    }

    AbstractTaskList readFileProcess() {
        throw programModeException;
    }

    void writeFileProcess(AbstractTaskList dataToSave) {
        throw programModeException;
    }
}
