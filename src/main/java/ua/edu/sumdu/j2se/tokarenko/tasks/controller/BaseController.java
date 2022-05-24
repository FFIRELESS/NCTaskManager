package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.ArrayUserList;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.User;
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
     * Метод, що контролює роботу вхідного меню програми.
     *
     * @return наступний(обраний) режим програми.
     * @throws NullPointerException якщо режим програми невірний.
     */
    ProgramModes authMenuProcess() {
        throw programModeException;
    }

    /**
     * Метод, що контролює роботу програми на основі колекції задач.
     *
     * @param users колекція користувачів.
     * @return наступний(обраний) режим програми.
     * @throws NullPointerException якщо режим програми невірний.
     */
    ProgramModes process(ArrayUserList users) {
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
     * Метод, що контролює роботу програми на основі колекції задач та режиму програми.
     *
     * @param taskList колекція задач.
     * @param mode     режим програми.
     * @return наступний(обраний) режим програми.
     * @throws NullPointerException якщо режим програми невірний.
     */
    ProgramModes process(AbstractTaskList taskList, ProgramModes mode) {
        throw programModeException;
    }

    /**
     * Метод, що контролює роботу програми на основі колекції задач та поточного користувача.
     *
     * @param tasks колекція задач.
     * @param user  поточний користувач.
     * @return колекція задач користувача.
     */
    ArrayTaskList process(AbstractTaskList tasks, User user) {
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
    ProgramModes process(AbstractTaskList tasks, ProgramModes mode, User user) {
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
     * Метод, що контролює роботу програми на основі колекції всіх задач та колекції задач користувача.
     *
     * @param tasks     колекція задач.
     * @param userTasks колекція задач користувача.
     */
    public void process(AbstractTaskList tasks, ArrayTaskList userTasks) {
        throw programModeException;
    }

    /**
     * Метод, що контролює роботу програми на основі колекції користувачів та контролеру дій користувачів.
     *
     * @param users      колекція користувачів.
     * @param controller контролер дій користувачів.
     * @return наступний(обраний) режим програми.
     * @throws NullPointerException якщо режим програми невірний.
     */
    public ProgramModes process(ArrayUserList users, UserActionsController controller) {
        throw programModeException;
    }

    /**
     * Метод, що контролює зчитування задач з файлу.
     *
     * @return колекцію задач.
     * @throws NullPointerException якщо режим програми невірний.
     */
    AbstractTaskList readTasksFileProcess() {
        throw programModeException;
    }

    /**
     * Метод, що контролює зчитування користувачів з файлу.
     *
     * @return колекцію користувачів.
     * @throws NullPointerException якщо режим програми невірний.
     */
    ArrayUserList readUsersFileProcess() {
        throw programModeException;
    }

    /**
     * Метод, що контролює запис задач до файлу.
     *
     * @param taskList колекція задач.
     * @throws NullPointerException якщо режим програми невірний.
     */
    void writeTasksFileProcess(AbstractTaskList taskList) {
        throw programModeException;
    }

    /**
     * Метод, що контролює запис користувачів до файлу.
     *
     * @param userList колекція користувачів.
     * @throws NullPointerException якщо режим програми невірний.
     */
    void writeUsersFileProcess(ArrayUserList userList) {
        throw programModeException;
    }
}
