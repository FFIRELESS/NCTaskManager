package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.ProgramModes;
import ua.edu.sumdu.j2se.tokarenko.tasks.view.MainMenuView;

public class MainController extends BaseController {
    protected static final Logger logger = Logger.getLogger(MainController.class);

    private final BaseController todayTasksController = new TodayTasksController();
    private final BaseController mainMenuController = new MainMenuController();
    private final BaseController createTaskController = new CreateTaskController();
    private final BaseController editTaskController = new EditTaskController();
    private final BaseController printCalendarController = new PrintCalendarController();
    private final BaseController printTasksController = new PrintTasksController();
    private final BaseController fileIOController = new FileIOController();
    private final AlertsController alertsController = new AlertsController();

    ProgramModes mode = ProgramModes.MAIN_MENU;

    @Override
    public void process() {
        logger.debug("Program started");

        MainMenuView.printHello();

        // перевірка на українця
        if (!ConsoleInputController.nextUkrainian()) {
            System.exit(1);
        }

        AbstractTaskList storedTasks = fileIOController.readFileProcess();

        while (!mode.equals(ProgramModes.EXIT)) {
            if (mode.equals(ProgramModes.MAIN_MENU)) {
                alertsController.process(storedTasks);
                todayTasksController.process(storedTasks);
                mode = mainMenuController.process(mode);
            }

            switch (mode) {
                case ADD:
                    mode = mainMenuController.process(mode);
                    mode = createTaskController.process(storedTasks, mode);
                    break;
                case EDIT:
                    mode = editTaskController.process(storedTasks, ProgramModes.SKIP);
                    mode = mainMenuController.process(mode);
                    mode = editTaskController.process(storedTasks, mode);
                    break;
                case PRINT_CALENDAR:
                    mode = printCalendarController.process(storedTasks);
                    break;
                case PRINT_ALL:
                    mode = printTasksController.process(storedTasks);
                    break;
            }
        }
        MainMenuView.printBye();
        fileIOController.writeFileProcess(storedTasks);

        logger.debug("Program finished");
    }
}
