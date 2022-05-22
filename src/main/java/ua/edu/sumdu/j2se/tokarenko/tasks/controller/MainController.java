package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.ArrayUserList;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.User;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.ProgramModes;
import ua.edu.sumdu.j2se.tokarenko.tasks.view.MainMenuView;

import java.util.Date;

public class MainController extends BaseController {
    protected static final Logger logger = Logger.getLogger(MainController.class);

    private final BaseController todayTasksController = new TodayTasksController();
    private final BaseController mainMenuController = new MainMenuController();
    private final BaseController authMenuController = new AuthMenuController();
    private final BaseController authController = new AuthController();
    private final BaseController registerController = new RegisterController();
    private final BaseController createTaskController = new CreateTaskController();
    private final BaseController editTaskController = new EditTaskController();
    private final BaseController printCalendarController = new PrintCalendarController();
    private final BaseController printTasksController = new PrintTasksController();
    private final BaseController fileIOController = new FileIOController();
    private final AlertsController alertsController = new AlertsController();

    ProgramModes mode = ProgramModes.AUTH_MENU;

    /**
     * Метод, що контролює роботу всієї програми
     */
    @Override
    public void process() {
        logger.debug("Program started at: " + new Date());

        MainMenuView.printHello();

        if (!ConsoleInputController.nextUkrainian()) {
            System.exit(1);
        }

        AbstractTaskList storedTasks = fileIOController.readTasksFileProcess();
        ArrayUserList storedUsers = fileIOController.readUsersFileProcess();

        while (!mode.equals(ProgramModes.MAIN_MENU) && !mode.equals(ProgramModes.EXIT)) {
            if (mode.equals(ProgramModes.AUTH_MENU)) {
                mode = authMenuController.authMenuProcess();
            }
            switch (mode) {
                case AUTHORIZE:
                    mode = authController.process(storedUsers);
                    break;

                case REGISTER:
                    mode = registerController.process(storedUsers);
                    break;

                case EXIT:
                    break;
            }
        }

        User currentUser = UserActionsController.getBufferedUser();

        while (!mode.equals(ProgramModes.EXIT)) {
            if (mode.equals(ProgramModes.MAIN_MENU)) {
                alertsController.process(storedTasks, currentUser);
                todayTasksController.process(storedTasks, currentUser);
                mode = mainMenuController.process(mode);
            }

            switch (mode) {
                case ADD:
                    mode = mainMenuController.process(mode);
                    mode = createTaskController.process(storedTasks, mode, currentUser);
                    break;
                case EDIT:
                    mode = editTaskController.process(storedTasks, ProgramModes.SKIP, currentUser);
                    if (mode.equals(ProgramModes.MAIN_MENU)) {
                        break;
                    }
                    mode = mainMenuController.process(mode);
                    mode = editTaskController.process(storedTasks, mode, currentUser);
                    break;
                case PRINT_CALENDAR:
                    mode = printCalendarController.process(storedTasks, currentUser);
                    break;
                case PRINT_ALL:
                    mode = printTasksController.process(storedTasks, currentUser);
                    break;
            }
        }
        MainMenuView.printBye();
        fileIOController.writeTasksFileProcess(storedTasks);
        fileIOController.writeUsersFileProcess(storedUsers);

        logger.debug("Program finished at: " + new Date());
    }
}
