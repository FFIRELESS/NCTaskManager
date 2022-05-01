package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.ProgramModes;

import java.util.Scanner;

import static ua.edu.sumdu.j2se.tokarenko.tasks.utils.Exceptions.programModeException;

public abstract class BaseController {
    protected static Scanner in = new Scanner(System.in);

    /**
     * Метод, що контролює роботу всієї програми.
     *
     * @throws NullPointerException якщо режим програми невірний.
     */
    void process() {
        throw programModeException;
    }

    /**
     * Метод, що контролює роботу програми на основі колекції задач.
     *
     * @param tasks колекція задач.
     * @return наступний(обраний) режим програми.
     * @throws NullPointerException якщо режим програми невірний.
     */
    ProgramModes process(AbstractTaskList tasks) {
        throw programModeException;
    }

    /**
     * Метод, що контролює роботу програми на основі режиму програми.
     *
     * @param mode режим програми.
     * @return наступний(обраний) режим програми.
     * @throws NullPointerException якщо режим програми невірний.
     */
    ProgramModes process(ProgramModes mode) {
        throw programModeException;
    }

    /**
     * Метод, що контролює роботу програми на основі режиму програми та колекції задач.
     *
     * @param mode  режим програми.
     * @param tasks колекція задач.
     * @return наступний(обраний) режим програми.
     * @throws NullPointerException якщо режим програми невірний.
     */
    ProgramModes process(AbstractTaskList tasks, ProgramModes mode) {
        throw programModeException;
    }

    /**
     * Метод, що контролює роботу програми на основі колекції задач та контролеру дій задач.
     *
     * @param tasks      колекція задач.
     * @param controller контролер дій задачі.
     * @return наступний(обраний) режим програми.
     * @throws NullPointerException якщо режим програми невірний.
     */
    public ProgramModes process(AbstractTaskList tasks, TaskActionsController controller) {
        throw programModeException;
    }

    /**
     * Метод, що контролює зчитування задач з файлу.
     *
     * @return колекцію задач.
     * @throws NullPointerException якщо режим програми невірний.
     */
    AbstractTaskList readFileProcess() {
        throw programModeException;
    }

    /**
     * Метод, що контролює запис задач до файлу.
     *
     * @param taskList колекція задач.
     * @throws NullPointerException якщо режим програми невірний.
     */
    void writeFileProcess(AbstractTaskList taskList) {
        throw programModeException;
    }
}
