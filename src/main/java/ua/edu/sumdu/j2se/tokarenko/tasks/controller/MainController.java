package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.ArrayTaskList;
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
    private final BaseController getUserTasksController = new GetUserTasksController();
    private final BaseController createTaskController = new CreateTaskController();
    private final BaseController editTaskController = new EditTaskController();
    private final BaseController printCalendarController = new PrintCalendarController();
    private final BaseController printTasksController = new PrintTasksController();
    private final BaseController savingController = new SavingController();
    private final BaseController fileIOController = new FileIOController();
    private final BaseController alertsController = new AlertsController();
    private final DatabaseController databaseController = new DatabaseController();

    ProgramModes mode = ProgramModes.AUTH_MENU;

    /**
     * Метод, що контролює роботу всієї програми
     */
    @Override
    public void process() {
        logger.debug("Program started at: " + new Date());

        MainMenuView.printHello();

//        if (!ConsoleInputController.nextUkrainian()) {
//            System.exit(1);
//        }

//        ArrayUserList storedUsers = fileIOController.readUsersFileProcess();
        databaseController.connect();
        ArrayUserList storedUsers = databaseController.readUsers();

        System.out.println(storedUsers);

        while (!mode.equals(ProgramModes.MAIN_MENU)) {
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
                    MainMenuView.printBye();
                    fileIOController.writeUsersFileProcess(storedUsers);
                    databaseController.disconnect();

                    System.exit(0);
                    logger.debug("Program finished at: " + new Date());
                    break;
            }
        }

        User currentUser = UserActionsController.getBufferedUser();
//        AbstractTaskList storedTasks = fileIOController.readTasksFileProcess();
        AbstractTaskList storedTasks = databaseController.readTasks();
        ArrayTaskList userTasks = getUserTasksController.process(storedTasks, currentUser);

        while (true) {
            if (mode.equals(ProgramModes.MAIN_MENU)) {
                alertsController.process(userTasks);
                todayTasksController.process(userTasks);
                mode = mainMenuController.process(mode);
            }

            switch (mode) {
                case ADD:
                    mode = mainMenuController.process(mode);
                    mode = createTaskController.process(userTasks, mode, currentUser);
                    break;
                case EDIT:
                    mode = editTaskController.process(userTasks, ProgramModes.SKIP);
                    if (mode.equals(ProgramModes.MAIN_MENU)) {
                        break;
                    }
                    mode = mainMenuController.process(mode);
                    mode = editTaskController.process(userTasks, mode);
                    break;
                case PRINT_CALENDAR:
                    mode = printCalendarController.process(userTasks);
                    break;
                case PRINT_ALL:
                    mode = printTasksController.process(userTasks);
                    break;
                case EXIT:
                    MainMenuView.printBye();
                    savingController.process(storedTasks, userTasks);
                    fileIOController.writeTasksFileProcess(storedTasks);
                    fileIOController.writeUsersFileProcess(storedUsers);
                    databaseController.disconnect();

                    System.exit(0);
                    logger.debug("Program finished at: " + new Date());
                    break;
            }
        }
    }
}
